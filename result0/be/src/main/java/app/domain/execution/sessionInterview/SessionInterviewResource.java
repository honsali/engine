package app.domain.execution.sessionInterview;

import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.domain.execution.questionnaire.Questionnaire;
import app.domain.execution.questionnaire.QuestionnaireRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sessionInterview")
@Transactional
public class SessionInterviewResource {

    private final SessionInterviewRepository sessionInterviewRepository;
    private final QuestionnaireRepository questionnaireRepository;

    public SessionInterviewResource(SessionInterviewRepository sessionInterviewRepository, QuestionnaireRepository questionnaireRepository) {
        this.sessionInterviewRepository = sessionInterviewRepository;
        this.questionnaireRepository = questionnaireRepository;
    }

    @PostMapping("/questionnaire/{idQuestionnaire}")
    public ResponseEntity<SessionInterviewDto> creer(@PathVariable Long idQuestionnaire, @Valid @RequestBody SessionInterview sessionInterview) throws URISyntaxException {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(idQuestionnaire);
        if (!questionnaire.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "questionnaire.notFoud");
        }
        sessionInterview.setQuestionnaire(questionnaire.get());
        SessionInterview result = sessionInterviewRepository.save(sessionInterview);
        return ResponseEntity.ok().body(SessionInterviewDto.asEntity(result));
    }
}