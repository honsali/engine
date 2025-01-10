package app.domain.commun.tache.tache;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.domain.sinistre.sinistre.Sinistre;
import app.domain.sinistre.sinistre.SinistreRepository;

@RestController
@RequestMapping("/api/tache")
@Transactional
public class TacheResource {

    private final TacheRepository tacheRepository;
    private final SinistreRepository sinistreRepository;

    public TacheResource(TacheRepository tacheRepository, SinistreRepository sinistreRepository) {
        this.tacheRepository = tacheRepository;
        this.sinistreRepository = sinistreRepository;
    }
}