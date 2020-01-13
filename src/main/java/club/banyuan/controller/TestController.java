package club.banyuan.controller;

import javax.json.Json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/{username}")
    String paraHandler (
            @PathVariable String username,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        String json = Json.createObjectBuilder()
                .add("name", username)
                .add("page", page)
                .add("size", size)
                .build().toString();
        return json;
    }
}
