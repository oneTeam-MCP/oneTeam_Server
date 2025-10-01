package oneteam.oneteamserver.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.schedule.Schedule;
import oneteam.oneteamserver.domain.schedule.dto.ScheduleResponse;
import oneteam.oneteamserver.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResponse> getSchedules() {
        List<Schedule> result = scheduleRepository.findAll();

        return result.stream()
                .map(ScheduleResponse::of)
                .toList();
    }
}
