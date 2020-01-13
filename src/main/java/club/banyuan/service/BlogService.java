package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.dao.BlogDao;
import club.banyuan.dao.CommentDao;
import club.banyuan.dao.UserDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final BlogDao blogDao;
    private final UserDao userDao;
    private final CommentDao commentDao;

    @Autowired
    public BlogService(BlogDao blogDao, UserDao userDao, CommentDao commentDao) {
        this.blogDao = blogDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    public Blog findBlog(int blogId) {
        return blogDao.findBlogById(blogId);
    }

    public List<Blog> findBlogsByKey(String key, Integer page, Integer size)
    {
        return null;
    }
    public PageInfo findBlogsByUser(User user, int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo(blogDao.findBlogsByUsername(user.getName()));
    }

    public void createBlog(Blog blog) {
        blogDao.addBlog(blog);
    }
}
