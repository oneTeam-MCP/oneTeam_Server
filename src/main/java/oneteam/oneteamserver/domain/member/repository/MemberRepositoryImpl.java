package oneteam.oneteamserver.domain.member.repository;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.global.exception.CustomException;
import oneteam.oneteamserver.global.exception.ErrorCode;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public Member getById(Long memberId) {
        return memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public Member getByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }
}
