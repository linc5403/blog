package club.banyuan.controller;

import club.banyuan.form.UserRegisterForm;
import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final BlogService blogService;

    @Autowired
    public RegisterController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @GetMapping
    public String get() {
        return "register";
    }

    @PostMapping
    public String post(@Valid UserRegisterForm userRegisterForm,
                       BindingResult result,
                       final RedirectAttributes redirectAttributes
    ) {
        //Your code goes here
        if (result.hasErrors())
            return "register";
        User user = userRegisterForm.toUser();
        System.out.println(user);
        userService.register(user);
        Blog blog = new Blog("aaaa", "aaaaaaaaa", user);
        blog.setAuthor(user);
        blogService.createBlog(blog);
        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/admin";
    }

}
