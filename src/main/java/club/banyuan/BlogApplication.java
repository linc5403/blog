package club.banyuan;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.dao.BlogDao;
import club.banyuan.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan("club.banyuan")
public class BlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        UserDao userDao = context.getBean(UserDao.class);
        User user1 = userDao.selectUserByName("王二");
        System.out.println(userDao.selectUserByName("王二").toString());

        BlogDao blogDao = context.getBean(BlogDao.class);
        List<Blog> blogs = blogDao.selectBlogByUsername("aa");
        System.out.println(blogs.toString());
        Blog blog = blogDao.selectBlogById(11);
        blog.setContent("aaaaaaaa");
        blogDao.updateBlog(blog);

/*        Blog blog = new Blog();
        blog.setUserId(user1.getId());
        blog.setContent("contentzzzz");
        blog.setTitle("titlezzzz");
        blogDao.insertBlog(blog);
        System.out.println(blog);*/
    }
}
