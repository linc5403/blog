package club.banyuan.controller;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CommentService commentService;
    private final BlogService blogService;

    @Autowired
    public CommentController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @RequestMapping(value = "/blogs/{id}/comments", method = {RequestMethod.GET, RequestMethod.POST})
    String post(@PathVariable int id,
                User user,
                HttpSession session
                ) {
        Comment c = new Comment();
        c.setContent((String)session.getAttribute("content"));
        session.removeAttribute("content");
        logger.info("to create comment {}", c);
        c.setCommenter(user);
        c.setBlog(blogService.findBlog(id));
        commentService.createComment(c);
        return "redirect:/blogs/" + id;
    }
}
