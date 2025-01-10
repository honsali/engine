package app.domain.conception.rubrique;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubriqueRepository extends JpaRepository<Rubrique, Long> {

    List<Rubrique> findAllByVersionModeleQuestionnaire_IdOrderByPosition(Long idVersionModeleQuestionnaire);
}