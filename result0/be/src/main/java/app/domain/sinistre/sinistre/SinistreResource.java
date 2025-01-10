package app.domain.sinistre.sinistre;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sinistre")
@Transactional
public class SinistreResource {

    private final SinistreRepository sinistreRepository;

    public SinistreResource(SinistreRepository sinistreRepository) {
        this.sinistreRepository = sinistreRepository;
    }
}