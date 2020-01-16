package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/blogs/create")
    public String showCreatePage() {
        return "create";
    }

    @PostMapping(value = "/blogs")
    // public String createBlog(@RequestParam String title, @RequestParam String content) {
    public String createBlog(Blog blog) {
        Integer userId = 1;
        blog.setUserId(userId);
        System.out.println(blog);
        // blog -> mysql
        blogService.createBlog(blog);
        return "redirect:/blogs/" + blog.getId();
    }

    @GetMapping("/blogs/{blogId}")
    String getBlog(@PathVariable Integer blogId,
                    Model model) {
        //blogId --> blog
        Blog blog = blogService.getBlogByBlogId(blogId);
        // blogId --> comments
        List<Comment> comments = commentService.findBlogComments(blogId);
        //blog --> model
        model.addAttribute("blog", blog);
        //comments --> model
        model.addAttribute("comments", comments);
        return "item";
    }

    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username,
                        @RequestParam(required = false, defaultValue = "1") Integer page,
                        @RequestParam(required = false, defaultValue = "10") Integer size,
                        Model model
    ) {
        User user = userService.findUserByName(username);
        PageInfo pageInfo = blogService.pageUserBlog(username, page, size);
        model.addAttribute("user", user);
        model.addAttribute("blogs", pageInfo);
        return "list";
    }
}
