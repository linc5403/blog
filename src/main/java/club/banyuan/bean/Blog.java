package club.banyuan.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Blog implements Serializable {
    @NonNull
    private String title;
    @NonNull
    private String content;
    private Date createdTime;
    private int id;
    @NonNull
    private User author;
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                ", id=" + id +
                ", author=" + author.toString() +
                '}';
    }

    public String detailToString() {
        StringBuilder ret = new StringBuilder("Blog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                ", id=" + id +
                ", author=" + author.toString() +
                ", comments=");
        for (Comment c: comments) {
            ret.append(c.toString());
        }
        return ret.toString() + '}';
    }
}
