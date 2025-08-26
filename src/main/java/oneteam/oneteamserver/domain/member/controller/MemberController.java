package oneteam.oneteamserver.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.dto.MemberRegisterRequest;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;
import oneteam.oneteamserver.domain.member.service.MemberFinder;
import oneteam.oneteamserver.domain.member.service.MemberRegister;
import oneteam.oneteamserver.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 API", description = "회원 관련 API 입니다.")
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberRegister memberRegister;
    private final MemberFinder memberFinder;

    @PostMapping
    @Operation(summary = "회원가입", description = "이메일과 비밀번호로 회원가입 합니다.")
    public SuccessResponse<MemberResponse> registerMember(
            @RequestBody @Valid MemberRegisterRequest registerRequest
    ) {
        MemberResponse response = memberRegister.register(registerRequest);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 단건 조회", description = "memberId로 회원 정보를 조회합니다.")
    public SuccessResponse<MemberResponse> findMember(
            @PathVariable Long memberId
    ) {
        MemberResponse response = memberFinder.find(memberId);
        return SuccessResponse.ok(response);
    }
}
