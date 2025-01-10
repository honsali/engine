package app.domain.commun.documentGed.documentGed;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documentGed")
@Transactional
public class DocumentGedResource {

    private final DocumentGedRepository documentGedRepository;

    public DocumentGedResource(DocumentGedRepository documentGedRepository) {
        this.documentGedRepository = documentGedRepository;
    }
}