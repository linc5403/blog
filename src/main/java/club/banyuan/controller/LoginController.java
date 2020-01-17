package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping
    String showLoginHtml(@RequestParam(required = false) String next,
                         HttpSession session) {
        if (next != null) {
            session.setAttribute("NEXT_URI", next);
        }
        return "login";
    }

    @PostMapping
    void login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) throws IOException {
        // 1. get form data(username password)
        // 2. dao -> username:password
        User user = userService.findUserByName(username);
        // 3. check
        if (user != null && password.equals(user.getPassword())) {
            // login success
            // 3.1 session
            session.setAttribute("CURRENT_USER", user);
            // 4. redirect
            // 4.1 如果带有next参数，重定向到next
            String nextUri = (String)session.getAttribute("NEXT_URI");
            if (nextUri != null) {
                String redirectUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + nextUri;
                response.setHeader("Location", redirectUrl);
                response.setStatus(302);
//                return "redirect:" + URLEncoder.encode(redirectUrl, "UTF-8");
            }
            // 4.2 不带next表示直接访问的login，那么跳转到user的controller
            response.sendRedirect("/user/" + URLEncoder.encode(username, "UTF-8"));
//            return "redirect:/user/" + URLEncoder.encode(username, "UTF-8");
            // return "redirect:" + URLEncoder.encode("/user/" + username, "UTF-8");
        }
        else {
            // login fail
        }
    }
}
