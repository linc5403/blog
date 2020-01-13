package club.banyuan.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class DefaultLoginHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequest == null)
        {
            //直接访问的是login
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            httpServletResponse.sendRedirect("/users/" + URLEncoder.encode(username, "UTF-8"));
        }
        else
        {
            //跳转到之前到页面
            String queryString = savedRequest.getQueryString();
            String redirectUrl = savedRequest.getRequestURL();
            if (queryString != null && queryString.length()>0) {
                redirectUrl += "?" + queryString;
            }
            httpServletResponse.sendRedirect(redirectUrl);
        }
    }
}
