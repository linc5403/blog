package club.banyuan.interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断访问的uri是否和session里面的用户名一致
        // 1. get URI
        String uri = request.getRequestURI();
        String uriSplit[] = uri.split("/");
        System.out.println(uriSplit);
        String uriUsername = URLDecoder.decode(uriSplit[2], "UTF-8");

        // 2. get session's username
        User user = (User)request.getSession().getAttribute("CURRENT_USER");
        String sessionUsername = user.getName();
        if (sessionUsername.equals(uriUsername)) {
            // 一致
            return true;
        }
        else {
            // 不一致
            response.sendRedirect("/admin/" + URLEncoder.encode(sessionUsername, "UTF-8"));
            return false;
        }
    }
}
