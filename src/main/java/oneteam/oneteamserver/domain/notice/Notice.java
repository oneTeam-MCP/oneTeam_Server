package oneteam.oneteamserver.domain.notice;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Entity
@Getter
@Immutable
@Table(name = "smu_notices")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String campus;

    private String category;

    private String title;

    private LocalDate date;

    @Column(name = "is_important")
    private Boolean isImportant;

    @Column(length = 1000)
    private String link;

    @Column(length = 1000)
    private String attachments;
}
