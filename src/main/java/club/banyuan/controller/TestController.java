package club.banyuan.controller;

import club.banyuan.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @PostMapping("/test")
    @ResponseBody
    User get_test(User user) {
        return user;
    }
}
