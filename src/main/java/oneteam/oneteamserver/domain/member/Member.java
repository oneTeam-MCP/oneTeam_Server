package oneteam.oneteamserver.domain.member;

import jakarta.persistence.*;
import lombok.*;
import oneteam.oneteamserver.domain.member.dto.MemberRegisterRequest;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@Builder
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @NaturalId
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public static Member register(MemberRegisterRequest registerRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = requireNonNull(registerRequest.email());
        member.password = requireNonNull(passwordEncoder.encode(registerRequest.password()));

        return member;
    }
}
