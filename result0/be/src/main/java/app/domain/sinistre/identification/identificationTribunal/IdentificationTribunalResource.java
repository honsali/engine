package app.domain.sinistre.identification.identificationTribunal;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identificationTribunal")
@Transactional
public class IdentificationTribunalResource {

    private final IdentificationTribunalRepository identificationTribunalRepository;

    public IdentificationTribunalResource(IdentificationTribunalRepository identificationTribunalRepository) {
        this.identificationTribunalRepository = identificationTribunalRepository;
    }
}