package seminar10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seminar10.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByUserId(Long userId);
}
