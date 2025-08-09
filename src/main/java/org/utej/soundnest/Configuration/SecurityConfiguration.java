package org.utej.soundnest.Configuration;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    private final OnboardingRedirectFilter onboardingRedirectFilter;

    public SecurityConfiguration(OnboardingRedirectFilter onboardingRedirectFilter) {
        this.onboardingRedirectFilter = onboardingRedirectFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/home", "/profile", "/album/**", "/music/**").authenticated()
                        .requestMatchers("/onboarding").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2->oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/post-login", true)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/index.html").permitAll()
                );
        http.addFilterAfter(onboardingRedirectFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
