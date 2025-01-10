package app.domain.execution.reponse;

import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.domain.execution.questionnaire.Questionnaire;
import app.domain.execution.questionnaire.QuestionnaireRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reponse")
@Transactional
public class ReponseResource {

    private final ReponseRepository reponseRepository;
    private final QuestionnaireRepository questionnaireRepository;

    public ReponseResource(ReponseRepository reponseRepository, QuestionnaireRepository questionnaireRepository) {
        this.reponseRepository = reponseRepository;
        this.questionnaireRepository = questionnaireRepository;
    }

    @PutMapping
    public ResponseEntity<Void> enregistrer(@Valid @RequestBody Reponse reponse) throws URISyntaxException {
        reponseRepository.save(reponse);
        return ResponseEntity.noContent().build();
    }
}