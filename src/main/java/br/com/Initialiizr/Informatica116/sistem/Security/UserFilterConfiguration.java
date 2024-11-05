package br.com.Initialiizr.Informatica116.sistem.Security;

import br.com.Initialiizr.Informatica116.sistem.Service.UserActivityService;
import jakarta.servlet.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
@Configuration
public class UserFilterConfiguration implements Filter {
    private final UserActivityService userActivityService;

    public UserFilterConfiguration(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("meu nome e"+username);
            userActivityService.updateLastActivity(username);
        }

        chain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
