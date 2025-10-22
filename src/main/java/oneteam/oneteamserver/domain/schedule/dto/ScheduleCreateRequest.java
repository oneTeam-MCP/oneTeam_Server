package oneteam.oneteamserver.domain.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ScheduleCreateRequest(
        @Schema(description = "시작 날짜", example = "2025-11-01")
        @NotNull LocalDate startDate,

        @Schema(description = "종료 날짜", example = "2025-11-05")
        @NotNull LocalDate endDate,

        @Schema(description = "일정 내용", example = "중간고사 준비")
        @NotEmpty String content
) {}
