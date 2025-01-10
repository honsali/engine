package app.domain.sinistre.identification.identificationTribunal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationTribunalRepository extends JpaRepository<IdentificationTribunal, Long> {
}