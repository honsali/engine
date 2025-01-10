package app.domain.conception.regleSuivi;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.domain.conception.question.Question;
import app.domain.conception.question.QuestionRepository;

@RestController
@RequestMapping("/api/regleSuivi")
@Transactional
public class RegleSuiviResource {

    private final RegleSuiviRepository regleSuiviRepository;
    private final QuestionRepository questionRepository;

    public RegleSuiviResource(RegleSuiviRepository regleSuiviRepository, QuestionRepository questionRepository) {
        this.regleSuiviRepository = regleSuiviRepository;
        this.questionRepository = questionRepository;
    }
}