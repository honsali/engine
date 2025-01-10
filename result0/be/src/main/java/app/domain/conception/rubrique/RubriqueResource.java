package app.domain.conception.rubrique;

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
import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaire;
import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaireRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rubrique")
@Transactional
public class RubriqueResource {

    private final RubriqueRepository rubriqueRepository;
    private final VersionModeleQuestionnaireRepository versionModeleQuestionnaireRepository;

    public RubriqueResource(RubriqueRepository rubriqueRepository, VersionModeleQuestionnaireRepository versionModeleQuestionnaireRepository) {
        this.rubriqueRepository = rubriqueRepository;
        this.versionModeleQuestionnaireRepository = versionModeleQuestionnaireRepository;
    }

    @PostMapping("/versionModeleQuestionnaire/{idVersionModeleQuestionnaire}")
    public ResponseEntity<RubriqueDto> creer(@PathVariable Long idVersionModeleQuestionnaire, @Valid @RequestBody Rubrique rubrique) throws URISyntaxException {
        Optional<VersionModeleQuestionnaire> versionModeleQuestionnaire = versionModeleQuestionnaireRepository.findById(idVersionModeleQuestionnaire);
        if (!versionModeleQuestionnaire.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "versionModeleQuestionnaire.notFoud");
        }
        rubrique.setVersionModeleQuestionnaire(versionModeleQuestionnaire.get());
        Rubrique result = rubriqueRepository.save(rubrique);
        return ResponseEntity.ok().body(RubriqueDto.asEntity(result));
    }

    @PutMapping
    public ResponseEntity<Void> enregistrer(@Valid @RequestBody Rubrique rubrique) throws URISyntaxException {
        rubriqueRepository.save(rubrique);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listerParIdVersionModeleQuestionnaire/{idVersionModeleQuestionnaire}")
    public List<RubriqueDto> listerParIdVersionModeleQuestionnaire(@PathVariable Long idVersionModeleQuestionnaire) {
        return rubriqueRepository.findAllByVersionModeleQuestionnaire_IdOrderByPosition(idVersionModeleQuestionnaire).stream().map(RubriqueDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RubriqueDto> recupererParId(@PathVariable Long id) {
        Optional<Rubrique> rubrique = rubriqueRepository.findById(id);
        return rubrique.map(response -> ResponseEntity.ok().body(RubriqueDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}