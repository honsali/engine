package app.domain.sinistre.identification;

import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identification")
@Transactional
public class IdentificationResource {

    private final IdentificationRepository identificationRepository;

    public IdentificationResource(IdentificationRepository identificationRepository) {
        this.identificationRepository = identificationRepository;
    }

    @PostMapping("/chercher")
    public Page<Identification> chercher(@RequestBody Identification identification, @ParameterObject Pageable pageable) {
        return identificationRepository.chercher(identification, pageable);
    }
}