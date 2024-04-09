package com.login.demo.oauth2;

import com.login.demo.service.Oauth2UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final Oauth2UserServiceImpl oauth2UserServiceImpl;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest)-> authorizeRequest
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll())
                    //.requestMatchers(new MvcRequestMatcher(introspector, "/user/**")).authenticated().anyRequest().permitAll())
                    .logout(logout-> logout.logoutSuccessUrl("/"))

                .oauth2Login((oauth2Login)->oauth2Login.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(oauth2UserServiceImpl)).defaultSuccessUrl("/google-login"))
    ;
            return http.build();
                        //        http
//                .authorizeHttpRequests(authz -> authz.anyRequest().authenticated()) // 인증된 사용자만 엔드포인트에 접근 가능
//                .formLogin(f -> f.defaultSuccessUrl("/community", true));  // 양식 기반 로그인 인증 방식을 사용하고, 인증 성공 시 /main 으로 이동
//        http.csrf(c -> c.ignoringRequestMatchers("/write")) // /world 경로는 CSRF 보호 제회
//                .authorizeHttpRequests(authz -> authz.anyRequest().permitAll());  // 그 외의 경로는 인증없이 모두 허용

    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable() // h2-console 화면 사용
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                // 로그인 할 때 사용할 파라미터
//                .and()
//                .logout()
//                .logoutSuccessUrl("/community")
//                .and()
//                .oauth2Login() // OAuth 2 로그인 설정 진입점
//                .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정
//                .userService(oauth2UserServiceImpl);
//    }
}
