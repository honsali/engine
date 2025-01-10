package app.domain.sinistre.identification.identificationSinistre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationSinistreRepository extends JpaRepository<IdentificationSinistre, Long> {
}