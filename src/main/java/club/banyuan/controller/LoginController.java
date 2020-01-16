package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping
    String showLoginHtml() {
        return "login";
    }

    @PostMapping
    String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) throws UnsupportedEncodingException {
        // 1. get form data(username password)
        // 2. dao -> username:password
        User user = userService.findUserByName(username);
        // 3. check
        if (user != null && password.equals(user.getPassword())) {
            // login success
            // 3.1 session
            session.setAttribute("CURRENT_USER", user);
            // 4. redirect
            return "redirect:/user/" + URLEncoder.encode(username, "UTF-8");
        }
        else {
            // login fail
        }
        return "";
    }
}
