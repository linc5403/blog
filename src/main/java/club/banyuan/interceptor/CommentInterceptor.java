package club.banyuan.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommentInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("CURRENT_USER") == null)
        {
            // 判断用户未登录时保存content信息到session
            String commentContent = request.getParameter("content");
            session.setAttribute("COMMENT_CONTENT", commentContent);
        }
        return true;
    }
}
