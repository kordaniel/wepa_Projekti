package projekti.controller;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import projekti.model.FileObject;
import projekti.service.ImageObjectService;

@Controller
public class ImageFileController {
    
    public static final String BASEPATH = "/images";
    
    @Autowired
    ImageObjectService imageObjectService;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("images", imageObjectService.findAll());
        return "images";
    }
    
    @PostMapping("/images")
    public String create(@RequestParam("file") MultipartFile file) throws IOException {
        imageObjectService.save(file);
        return "redirect:/images";
    }
    
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> viewImage(@PathVariable Long id) {
        FileObject fo = imageObjectService.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        
        if (fo != null) {
            headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
            headers.setContentLength(fo.getSize());
            //headers.add("Content-Disposition", "attachment; filename=" + fo.getName());
        } else {
            fo = new FileObject();
        }
        
        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }
    
    @PostMapping("/images/delete/{id}")
    public String deleteImage(@PathVariable Optional<Long> id,
                              @RequestParam Optional<Long> albumId) {
        if (id.isPresent()) {
            imageObjectService.deleteImage(id.get());
        }
        if (!albumId.isPresent()) {
            return "redirect:/";
        }
        
        return "redirect:/album/" + albumId.get();
    }
}
