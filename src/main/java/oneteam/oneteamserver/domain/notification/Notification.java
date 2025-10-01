package oneteam.oneteamserver.domain.notification;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Immutable
@Table(name = "notifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    private String title;

    private String url;

    @Column(length = 50)
    private String type;

    @Column(length = 100)
    private String author;

    private LocalDate createdDate;

    private Long views;

    private String content;

    private LocalDateTime crawledAt;

    private LocalDateTime createdAt;
}
