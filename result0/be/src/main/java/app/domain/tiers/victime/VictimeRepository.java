package app.domain.tiers.victime;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictimeRepository extends JpaRepository<Victime, Long> {

    List<Victime> findAllBySinistre_IdOrderByNom(Long idSinistre);
}