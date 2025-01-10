package app.domain.conception.transition;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.domain.conception.question.Question;
import app.domain.conception.question.QuestionRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transition")
@Transactional
public class TransitionResource {

    private final TransitionRepository transitionRepository;
    private final QuestionRepository questionRepository;

    public TransitionResource(TransitionRepository transitionRepository, QuestionRepository questionRepository) {
        this.transitionRepository = transitionRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/question/{idQuestion}")
    public ResponseEntity<TransitionDto> creer(@PathVariable Long idQuestion, @Valid @RequestBody Transition transition) throws URISyntaxException {
        Optional<Question> question = questionRepository.findById(idQuestion);
        if (!question.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "question.notFoud");
        }
        transition.setQuestion(question.get());
        Transition result = transitionRepository.save(transition);
        return ResponseEntity.ok().body(TransitionDto.asEntity(result));
    }

    @PutMapping
    public ResponseEntity<Void> enregistrer(@Valid @RequestBody Transition transition) throws URISyntaxException {
        transitionRepository.save(transition);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listerParIdQuestion/{idQuestion}")
    public List<TransitionDto> listerParIdQuestion(@PathVariable Long idQuestion) {
        return transitionRepository.findAllByQuestion_IdOrderByRaison(idQuestion).stream().map(TransitionDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransitionDto> recupererParId(@PathVariable Long id) {
        Optional<Transition> transition = transitionRepository.findById(id);
        return transition.map(response -> ResponseEntity.ok().body(TransitionDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTransition(@PathVariable Long id) throws URISyntaxException {
        transitionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}