package app.domain.commun.tache.nombreTacheParCategorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NombreTacheParCategorieRepository extends JpaRepository<NombreTacheParCategorie, Long> {
}