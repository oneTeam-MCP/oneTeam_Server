package oneteam.oneteamserver.domain.member.service;

import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.member.dto.MemberDetailResponse;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;

/**
 * 회원 조회
 */
public interface MemberFinder {
    MemberResponse find(Long memberId);
    Member findByEmail(String email);
    MemberDetailResponse findById(Long memberId);
}
