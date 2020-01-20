package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> getBlogsByUsername(String username) {
        return blogDao.selectBlogByUsername(username);
    }

    public List<Blog> getPagedBlogsByUsername(String username, Integer page, Integer size) {
        Integer offset = (page-1) * size;
        return blogDao.selectBlogByUsernameWithPageInfo(username, offset, size);
    }

    public PageInfo pageUserBlog(String username, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Blog> allBlogs = blogDao.selectBlogByUsername(username);
        return new PageInfo(allBlogs);
    }

    public Blog getBlogByBlogId(Integer blogId) {
        return blogDao.selectBlogById(blogId);
    }

    public void createBlog(Blog blog) {
        blogDao.insertBlog(blog);
    }

    public void deleteBlogByBlogId(Integer blogId) {
        blogDao.deleteBlogById(blogId);
    }

    public void updateBlog(Blog blog) {
        blogDao.updateBlog(blog);
    }
}
