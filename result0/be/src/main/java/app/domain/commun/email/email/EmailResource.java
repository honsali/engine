package app.domain.commun.email.email;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@Transactional
public class EmailResource {

    private final EmailRepository emailRepository;

    public EmailResource(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }
}