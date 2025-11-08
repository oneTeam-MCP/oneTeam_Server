package oneteam.oneteamserver.domain.schedule.repository;

import io.lettuce.core.dynamic.annotation.Param;
import oneteam.oneteamserver.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.type = 'common' OR s.userId = :userId ORDER BY s.startDate ASC")
    List<Schedule> findAllVisibleToUser(@Param("userId") String userId);

    Optional<Schedule> findByIdAndUserIdAndType(Long id, String userId, String type);
}
