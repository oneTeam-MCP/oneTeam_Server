package oneteam.oneteamserver.domain.assignment.dto;

import oneteam.oneteamserver.domain.assignment.Assignment;
import oneteam.oneteamserver.global.annotation.DateTimeFormat;
import oneteam.oneteamserver.global.annotation.DateTimeFormatSpace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public record AssignmentResponse(
        Long id,
        String userId,
        String courseId,
        String courseName,
        String professor,
        String week,
        String assignmentName,
        @DateTimeFormatSpace LocalDateTime endDate,
        String submissionStatus,
        String grade,
        @DateTimeFormat LocalDateTime crawledAt,
        @DateTimeFormat LocalDateTime createdAt,
        Long daysLeft
) {
    private static final DateTimeFormatter DEADLINE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static AssignmentResponse of(Assignment assignment) {
        LocalDateTime endDateTime = parseEndDate(assignment.getEnd_date());
        Long daysLeft = calculateDaysLeft(endDateTime);

        return new AssignmentResponse(
                assignment.getId(),
                assignment.getUserId(),
                assignment.getCourse_id(),
                assignment.getCourse_name(),
                assignment.getProfessor(),
                assignment.getWeek(),
                assignment.getAssignment_name(),
                endDateTime,
                assignment.getSubmission_status(),
                assignment.getGrade(),
                assignment.getCrawledAt(),
                assignment.getCreatedAt(),
                daysLeft
        );
    }

    private static LocalDateTime parseEndDate(String endDateStr) {
        if (endDateStr == null || endDateStr.isBlank()) {
            return null;
        }
        try {
            return LocalDateTime.parse(endDateStr, DEADLINE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static Long calculateDaysLeft(LocalDateTime endDateTime) {
        if (endDateTime == null) {
            return null;
        }

        LocalDate today = LocalDate.now();
        LocalDate deadlineDate = endDateTime.toLocalDate();

        return ChronoUnit.DAYS.between(today, deadlineDate);
    }
}