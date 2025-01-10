package app.domain.sinistre.identification.identificationContrat;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identificationContrat")
@Transactional
public class IdentificationContratResource {

    private final IdentificationContratRepository identificationContratRepository;

    public IdentificationContratResource(IdentificationContratRepository identificationContratRepository) {
        this.identificationContratRepository = identificationContratRepository;
    }
}