package projekti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageFileController {
    
    @PostMapping("/images")
    public String create(@RequestParam("file") MultipartFile file) {
        return null;
    }
}
