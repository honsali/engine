package app.domain.conception.modeleQuestionnaire;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/modeleQuestionnaire")
@Transactional
public class ModeleQuestionnaireResource {

    private final ModeleQuestionnaireRepository modeleQuestionnaireRepository;

    public ModeleQuestionnaireResource(ModeleQuestionnaireRepository modeleQuestionnaireRepository) {
        this.modeleQuestionnaireRepository = modeleQuestionnaireRepository;
    }

    @PostMapping
    public ResponseEntity<ModeleQuestionnaireDto> creer(@Valid @RequestBody ModeleQuestionnaire modeleQuestionnaire) throws URISyntaxException {
        ModeleQuestionnaire result = modeleQuestionnaireRepository.save(modeleQuestionnaire);
        return ResponseEntity.ok().body(ModeleQuestionnaireDto.asEntity(result));
    }

    @GetMapping("/lister")
    public List<ModeleQuestionnaireDto> lister() {
        return modeleQuestionnaireRepository.findAllByOrderByDateCreation().stream().map(ModeleQuestionnaireDto::asEntity).collect(Collectors.toList());
    }
}