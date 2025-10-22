package oneteam.oneteamserver.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.schedule.Schedule;
import oneteam.oneteamserver.domain.schedule.dto.ScheduleCreateRequest;
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

    public List<ScheduleResponse> getSchedules(String userId) {
        List<Schedule> result = scheduleRepository.findAllVisibleToUser(userId);

        return result.stream()
                .map(ScheduleResponse::of)
                .toList();
    }

    @Transactional
    public ScheduleResponse addPersonalSchedule(ScheduleCreateRequest request, String userId) {
        Schedule schedule = Schedule.builder()
                .type("personal")
                .userId(userId)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .content(request.content())
                .build();

        Schedule saved = scheduleRepository.save(schedule);
        return ScheduleResponse.of(saved);
    }
}
