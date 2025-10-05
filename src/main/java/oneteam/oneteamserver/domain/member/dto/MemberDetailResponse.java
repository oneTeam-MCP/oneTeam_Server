package oneteam.oneteamserver.domain.member.dto;

import oneteam.oneteamserver.domain.member.Member;

public record MemberDetailResponse(
        Long id,
        String name,
        String email,
        String major
) {
    public static MemberDetailResponse of(Member member) {
        return new MemberDetailResponse(member.getId(), member.getName(), member.getEmail(), member.getMajor());
    }
}
