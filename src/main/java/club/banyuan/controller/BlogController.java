package club.banyuan.controller;

import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class BlogController {
    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username,
                          @RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        return "list";
    }
}
