package oneteam.oneteamserver.domain.member.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.member.dto.MemberRegisterRequest;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;
import oneteam.oneteamserver.domain.member.repository.MemberRepository;
import oneteam.oneteamserver.global.exception.CustomException;
import oneteam.oneteamserver.global.exception.ErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberModifyService implements MemberRegister {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public MemberResponse register(MemberRegisterRequest registerRequest) {
        checkDuplicateEmail(registerRequest);

        Member member = Member.register(registerRequest, passwordEncoder);
        memberRepository.save(member);

        return MemberResponse.of(member);
    }

    private void checkDuplicateEmail(MemberRegisterRequest registerRequest) {
        if (memberRepository.existsByEmail(registerRequest.email())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }
    }
}
