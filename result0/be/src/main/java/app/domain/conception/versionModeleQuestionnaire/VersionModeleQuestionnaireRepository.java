package app.domain.conception.versionModeleQuestionnaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionModeleQuestionnaireRepository extends JpaRepository<VersionModeleQuestionnaire, Long> {
}