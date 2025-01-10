package app.domain.tiers.ayantDroit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AyantDroitRepository extends JpaRepository<AyantDroit, Long> {

    List<AyantDroit> findAllBySousDossier_IdOrderByNom(Long idSousDossier);
}