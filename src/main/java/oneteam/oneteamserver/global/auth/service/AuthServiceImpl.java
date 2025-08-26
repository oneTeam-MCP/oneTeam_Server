package oneteam.oneteamserver.global.auth.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;
import oneteam.oneteamserver.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    /**
     * 이메일로 사용자 정보를 조회하고, 로그인 응답용 DTO로 변환하여 반환한다.
     * @param email 로그인할 사용자의 이메일
     */
    @Override
    public MemberResponse loginDtoByEmail(String email) {
        Member member = memberRepository.getByEmail(email);
        return MemberResponse.of(member);
    }
}
