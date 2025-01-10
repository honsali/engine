package app.domain.commun.reference.reference;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reference")
@Transactional
public class ReferenceResource {

    private final ReferenceRepository referenceRepository;

    public ReferenceResource(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }
}