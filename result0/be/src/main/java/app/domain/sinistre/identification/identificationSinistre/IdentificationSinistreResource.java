package app.domain.sinistre.identification.identificationSinistre;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identificationSinistre")
@Transactional
public class IdentificationSinistreResource {

    private final IdentificationSinistreRepository identificationSinistreRepository;

    public IdentificationSinistreResource(IdentificationSinistreRepository identificationSinistreRepository) {
        this.identificationSinistreRepository = identificationSinistreRepository;
    }
}