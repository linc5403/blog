package club.banyuan.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断session里面有没有用户
        HttpSession session = request.getSession();
        if (session.getAttribute("CURRENT_USER") != null) {
            // 如果有-> return true
            return true;
        }
        else {
            // 如果没有-> 修改response， return false
            String currentUri = request.getRequestURI();
            String redirectUrl = "/login?next=" + currentUri;
            response.sendRedirect(redirectUrl);
            return false;
        }
    }
}
