package club.banyuan.interceptor;

import club.banyuan.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 将comment的content放到session中
        Object content = request.getSession().getAttribute("content");
        logger.info("contentInterceptor get Session Attribute content: {}", content);
        if (content == null) {
            System.out.println("content is null");
            content = request.getParameter("content");
            request.getSession().setAttribute("content", content);
            logger.info("contentInterceptor get Parameter and set Attribute content: {}", content);
        }
        return true;
    }
}
