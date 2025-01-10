package app.domain.conception.option;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findAllByQuestion_IdOrderByPosition(Long idQuestion);
}