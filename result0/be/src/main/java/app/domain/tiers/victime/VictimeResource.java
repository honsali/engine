package app.domain.tiers.victime;

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
@RequestMapping("/api/victime")
@Transactional
public class VictimeResource {

    private final VictimeRepository victimeRepository;
    private final SinistreRepository sinistreRepository;

    public VictimeResource(VictimeRepository victimeRepository, SinistreRepository sinistreRepository) {
        this.victimeRepository = victimeRepository;
        this.sinistreRepository = sinistreRepository;
    }

    @PostMapping("/sinistre/{idSinistre}")
    public ResponseEntity<VictimeDto> creer(@PathVariable Long idSinistre, @Valid @RequestBody Victime victime) throws URISyntaxException {
        Optional<Sinistre> sinistre = sinistreRepository.findById(idSinistre);
        if (!sinistre.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sinistre.notFoud");
        }
        victime.setSinistre(sinistre.get());
        Victime result = victimeRepository.save(victime);
        return ResponseEntity.ok().body(VictimeDto.asEntity(result));
    }

    @PutMapping("/sinistre/{idSinistre}")
    public ResponseEntity<Void> enregistrer(@PathVariable Long idSinistre, @Valid @RequestBody Victime victime) throws URISyntaxException {
        victimeRepository.save(victime);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/envoyerSMS/sinistre/{idSinistre}/{idVictime}")
    public ResponseEntity<VictimeDto> envoyerSMS(@PathVariable Long idSinistre,@PathVariable Long idVictime) throws URISyntaxException {
        Victime result = victimeRepository.save(victime);
        return ResponseEntity.ok().body(VictimeDto.asEntity(result));
    }

    @GetMapping("/lister")
    public List<VictimeDto> lister() {
        return victimeRepository.findAllByOrderByNom().stream().map(VictimeDto::asEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VictimeDto> recupererParId(@PathVariable Long id) {
        Optional<Victime> victime = victimeRepository.findById(id);
        return victime.map(response -> ResponseEntity.ok().body(VictimeDto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}