package oneteam.oneteamserver.domain.member.service;

import oneteam.oneteamserver.domain.member.dto.MemberResponse;

/**
 * 회원 조회
 */
public interface MemberFinder {
    MemberResponse find(Long memberId);
    MemberResponse findByEmail(String email);
}
