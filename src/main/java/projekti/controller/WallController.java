package projekti.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import projekti.auth.service.AccountService;
import projekti.model.Message;
import projekti.service.MessageService;

@Controller
public class WallController {
    
    static final String BASEPATH = "/wall";
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    MessageService messageService;
    
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
    
    @GetMapping(BASEPATH)
    @ResponseBody
    public String listaa() {
        //List<Message> messages = messageService.findAll();
        List<Message> messages = accountService.findBySignature("danielko").getMessages();
        String palautus = "";
        for (Message message : messages) {
            palautus += message.getCreateDateTime() + "\n"
                    + message.getContent() + "\n"
                    + message.getAccount().getSignature() + "\n"
                    + "--------------------------------------\n";
        }
        return palautus;
    }
}
