package app.domain.commun.expediteur.expediteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpediteurRepository extends JpaRepository<Expediteur, Long> {
}