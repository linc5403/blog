package club.banyuan;

import club.banyuan.dao.BlogDao;
import club.banyuan.dao.UserDao;
import lombok.var;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("club.banyuan.dao")
public class BlogApplication {
    public static void main(String[] args) {
        var logger = LoggerFactory.getLogger(BlogApplication.class);
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        var userDao = context.getBean(UserDao.class);
        var user1 = userDao.selectUserByName("aa");
        logger.info("{}", user1);
        var blogDao = context.getBean(BlogDao.class);
        var blogs = blogDao.selectBlogByUserName("aa");
        logger.info("{}", blogs);
    }
}
