package projekti.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;

@Controller
public class UserController {
    
    static final String BASEPATH = "/users";
    
    @Autowired
    AccountService accountService;
    
    @GetMapping(BASEPATH)
    public String allUsers(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "user/all_users";
    }
    
    @GetMapping(BASEPATH + "/{signature}")
    public String user(@PathVariable String signature, Model model) {
        //add error handling
        Account account = accountService.findBySignature(signature);
        if (account == null) {
            return "redirect:/users";
        }
        
        model.addAttribute("account", account);
        return "user/user";
    }
    
    @GetMapping(BASEPATH + "/search")
    public String findUser(Model model) {
        List<Account> accounts = accountService.findByFullNameContainingIgnoreCase("Da");
        model.addAttribute("accounts", accounts);
        
        return "";
    }
    
}
