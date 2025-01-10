package app.domain.sinistre.tiers.tiers;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tiers")
@Transactional
public class TiersResource {

    private final TiersRepository tiersRepository;

    public TiersResource(TiersRepository tiersRepository) {
        this.tiersRepository = tiersRepository;
    }
}