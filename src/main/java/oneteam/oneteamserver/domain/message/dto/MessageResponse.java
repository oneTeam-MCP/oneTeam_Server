package oneteam.oneteamserver.domain.message.dto;

import oneteam.oneteamserver.domain.message.Message;
import oneteam.oneteamserver.global.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record MessageResponse(
        Long id,
        String userId,
        String sender,
        String sendTime,
        String title,
        String content,
        @DateTimeFormat LocalDateTime crawledAt,
        @DateTimeFormat LocalDateTime createdAt
) {
    public static MessageResponse of(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getUserId(),
                message.getSender(),
                message.getSendTime(),
                message.getTitle(),
                message.getContent(),
                message.getCrawledAt(),
                message.getCreatedAt()
        );
    }
}
