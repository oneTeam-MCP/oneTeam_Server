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
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false)
    private String major;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    public static Member register(MemberRegisterRequest registerRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = requireNonNull(registerRequest.email());
        member.password = requireNonNull(passwordEncoder.encode(registerRequest.password()));
        member.name = requireNonNull(registerRequest.name());
        member.major = requireNonNull(registerRequest.major());
        member.phoneNumber = requireNonNull(registerRequest.phoneNumber());

        return member;
    }
}
