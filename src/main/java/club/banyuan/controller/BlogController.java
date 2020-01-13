package club.banyuan.controller;

import club.banyuan.bean.User;
import com.github.pagehelper.PageInfo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username,
                        @RequestParam(required = false, defaultValue = "1") Integer page,
                        @RequestParam(required = false, defaultValue = "10") Integer size,
                        Model model) {
        PageInfo pageInfo = blogService.getBlogsByUserName(page, size, username);
        User user = userService.getUserByName(username);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("user", user);
        return "list";
    }
}
