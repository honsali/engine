package app.domain.commun.documentGed.documentGed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentGedRepository extends JpaRepository<DocumentGed, Long> {
}