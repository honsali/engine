package app.domain.sinistre.identification.identificationTiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationTiersRepository extends JpaRepository<IdentificationTiers, Long> {
}