package projekti.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.auth.model.Account;
import projekti.auth.service.AccountRelationService;
import projekti.auth.service.AccountService;

@Controller
public class UserController {
    
    static final String BASEPATH = "/users";
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    AccountRelationService accountRelationService;
    
    @GetMapping(BASEPATH)
    public String allUsers(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "user/all_users";
    }
    
    @GetMapping(BASEPATH + "/search")
    public String usersBySearch(Model model, @RequestParam Optional<String> nameusername) {
        if (nameusername.isPresent()) {
            String searchString = nameusername.orElse("");
            
            if (isValidParameters(searchString)) {
                model.addAttribute("accounts", accountService.
                        findByFullNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(searchString));
                return "user/all_users";
            }
        }
        
        return "redirect:" + BASEPATH;
    }
    
    @GetMapping(BASEPATH + "/{signature}")
    public String user(@PathVariable String signature, Model model) {
        Account account = null;
        if (isValidParameters(signature)) {
            account = accountService.findBySignature(signature);
        }
        
        if (account == null) {
            return "redirect:" + BASEPATH;
        }
        
        model.addAttribute("account", account);
        
        return "user/user";
    }
    
    @PostMapping(BASEPATH + "/follow")
    public String createRelation(@RequestParam String follower,
                                 @RequestParam String followed) {
        
        if (isValidParameters(follower, followed)) {
            accountRelationService.createFollower(follower, followed);
        }
        
        return "redirect:" + BASEPATH;
    }
    
    @PostMapping(BASEPATH + "/deletefollowing")
    public String deleteRelation(@RequestParam String follower,
                                 @RequestParam String followed) {
        
        if (isValidParameters(follower, followed)) {
            accountRelationService.deleteFollower(follower, followed);
        }
        
        return "redirect:" + BASEPATH + "/" + follower;
    }
    
    @PostMapping(BASEPATH + "/blockfollower")
    public String blockRelation(@RequestParam String follower,
                                @RequestParam String followed) {
        
        if (isValidParameters(follower, followed)) {
            accountRelationService.blockFollowing(follower, followed);    
        }
        
        return "redirect:" + BASEPATH + "/" + followed;
    }
    
    @PostMapping(BASEPATH + "/unblockfollower")
    public String unblockRelation(@RequestParam String follower,
                                  @RequestParam String followed) {
        
        if (isValidParameters(follower, followed)) {
            accountRelationService.unblockFollowing(follower, followed);
        }
        
        return "redirect:" + BASEPATH + "/" + followed;
    }
    
    public boolean isValidParameters(String... parameters) {
        for (String parameter : parameters) {
            if (parameter == null || parameter.trim().isEmpty()) {
                return false;
            }
        }
        
        return true;
    }
}
