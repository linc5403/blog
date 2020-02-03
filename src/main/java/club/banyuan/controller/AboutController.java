package club.banyuan.controller;

import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class AboutController {
    @GetMapping("/about")
    String getAbout() {
        char a = 'a';
        char c = 'a' + 'b';
        System.out.println(c);
        return "about";
    }
}
