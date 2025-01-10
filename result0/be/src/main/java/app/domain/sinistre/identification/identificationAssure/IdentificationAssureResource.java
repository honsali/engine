package app.domain.sinistre.identification.identificationAssure;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identificationAssure")
@Transactional
public class IdentificationAssureResource {

    private final IdentificationAssureRepository identificationAssureRepository;

    public IdentificationAssureResource(IdentificationAssureRepository identificationAssureRepository) {
        this.identificationAssureRepository = identificationAssureRepository;
    }
}