package projekti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import static projekti.MyApplication.ANON_USERNAME;
import projekti.auth.service.AccountService;
import projekti.service.ImageObjectService;
import projekti.service.MessageService;

@Controller
public class DefaultController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ImageObjectService imageObjectService;
    
    @GetMapping({"/", "/welcome"})
    public String helloWorld(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String authorizedUsername = securityContext.getAuthentication().getName();
        
        if (!ANON_USERNAME.equals(authorizedUsername)) {
            model.addAttribute("account", accountService.findByUsername(authorizedUsername));
            model.addAttribute("messages", messageService.findByAccountUsernameFollowing(authorizedUsername, 0, 25));
            model.addAttribute("images", imageObjectService.findByAccountUsernameFollowingSortedByCreationDate(authorizedUsername, 0, 10));
        }
        
        return "index";
    }
    
    @GetMapping("*")
    public String redirect() {
        return "redirect:/";
    }
}
