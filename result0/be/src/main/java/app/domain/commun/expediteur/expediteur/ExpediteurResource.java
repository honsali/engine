package app.domain.commun.expediteur.expediteur;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expediteur")
@Transactional
public class ExpediteurResource {

    private final ExpediteurRepository expediteurRepository;

    public ExpediteurResource(ExpediteurRepository expediteurRepository) {
        this.expediteurRepository = expediteurRepository;
    }
}