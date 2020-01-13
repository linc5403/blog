package club.banyuan.dao;

import club.banyuan.bean.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> finCommentsByBlogId(@Param("blogId") int blogId);
}
