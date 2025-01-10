package app.domain.execution.sessionInterview;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionInterviewRepository extends JpaRepository<SessionInterview, Long> {
}