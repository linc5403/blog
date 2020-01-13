package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    PageInfo getBlogsByUserName(Integer page, Integer size, String userName) {
        PageHelper.startPage(page, size);
        return new PageInfo(blogDao.selectBlogByUserName(userName));
    }
}
