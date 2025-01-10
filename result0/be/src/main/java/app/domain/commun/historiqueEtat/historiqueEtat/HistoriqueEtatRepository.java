package app.domain.commun.historiqueEtat.historiqueEtat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueEtatRepository extends JpaRepository<HistoriqueEtat, Long> {
}