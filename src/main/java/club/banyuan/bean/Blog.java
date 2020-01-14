package club.banyuan.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Date createdTime;
}
