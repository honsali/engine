package app.domain.conception.question;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByRubrique_IdOrderByPosition(Long idRubrique);
}