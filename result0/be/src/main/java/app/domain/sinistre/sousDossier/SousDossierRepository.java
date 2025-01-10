package app.domain.sinistre.sousDossier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousDossierRepository extends JpaRepository<SousDossier, Long> {
}