package app.domain.sinistre.sousDossier;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sousDossier")
@Transactional
public class SousDossierResource {

    private final SousDossierRepository sousDossierRepository;

    public SousDossierResource(SousDossierRepository sousDossierRepository) {
        this.sousDossierRepository = sousDossierRepository;
    }
}