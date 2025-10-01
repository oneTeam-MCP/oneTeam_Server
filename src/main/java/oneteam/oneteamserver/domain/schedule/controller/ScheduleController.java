package oneteam.oneteamserver.domain.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.schedule.dto.ScheduleResponse;
import oneteam.oneteamserver.domain.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "학사 일정 API", description = "학사 일정 관련 API 입니다.")
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    @Operation(summary = "학사 일정 전체 조회", description = "전체 학사 일정을 조회합니다.")
    public List<ScheduleResponse> getSchedules() {
        return scheduleService.getSchedules();
    }
}
