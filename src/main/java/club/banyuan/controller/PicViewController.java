package club.banyuan.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PicViewController {

    @GetMapping("/picView/{picName}")
    String showPic() {
        return "picView";
    }
}
