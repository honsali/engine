package app.domain.conception.option;

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
@RequestMapping("/api/option")
@Transactional
public class OptionResource {

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    public OptionResource(OptionRepository optionRepository, QuestionRepository questionRepository) {
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/question/{idQuestion}")
    public ResponseEntity<OptionDto> creer(@PathVariable Long idQuestion, @Valid @RequestBody Option option) throws URISyntaxException {
        Optional<Question> question = questionRepository.findById(idQuestion);
        if (!question.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "question.notFoud");
        }
        option.setQuestion(question.get());
        Option result = optionRepository.save(option);
        return ResponseEntity.ok().body(OptionDto.asEntity(result));
    }

    @PutMapping
    public ResponseEntity<Void> enregistrer(@Valid @RequestBody Option option) throws URISyntaxException {
        optionRepository.save(option);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listerParIdQuestion/{idQuestion}")
    public List<OptionDto> listerParIdQuestion(@PathVariable Long idQuestion) {
        return optionRepository.findAllByQuestion_IdOrderByPosition(idQuestion).stream().map(OptionDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDto> recupererParId(@PathVariable Long id) {
        Optional<Option> option = optionRepository.findById(id);
        return option.map(response -> ResponseEntity.ok().body(OptionDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerOption(@PathVariable Long id) throws URISyntaxException {
        optionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}