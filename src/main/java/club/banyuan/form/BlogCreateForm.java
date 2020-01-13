package club.banyuan.form;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class BlogCreateForm implements Serializable {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 2, max = 30)
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Size(min = 1)
    private String content;

    public Blog toBlog(User author) {
        Blog blog = new Blog();
        blog.setTitle(this.title);
        blog.setContent(this.content);
        blog.setAuthor(author);

        return blog;
    }
}
