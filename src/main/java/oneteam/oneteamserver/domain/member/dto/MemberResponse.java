package oneteam.oneteamserver.domain.member.dto;

import oneteam.oneteamserver.domain.member.Member;

public record MemberResponse(
        Long userId,
        String email
) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getEmail());
    }
}
