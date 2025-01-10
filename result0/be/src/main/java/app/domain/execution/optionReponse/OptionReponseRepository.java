package app.domain.execution.optionReponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionReponseRepository extends JpaRepository<OptionReponse, Long> {
}