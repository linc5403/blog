package club.banyuan.controller;


import club.banyuan.bean.Blog;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {
    private final UserService userService;
    private final BlogService blogService;

    @Autowired
    public SearchController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @GetMapping("/search")
    public String search(@RequestParam("key") String key,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         Model model)
    {
        List<Blog> blogs = blogService.findBlogsByKey(key, page.orElse(0), size.orElse(10));
        model.addAttribute("blogs", blogs);
        // Your code goes here
        return "list";
    }
}
