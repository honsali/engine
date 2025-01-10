package app.domain.sinistre.sinistre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistreRepository extends JpaRepository<Sinistre, Long> {
}