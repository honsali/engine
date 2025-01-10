package app.domain.conception.versionModeleQuestionnaire;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/versionModeleQuestionnaire")
@Transactional
public class VersionModeleQuestionnaireResource {

    private final VersionModeleQuestionnaireRepository versionModeleQuestionnaireRepository;

    public VersionModeleQuestionnaireResource(VersionModeleQuestionnaireRepository versionModeleQuestionnaireRepository) {
        this.versionModeleQuestionnaireRepository = versionModeleQuestionnaireRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VersionModeleQuestionnaireDto> recupererParId(@PathVariable Long id) {
        Optional<VersionModeleQuestionnaire> versionModeleQuestionnaire = versionModeleQuestionnaireRepository.findById(id);
        return versionModeleQuestionnaire.map(response -> ResponseEntity.ok().body(VersionModeleQuestionnaireDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}