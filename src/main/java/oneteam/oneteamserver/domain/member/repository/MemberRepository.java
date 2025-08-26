package oneteam.oneteamserver.domain.member.repository;

import oneteam.oneteamserver.domain.member.Member;

public interface MemberRepository {
    void save(Member member);
    Member getById(Long memberId);
    Member getByEmail(String email);
    boolean existsByEmail(String email);
}
