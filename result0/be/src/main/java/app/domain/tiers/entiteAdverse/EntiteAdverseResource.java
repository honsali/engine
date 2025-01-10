package app.domain.tiers.entiteAdverse;

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
import app.domain.sinistre.sinistre.Sinistre;
import app.domain.sinistre.sinistre.SinistreRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entiteAdverse")
@Transactional
public class EntiteAdverseResource {

    private final EntiteAdverseRepository entiteAdverseRepository;
    private final SinistreRepository sinistreRepository;

    public EntiteAdverseResource(EntiteAdverseRepository entiteAdverseRepository, SinistreRepository sinistreRepository) {
        this.entiteAdverseRepository = entiteAdverseRepository;
        this.sinistreRepository = sinistreRepository;
    }

    @PostMapping("/sinistre/{idSinistre}")
    public ResponseEntity<EntiteAdverseDto> creer(@PathVariable Long idSinistre, @Valid @RequestBody EntiteAdverse entiteAdverse) throws URISyntaxException {
        Optional<Sinistre> sinistre = sinistreRepository.findById(idSinistre);
        if (!sinistre.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sinistre.notFoud");
        }
        entiteAdverse.setSinistre(sinistre.get());
        EntiteAdverse result = entiteAdverseRepository.save(entiteAdverse);
        return ResponseEntity.ok().body(EntiteAdverseDto.asEntity(result));
    }

    @PutMapping("/sinistre/{idSinistre}")
    public ResponseEntity<Void> enregistrer(@PathVariable Long idSinistre, @Valid @RequestBody EntiteAdverse entiteAdverse) throws URISyntaxException {
        entiteAdverseRepository.save(entiteAdverse);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/envoyerSMS/sinistre/{idSinistre}/{idEntiteAdverse}")
    public ResponseEntity<EntiteAdverseDto> envoyerSMS(@PathVariable Long idSinistre,@PathVariable Long idEntiteAdverse) throws URISyntaxException {
        EntiteAdverse result = entiteAdverseRepository.save(entiteAdverse);
        return ResponseEntity.ok().body(EntiteAdverseDto.asEntity(result));
    }

    @GetMapping("/lister")
    public List<EntiteAdverseDto> lister() {
        return entiteAdverseRepository.findAllByOrderByNom().stream().map(EntiteAdverseDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntiteAdverseDto> recupererParId(@PathVariable Long id) {
        Optional<EntiteAdverse> entiteAdverse = entiteAdverseRepository.findById(id);
        return entiteAdverse.map(response -> ResponseEntity.ok().body(EntiteAdverseDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}