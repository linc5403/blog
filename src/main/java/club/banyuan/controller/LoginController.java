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
    String login(
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
            // nextUri -> /admin/王二
            if (nextUri != null) {
                String [] uriSplit = nextUri.split("/");
                String redirectUrl = "";
                for (String i: uriSplit) {
                    if (i.length() > 0)
                        redirectUrl += "/" + URLEncoder.encode(i, "UTF-8");
                }
                session.removeAttribute("NEXT_URI");
                return "redirect:" + redirectUrl;

            }
            else {
                // 4.2 不带next表示直接访问的login，那么跳转到user的controller
                return "redirect:/user/" + URLEncoder.encode(username, "UTF-8");
            }
        }
        else {
            // login fail
            return "";
        }
    }
}
