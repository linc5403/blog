package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blogs/{id}")
public class BlogController {

    private final BlogService blogService;
    private final CommentService commentService;

    @Autowired
    public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping
    public String get(@PathVariable("id") int id, Model model) {
        // Your Code goes here
        Blog blog = blogService.findBlog(id);
        List<Comment> comments = commentService.getCommentOfBlog(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "item";
    }
}
