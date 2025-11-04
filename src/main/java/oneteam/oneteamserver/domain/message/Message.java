package oneteam.oneteamserver.domain.message;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Getter
@Immutable
@Table(name = "ecampus_messages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    @Column(length = 100)
    private String sender;

    @Column(length = 50)
    private String sendTime;

    private String title;

    @Column(length = 500)
    private String content;

    private LocalDateTime crawledAt;

    private LocalDateTime createdAt;
}
