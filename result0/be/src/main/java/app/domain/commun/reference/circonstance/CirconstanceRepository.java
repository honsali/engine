package app.domain.commun.reference.circonstance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CirconstanceRepository extends JpaRepository<Circonstance, Long> {
}