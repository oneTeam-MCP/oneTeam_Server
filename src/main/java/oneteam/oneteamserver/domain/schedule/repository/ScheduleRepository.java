package oneteam.oneteamserver.domain.schedule.repository;

import oneteam.oneteamserver.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
