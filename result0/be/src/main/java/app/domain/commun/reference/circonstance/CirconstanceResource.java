package app.domain.commun.reference.circonstance;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/circonstance")
@Transactional
public class CirconstanceResource {

    private final CirconstanceRepository circonstanceRepository;

    public CirconstanceResource(CirconstanceRepository circonstanceRepository) {
        this.circonstanceRepository = circonstanceRepository;
    }
}