package projekti.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.service.ImageCommentService;
import projekti.service.ImageLikeService;
import projekti.service.ImageService;
import projekti.service.MessageCommentService;
import projekti.service.MessageLikeService;
import projekti.service.MessageService;

@Controller
public class WallController {
    
    public static final String BASEPATH = "/wall";
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private MessageLikeService messageLikeService;
    
    //@Autowired
    //private ImageCommentService imageCommentService;
    
    @Autowired
    private ImageLikeService imageLikeService;
    
    @Autowired
    private MessageCommentService messageCommentSerice;
    
    @PostMapping(BASEPATH + "/{messageId}/newComment")
    public String addComment(@PathVariable Optional<Long> messageId,
                             @RequestParam Optional<String> comment) {
        if (!messageId.isPresent() || !comment.isPresent()) {
            return "redirect:/";
        }
        Long msgId = messageId.get();
        
        messageCommentSerice.create(msgId, comment.get());
        
        return "redirect:" + BASEPATH + "/" + msgId;
    }
    
    @GetMapping(BASEPATH + "/{messageId}")
    public String getMessage(Model model, @PathVariable Optional<Long> messageId) {
        if (!messageId.isPresent()) {
            return "redirect:/";
        }
        Long msgId = messageId.get();
        model.addAttribute("message", messageService.getOne(messageId.get()));
        model.addAttribute("comments", messageCommentSerice.findByMessageSortedByCreationDate(msgId, 0, 10));
        
        return "messages/message";
    }
    
    @GetMapping(BASEPATH + "/image/{imageId}")
    public String getImage(Model model, @PathVariable Optional<Long> imageId) {
        if (!imageId.isPresent()) {
            return "redirect:/";
        }
        Long imgId = imageId.get();
        model.addAttribute("image", imageService.getOne(imageId.get()));
        //model.addAttribute("comments", imageCommentService.findByImageSortedByCreationDate(imgId, 0, 10));
        
        return "images/image";
    }
    
    @PostMapping(BASEPATH + "/like")
    public String likeMessage(@RequestParam Optional<Long> messageId) {
        if (messageId.isPresent()) {
            messageLikeService.addNewLike(messageId.get());
        }
        
        return "redirect:/";
    }
    
    @PostMapping(BASEPATH + "/newmess")
    public String createMessage(@RequestParam Optional<String> message) {
        if (message.isPresent()) {
            String messageStr = message.orElse("").trim();
            if (!messageStr.isEmpty()) {
                messageService.create(messageStr);
            }
        }
        
        return "redirect:/";
    }
    
    @PostMapping(BASEPATH + "/likeimage")
    public String likeImage(@RequestParam Optional<Long> imageId) {
        if (imageId.isPresent()) {
            imageLikeService.addNewLike(imageId.get());
        }
        return "redirect:/";
    }
    
}
