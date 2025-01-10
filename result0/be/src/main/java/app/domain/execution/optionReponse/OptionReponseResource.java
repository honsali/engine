package app.domain.execution.optionReponse;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/optionReponse")
@Transactional
public class OptionReponseResource {

    private final OptionReponseRepository optionReponseRepository;

    public OptionReponseResource(OptionReponseRepository optionReponseRepository) {
        this.optionReponseRepository = optionReponseRepository;
    }
}