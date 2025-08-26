package oneteam.oneteamserver.global.auth.config;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.global.auth.jwt.JwtFilter;
import oneteam.oneteamserver.global.auth.jwt.JwtUtil;
import oneteam.oneteamserver.global.auth.jwt.LoginFilter;
import oneteam.oneteamserver.global.auth.service.AuthService;
import oneteam.oneteamserver.global.redis.RefreshTokenCacheUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String[] ALLOWED_URLS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/actuator/health",
            "/",
            "/api/v1/auth/**",
            "/api/v1/members/**",
    };

    private final RefreshTokenCacheUtil refreshTokenCacheUtil;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            UserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider provider) {
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DaoAuthenticationProvider provider,
                                           AuthenticationManager authManager) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(provider)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(ALLOWED_URLS).permitAll()
                    .anyRequest().authenticated()
            );

        LoginFilter loginFilter = new LoginFilter(authManager, refreshTokenCacheUtil, jwtUtil, authService);
        loginFilter.setFilterProcessesUrl("/api/v1/auth/login");

        // JWTFilter 등록
        http.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);

        // 필터 추가 (UsernamePasswordAuthenticationFilter 전에 loginFilter 실행)
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
