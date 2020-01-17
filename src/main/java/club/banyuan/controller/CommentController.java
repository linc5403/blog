package club.banyuan.controller;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/blogs/{blogId}/comments")
    public String addCommentByGet(
            @PathVariable Integer blogId,
            HttpSession session,
            HttpServletRequest request) {
        User currentUser = (User)session.getAttribute("CURRENT_USER");
        String commentContent = (String)session.getAttribute("COMMENT_CONTENT");
        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setUserId(currentUser.getId());
        comment.setBlogId(blogId);
        commentService.addComment(comment);
        session.removeAttribute("COMMENT_CONTENT");
        return "redirect:/blogs/" + blogId;
    }

    @PostMapping("/blogs/{blogId}/comments")
    public String addComment(
                            @PathVariable Integer blogId,
                            Comment comment,
                            HttpSession session,
                            HttpServletRequest request
    ) {
        User currentUser = (User)session.getAttribute("CURRENT_USER");
//        if (currentUser != null) {
        comment.setUserId(currentUser.getId());
        comment.setBlogId(blogId);
        commentService.addComment(comment);
        return "redirect:/blogs/" + blogId;
//        }
/*        else {
            // 1. comment's content --> session
            session.setAttribute("COMMENT_CONTENT", comment.getContent());
            // 2. redirect next??
            return "redirect:/login?next=" + request.getRequestURI();
        }*/
    }
}
