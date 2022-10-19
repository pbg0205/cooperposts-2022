package com.cooper.cooperposts2022.common.config;

import com.cooper.cooperposts2022.common.provider.UsernamePasswordAuthenticationProvider;
import com.cooper.cooperposts2022.users.service.JpaUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    /**
     *  - Spring Security without the WebSecurityConfigurerAdapter
     *      1. https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authorize ->
                        authorize.antMatchers("/api/v1/users/**", "/h2-console/**").permitAll()
                                .antMatchers("/api/v1/posts/**").authenticated());

        http.headers().frameOptions().disable();
        http.httpBasic();
        http.authenticationProvider(usernamePasswordAuthenticationProvider);

        return http.build();
    }

}
