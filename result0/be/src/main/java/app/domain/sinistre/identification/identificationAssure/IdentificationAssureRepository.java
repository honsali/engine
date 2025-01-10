package app.domain.sinistre.identification.identificationAssure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationAssureRepository extends JpaRepository<IdentificationAssure, Long> {
}