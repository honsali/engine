package app.domain.conception.transition;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitionRepository extends JpaRepository<Transition, Long> {

    List<Transition> findAllByQuestion_IdOrderByRaison(Long idQuestion);
}