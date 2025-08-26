package oneteam.oneteamserver.global.auth.service;

import oneteam.oneteamserver.domain.member.dto.MemberResponse;

public interface AuthService {
    MemberResponse loginDtoByEmail(String email);
}
