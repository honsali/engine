package app.domain.sinistre.tiers.tiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiersRepository extends JpaRepository<Tiers, Long> {
}