package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.dao.CommentDao;
import club.banyuan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    public CommentService(UserDao userDao, CommentDao commentDao) {
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    private final UserDao userDao;
    private final CommentDao commentDao;


    public List<Comment> getCommentOfBlog(Blog blog) {
        return commentDao.finCommentsByBlogId(blog.getId());
    }
    public void createComment(Comment comment) {

    }
}
