package oneteam.oneteamserver.domain.member.service;

import oneteam.oneteamserver.domain.member.dto.MemberRegisterRequest;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;

public interface MemberRegister {
    MemberResponse register(MemberRegisterRequest registerRequest);
}
