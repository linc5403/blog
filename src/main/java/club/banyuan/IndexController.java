package club.banyuan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class IndexController {
    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username) {
        return username;
    }
}
