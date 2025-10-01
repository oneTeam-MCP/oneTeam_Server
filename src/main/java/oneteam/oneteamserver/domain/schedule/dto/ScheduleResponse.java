package oneteam.oneteamserver.domain.schedule.dto;

import oneteam.oneteamserver.domain.schedule.Schedule;
import oneteam.oneteamserver.global.annotation.DateFormat;
import oneteam.oneteamserver.global.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleResponse(
        Long id, @DateFormat LocalDate startDate, @DateFormat LocalDate endDate,
        String content, @DateTimeFormat LocalDateTime createdAt
) {
    public static ScheduleResponse of(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(), schedule.getStartDate(), schedule.getEndDate(), schedule.getContent(), schedule.getCreatedAt()
        );
    }
}
