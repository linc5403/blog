package club.banyuan.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    private int id;
    private Date createdTime;
    private int userId;
    private int blogId;
    private String content;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                '}';
    }

    public void setCommenter(User user) {
    }

    public void setBlog(Blog blog) {
    }

}
