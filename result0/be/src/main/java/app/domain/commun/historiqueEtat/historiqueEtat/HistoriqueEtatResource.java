package app.domain.commun.historiqueEtat.historiqueEtat;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/historiqueEtat")
@Transactional
public class HistoriqueEtatResource {

    private final HistoriqueEtatRepository historiqueEtatRepository;

    public HistoriqueEtatResource(HistoriqueEtatRepository historiqueEtatRepository) {
        this.historiqueEtatRepository = historiqueEtatRepository;
    }
}