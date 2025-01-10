package app.domain.commun.utilisateur.utilisateur;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilisateur")
@Transactional
public class UtilisateurResource {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurResource(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
}