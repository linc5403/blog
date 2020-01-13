package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class IndexController {

    private final BlogService blogService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IndexController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    public String getByPage(@PathVariable("username") String username,
                            @RequestParam Optional<Integer> page,
                            @RequestParam Optional<Integer> size,
                            Model model) {
        // Your Code goes here
        // 渲染模板list.html
        User user = userService.findByName(username);
        PageInfo pageInfo = blogService.findBlogsByUser(user, page.orElse(1), size.orElse(5));
        logger.info("{}", pageInfo);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute(user);
        return "list";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
