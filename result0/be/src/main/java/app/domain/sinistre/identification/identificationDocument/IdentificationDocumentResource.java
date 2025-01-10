package app.domain.sinistre.identification.identificationDocument;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identificationDocument")
@Transactional
public class IdentificationDocumentResource {

    private final IdentificationDocumentRepository identificationDocumentRepository;

    public IdentificationDocumentResource(IdentificationDocumentRepository identificationDocumentRepository) {
        this.identificationDocumentRepository = identificationDocumentRepository;
    }
}