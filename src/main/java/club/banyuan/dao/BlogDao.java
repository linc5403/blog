package club.banyuan.dao;

import club.banyuan.bean.Blog;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    Blog findBlogById(@Param("id") int id);
    Blog findBlogDetailById(@Param("id") int id);
    List<Blog> findBlogsByUsername(@Param("username") String username);
    List<Blog> findBlogsByUserId(@Param("userId") int userId);
    void addBlog(Blog blog);
}
