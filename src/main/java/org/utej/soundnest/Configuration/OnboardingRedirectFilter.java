package org.utej.soundnest.Configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.utej.soundnest.Model.User;
import org.utej.soundnest.Repository.UserRepository;

import java.io.IOException;

@Component
public class OnboardingRedirectFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    public OnboardingRedirectFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal())) {

            String email = authentication.getName(); // OAuth2 email
            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null && !user.isOnboarded()) {
                String path = request.getRequestURI();
                if (!path.equals("/onboarding") && !path.startsWith("/css") && !path.startsWith("/js")) {
                    response.sendRedirect("/onboarding");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
