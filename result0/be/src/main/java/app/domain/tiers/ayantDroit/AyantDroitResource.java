package app.domain.tiers.ayantDroit;

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
import app.domain.sinistre.sousDossier.SousDossier;
import app.domain.sinistre.sousDossier.SousDossierRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ayantDroit")
@Transactional
public class AyantDroitResource {

    private final AyantDroitRepository ayantDroitRepository;
    private final SousDossierRepository sousDossierRepository;

    public AyantDroitResource(AyantDroitRepository ayantDroitRepository, SousDossierRepository sousDossierRepository) {
        this.ayantDroitRepository = ayantDroitRepository;
        this.sousDossierRepository = sousDossierRepository;
    }

    @PostMapping("/sinistre/{idSinistre}/sousDossier/{idSousDossier}")
    public ResponseEntity<AyantDroitDto> creer(@PathVariable Long idSinistre,@PathVariable Long idSousDossier, @Valid @RequestBody AyantDroit ayantDroit) throws URISyntaxException {
        Optional<SousDossier> sousDossier = sousDossierRepository.findById(idSousDossier);
        if (!sousDossier.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sousDossier.notFoud");
        }
        ayantDroit.setSousDossier(sousDossier.get());
        AyantDroit result = ayantDroitRepository.save(ayantDroit);
        return ResponseEntity.ok().body(AyantDroitDto.asEntity(result));
    }

    @PutMapping("/sinistre/{idSinistre}/sousDossier/{idSousDossier}")
    public ResponseEntity<Void> enregistrer(@PathVariable Long idSinistre,@PathVariable Long idSousDossier, @Valid @RequestBody AyantDroit ayantDroit) throws URISyntaxException {
        ayantDroitRepository.save(ayantDroit);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lister")
    public List<AyantDroitDto> lister() {
        return ayantDroitRepository.findAllByOrderByNom().stream().map(AyantDroitDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AyantDroitDto> recupererParId(@PathVariable Long id) {
        Optional<AyantDroit> ayantDroit = ayantDroitRepository.findById(id);
        return ayantDroit.map(response -> ResponseEntity.ok().body(AyantDroitDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}