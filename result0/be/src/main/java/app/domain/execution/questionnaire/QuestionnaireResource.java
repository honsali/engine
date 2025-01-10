package app.domain.execution.questionnaire;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/questionnaire")
@Transactional
public class QuestionnaireResource {

    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireResource(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    @PostMapping
    public ResponseEntity<QuestionnaireDto> creer(@Valid @RequestBody Questionnaire questionnaire) throws URISyntaxException {
        Questionnaire result = questionnaireRepository.save(questionnaire);
        return ResponseEntity.ok().body(QuestionnaireDto.asEntity(result));
    }

    @GetMapping("/lister")
    public List<QuestionnaireDto> lister() {
        return questionnaireRepository.findAllByOrderByCode().stream().map(QuestionnaireDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionnaireDto> recupererParId(@PathVariable Long id) {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        return questionnaire.map(response -> ResponseEntity.ok().body(QuestionnaireDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}