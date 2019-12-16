package projekti.controller;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import projekti.model.Album;
import projekti.service.AlbumService;

@Controller
public class AlbumController {

    public static final String BASEPATH = "/album";

    @Autowired
    private AlbumService albumService;
    
    @GetMapping(BASEPATH + "/user/{userSignature}")
    public String getUsersAlbum(Model model, @PathVariable Optional<String> userSignature) {
        if (!userSignature.isPresent()) {
            return "redirect:/";
        }
        
        Album album = albumService.findByAccount(userSignature.get());
        model.addAttribute("album", album);
        
        return "album/album";
    }
    
    @GetMapping(BASEPATH + "/{albumId}")
    public String getAlbum(Model model, @PathVariable Optional<Long> albumId) {
        if (!albumId.isPresent()) {
            return "redirect:/";
        }
        
        Album album = albumService.getOne(albumId.get());
        model.addAttribute("album", album);
        
        return "album/album";
    }
    
    @PostMapping(BASEPATH + "/{albumId}/newimage")
    public String create(@PathVariable Optional<Long> albumId,
                         @RequestParam("file") Optional<MultipartFile> file,
                         @RequestParam Optional<String> comment) throws IOException {
        if (!albumId.isPresent() || !file.isPresent() || !comment.isPresent()) {
            return "redirect:/";
        }
        
        Long albmId = albumId.get();
        albumService.saveImageToAlbum(albmId, file.get(), comment.get());
        
        return "redirect:" + BASEPATH + "/" + albmId;
    }
    
}
