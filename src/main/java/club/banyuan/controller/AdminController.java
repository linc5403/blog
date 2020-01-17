package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/admin/{username}")
    public String showAdminHtml(
            @PathVariable String username,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            Model model) {
        PageInfo blogs = blogService.pageUserBlog(username, page.orElse(1), size.orElse(10));
        model.addAttribute("blogs", blogs);
        model.addAttribute("username", username);
        return "admin";
    }
}
