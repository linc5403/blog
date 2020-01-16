package club.banyuan.controller;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/blogs/{blogId}/comments")
    public String addComment(
            @PathVariable Integer blogId,
            Comment comment,
            HttpSession session
    ) {
        User currentUser = (User)session.getAttribute("CURRENT_USER");
        if (currentUser != null) {
            comment.setUserId(currentUser.getId());
            comment.setBlogId(blogId);
            commentService.addComment(comment);
            return "redirect:/blogs/" + blogId;
        }
        else {
            return "redirect:/login";
        }
    }
}
