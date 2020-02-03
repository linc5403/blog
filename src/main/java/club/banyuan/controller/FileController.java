package club.banyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    @GetMapping("/upload")
    String showUploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    String doFileUpload(@RequestParam MultipartFile file,
                        @RequestParam String name) throws IOException {
        System.out.println("parameter name is " + name);
        // 用户文件的文件名
        Files.copy(file.getInputStream(), Paths.get("/Users/banyuan/pic/" + file.getOriginalFilename()));
        return "redirect:/picView";
    }
}
