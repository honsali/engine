package app.domain.tiers.entiteAdverse;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntiteAdverseRepository extends JpaRepository<EntiteAdverse, Long> {

    List<EntiteAdverse> findAllBySinistre_IdOrderByNom(Long idSinistre);
}