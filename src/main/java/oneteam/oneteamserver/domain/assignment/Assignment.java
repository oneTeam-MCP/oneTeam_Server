package oneteam.oneteamserver.domain.assignment;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Getter
@Immutable
@Table(name = "ecampus_assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String userId;

    @Column(length = 50, nullable = false)
    private String course_id;

    private String course_name;

    private String professor;

    @Column(length = 50)
    private String week;

    private String assignment_name;

    @Column(length = 100)
    private String end_date;

    @Column(length = 100)
    private String submission_status;

    @Column(length = 100)
    private String grade;

    private LocalDateTime crawledAt;

    private LocalDateTime createdAt;
}
