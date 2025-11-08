package oneteam.oneteamserver.domain.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.schedule.dto.ScheduleCreateRequest;
import oneteam.oneteamserver.domain.schedule.dto.ScheduleResponse;
import oneteam.oneteamserver.domain.schedule.service.ScheduleService;
import oneteam.oneteamserver.global.annotation.CurrentMember;
import oneteam.oneteamserver.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "학사 일정 API", description = "학사 일정 관련 API 입니다.")
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    @Operation(summary = "학사 일정 전체 조회", description = "공통 + 개인 학사 일정을 조회합니다.")
    public List<ScheduleResponse> getSchedules(
            @CurrentMember Member member
    ) {
        return scheduleService.getSchedules(member.getStudentId());
    }

    @PostMapping
    @Operation(summary = "개인 일정 추가", description = "요청 유저의 개인 일정을 추가합니다.")
    public ScheduleResponse addPersonalSchedule(
            @RequestBody ScheduleCreateRequest request,
            @CurrentMember Member member
    ) {
        return scheduleService.addPersonalSchedule(request, member.getStudentId());
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "개인 일정 삭제", description = "요청 유저의 개인 일정을 삭제합니다.")
    public SuccessResponse<String> deletePersonalSchedule(
            @PathVariable Long scheduleId,
            @CurrentMember Member member
    ) {
        scheduleService.deletePersonalSchedule(member.getStudentId(), scheduleId);
        return SuccessResponse.ok("일정 삭제 성공");
    }
}
