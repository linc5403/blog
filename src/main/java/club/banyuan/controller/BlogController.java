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
    public String createBlog(@RequestParam String title, @RequestParam String content) {
    // public String createBlog(@RequestParam Blog blog) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        Integer userId = 1;
        blog.setUserId(userId);
        // blog -> mysql
        blogService.createBlog(blog);
        // return redirect /blogs/{blogId}
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
        // username - > user
        // userService -> getUserByUsername

/*        List<Blog> blogs = blogService.getPagedBlogsByUsername(username, page, size);
        // username -> List<Blog> -> blogs
        // blogService -> getBlogsByUsername
        boolean hasPre = (page == 1 ? false:true);
        List<Blog> nextPageBlogs = blogService.getPagedBlogsByUsername(username, page+1, size);
        boolean hasNext = (nextPageBlogs.size() == 0) ? false : true;

        model.addAttribute("blogs", blogs);
        model.addAttribute("user", user);
        model.addAttribute("hasPre", hasPre);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("page", page);*/
        PageInfo pageInfo = blogService.pageUserBlog(username, page, size);
        model.addAttribute("user", user);
        model.addAttribute("blogs", pageInfo);
        return "list";
    }
}
