package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.dao.CommentDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> findBlogComments(Integer blogId) {
        return commentDao.selectCommentByBlogId(blogId);
    }

}
