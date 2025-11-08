package oneteam.oneteamserver.domain.notification;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Getter
@Immutable
@Table(name = "ecampus_notifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    private String title;

    private String timeInfo;

    private String content;

    private String link;

    private LocalDateTime crawledAt;

    private LocalDateTime createdAt;
}
