package oneteam.oneteamserver.domain.assignment.repository;

import io.lettuce.core.dynamic.annotation.Param;
import oneteam.oneteamserver.domain.assignment.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("""
          SELECT a\s
           FROM Assignment a\s
           WHERE a.userId = :userId\s
             AND a.submission_status = :status
           ORDER BY a.createdAt DESC, a.id DESC
          \s""")
    List<Assignment> findMyUnsubmitted(
            @Param("userId") String userId,
            @Param("status") String status
    );
}