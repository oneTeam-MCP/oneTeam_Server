package oneteam.oneteamserver.domain.notification.dto;

import oneteam.oneteamserver.domain.notification.Notification;
import oneteam.oneteamserver.global.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id, String userId, String title, String timeInfo, String content, String link,
        @DateTimeFormat LocalDateTime crawledAt, @DateTimeFormat LocalDateTime createdAt) {

        public static NotificationResponse of(Notification notification) {
            return new NotificationResponse(
                    notification.getId(), notification.getUserId(), notification.getTitle(), notification.getTimeInfo(),
                    notification.getContent(), notification.getLink(), notification.getCrawledAt(), notification.getCreatedAt()
            );
        }
}
