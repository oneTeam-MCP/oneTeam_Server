package oneteam.oneteamserver.global.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.dto.MemberResponse;
import oneteam.oneteamserver.global.auth.dto.CustomUserDetails;
import oneteam.oneteamserver.global.auth.dto.LoginRequest;
import oneteam.oneteamserver.global.auth.service.AuthService;
import oneteam.oneteamserver.global.exception.ErrorCode;
import oneteam.oneteamserver.global.redis.RefreshTokenCacheUtil;
import oneteam.oneteamserver.global.response.ErrorResponse;
import oneteam.oneteamserver.global.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenCacheUtil refreshTokenCacheUtil;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("email");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            String email = loginRequest.email();
            String password = loginRequest.password();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)  throws IOException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        // Spring Security의 인터페이스에 맞게 username 으로 전달
        String email = customUserDetails.getUsername();

        String accessToken = jwtUtil.createAccessToken(email);
        String refreshToken = jwtUtil.createRefreshToken(email);

        // Redis에 Refresh Token 저장
        Duration refreshTokenTTL = Duration.ofDays(7);
        refreshTokenCacheUtil.saveRefreshToken(email, refreshToken, refreshTokenTTL);

        // 응답 헤더에 AccessToken + RefreshToken 추가
        response.addHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh-Token", refreshToken);

        response.setContentType("application/json;charset=UTF-8");

        MemberResponse loginDto = authService.loginDtoByEmail(email);

        new ObjectMapper().writeValue(response.getWriter(), SuccessResponse.ok(loginDto));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();

        ErrorCode errorCode;
        HttpStatus status;
        if (failed instanceof UsernameNotFoundException) {
            errorCode = ErrorCode.USER_EMAIL_NOT_EXIST;
            status = HttpStatus.NOT_FOUND;
        } else if (failed instanceof BadCredentialsException) {
            errorCode = ErrorCode.USER_WRONG_PASSWORD;
            status = HttpStatus.UNAUTHORIZED;
        } else {
            errorCode = ErrorCode.UNAUTHORIZED;
            status = HttpStatus.UNAUTHORIZED;
        }

        response.setStatus(status.value());
        ErrorResponse<Object> payload = ErrorResponse.of(
                errorCode.getErrorCode(),
                errorCode.getMessage()
        );
        response.getWriter().write(objectMapper.writeValueAsString(payload));
    }
}
