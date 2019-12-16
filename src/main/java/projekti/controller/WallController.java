package projekti.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.service.MessageLikeService;
import projekti.service.MessageService;

@Controller
public class WallController {
    
    static final String BASEPATH = "/wall";
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private MessageLikeService messageLikeService;
    
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
    
}
