package oneteam.oneteamserver.global.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.global.auth.dto.LoginRequest;
import oneteam.oneteamserver.global.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "인증 API", description = "로그인, 로그아웃 등 인증 관련 API 입니다.")
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인 후 JWT 토큰을 헤더에 담아 반환합니다.")
    public ResponseEntity<Void> login(
            @RequestBody @Valid LoginRequest request
    ) {
        return ResponseEntity.ok().build();
    }
}
