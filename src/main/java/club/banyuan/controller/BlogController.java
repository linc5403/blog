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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
/*    public String showCreatePage(HttpSession session,
                                 HttpServletRequest request) {*/
        // 1. 判断用户是否已经登陆
        /*User currentUser = (User)session.getAttribute("CURRENT_USER");
        if (currentUser != null) {
            // 展示createHtml
            return "create";
        }
        else {
            // 跳转到login
            String currentUri = request.getRequestURI();
            return "redirect:/login?next=" + currentUri;
            // redirect:/login?next=/blogs/create
        }
    }*/

    @PostMapping(value = "/blogs")
    // public String createBlog(@RequestParam String title, @RequestParam String content) {
    public String createBlog(Blog blog,
                             HttpSession session) {
        User currentUser = (User) session.getAttribute("CURRENT_USER");
        blog.setUserId(currentUser.getId());
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

    @DeleteMapping("/blog/{blogId}")
    String deleteBlog(@PathVariable Integer blogId,
                      HttpSession session,
                      Model model) throws UnsupportedEncodingException {
        blogService.deleteBlogByBlogId(blogId);
        String username = ((User)session.getAttribute("CURRENT_USER")).getName();
        PageInfo blogs = blogService.pageUserBlog(username, 1, 10);
        model.addAttribute("blogs", blogs);
        model.addAttribute("username", username);
        return "redirect:/admin/" + URLEncoder.encode(username, "UTF-8");
    }

    // 返回edit视图
    @GetMapping("/blogs/{blogId}/edit")
    String showEditHtml(@PathVariable Integer blogId, Model model) {
        // get blog by id
        Blog blog = blogService.getBlogByBlogId(blogId);
        model.addAttribute(blog);
        return "edit";
    }

    // 处理put请求的controller（完全更新一篇blog）
    @PutMapping("/blogs/{blogId}/edit")
    String editBlog(@PathVariable Integer blogId,
                    Blog blog) {
        blog.setId(blogId);
        blogService.updateBlog(blog);
        return "redirect:/blogs/" + blogId;
    }
}
