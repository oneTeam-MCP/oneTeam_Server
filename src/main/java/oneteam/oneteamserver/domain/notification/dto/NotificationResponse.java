package oneteam.oneteamserver.domain.notification.dto;

import oneteam.oneteamserver.domain.notification.Notification;
import oneteam.oneteamserver.global.annotation.DateFormat;
import oneteam.oneteamserver.global.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NotificationResponse(
        Long id, String userId, String title, String url, String type, String author,
        @DateFormat LocalDate createdDate, Long views, String content,
        @DateTimeFormat LocalDateTime crawledAt, @DateTimeFormat LocalDateTime createdAt
) {
        public static NotificationResponse of(Notification notification) {
            return new NotificationResponse(
                    notification.getId(), notification.getUserId(), notification.getTitle(), notification.getUrl(),
                    notification.getType(),  notification.getAuthor(), notification.getCreatedDate(), notification.getViews(),
                    notification.getContent(), notification.getCrawledAt(), notification.getCreatedAt()
            );
        }
}
