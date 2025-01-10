package app.domain.commun.reference.contrat;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contrat")
@Transactional
public class ContratResource {

    private final ContratRepository contratRepository;

    public ContratResource(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }
}