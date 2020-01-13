package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AdminController {

    public AdminController(BlogService blogService) {
        this.blogService = blogService;
    }

    private final BlogService blogService;


    @GetMapping("/admin")
    String getAdminList(
            @ModelAttribute("message") String message,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            User user,
            Model model
    ) {
        model.addAttribute(user);
        PageInfo blogs = blogService.findBlogsByUser(user, page.orElse(0), size.orElse(5));
        model.addAttribute("blogs", blogs);
        return "admin";
    }
}
