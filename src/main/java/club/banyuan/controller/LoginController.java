package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String get(@RequestParam("next") Optional<String> next,
                      User user, HttpSession session
                      ) {
        return "login";
    }

    @PostMapping
    public String post(@RequestParam("next") Optional<String> next,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password,
                       HttpSession session) throws UnsupportedEncodingException {
        User user = userService.login(email, password);
        // TODO
        //if (user != null)
        session.setAttribute("CURRENT_USER", user);
        return "redirect:".concat(next.orElse("/users/" +
                URLEncoder.encode("".concat(user.getName()), "UTF-8")));
    }
}
