package app.domain.conception.question;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.domain.conception.rubrique.Rubrique;
import app.domain.conception.rubrique.RubriqueRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/question")
@Transactional
public class QuestionResource {

    private final QuestionRepository questionRepository;
    private final RubriqueRepository rubriqueRepository;

    public QuestionResource(QuestionRepository questionRepository, RubriqueRepository rubriqueRepository) {
        this.questionRepository = questionRepository;
        this.rubriqueRepository = rubriqueRepository;
    }

    @PostMapping("/rubrique/{idRubrique}")
    public ResponseEntity<QuestionDto> creer(@PathVariable Long idRubrique, @Valid @RequestBody Question question) throws URISyntaxException {
        Optional<Rubrique> rubrique = rubriqueRepository.findById(idRubrique);
        if (!rubrique.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "rubrique.notFoud");
        }
        question.setRubrique(rubrique.get());
        Question result = questionRepository.save(question);
        return ResponseEntity.ok().body(QuestionDto.asEntity(result));
    }

    @PutMapping
    public ResponseEntity<Void> enregistrer(@Valid @RequestBody Question question) throws URISyntaxException {
        questionRepository.save(question);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listerParIdRubrique/{idRubrique}")
    public List<QuestionDto> listerParIdRubrique(@PathVariable Long idRubrique) {
        return questionRepository.findAllByRubrique_IdOrderByPosition(idRubrique).stream().map(QuestionDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> recupererParId(@PathVariable Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.map(response -> ResponseEntity.ok().body(QuestionDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}