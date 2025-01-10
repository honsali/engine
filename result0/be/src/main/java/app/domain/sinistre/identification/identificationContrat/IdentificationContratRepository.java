package app.domain.sinistre.identification.identificationContrat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationContratRepository extends JpaRepository<IdentificationContrat, Long> {
}