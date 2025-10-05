package oneteam.oneteamserver.domain.member.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.member.dto.MemberDetailResponse;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;
import oneteam.oneteamserver.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse find(Long memberId) {
        Member member = memberRepository.getById(memberId);
        return MemberResponse.of(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.getByEmail(email);
    }

    @Override
    public MemberDetailResponse findById(Long memberId) {
        Member member = memberRepository.getById(memberId);
        return MemberDetailResponse.of(member);
    }
}
