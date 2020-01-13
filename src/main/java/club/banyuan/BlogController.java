package club.banyuan;

import club.banyuan.bean.PageInfo;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Parameter;

@Controller
@ResponseBody
public class BlogController {
    @GetMapping("/user/{username}")
    PageInfo getUserBlogs(@PathVariable String username,
                          @RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        var pageInfo = new PageInfo();
        pageInfo.setPage(page);
        pageInfo.setSize(size);
        return pageInfo;
    }
}
