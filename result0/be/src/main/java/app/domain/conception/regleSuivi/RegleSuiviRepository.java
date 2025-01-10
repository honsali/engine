package app.domain.conception.regleSuivi;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegleSuiviRepository extends JpaRepository<RegleSuivi, Long> {
}