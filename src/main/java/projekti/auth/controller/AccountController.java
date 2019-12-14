package projekti.auth.controller;

import projekti.auth.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import projekti.auth.service.AccountService;
import projekti.auth.service.SecurityService;
import projekti.auth.validator.AccountValidator;

@Controller
public class AccountController {
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    AccountValidator accountValidator;
    
    
    @GetMapping("/registration")
    public String registration(@ModelAttribute Account account) {
        return "auth/registration";
    }
    
    
    @PostMapping("/registration")
    public String registration(@ModelAttribute("accountForm") Account accountForm,
            BindingResult bindingResult) {
        System.out.println("MOI");
        if (accountForm == null) {
            return "redirect:/registration";
        }
        accountValidator.validate(accountForm, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        
        accountService.save(accountForm);
        securityService.autoLogin(accountForm.getUsername(), accountForm.getPasswordConfirm());
        
        return "redirect:/welcome";
    }
    
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and/or password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out succesfully.");
        }
        
        return "auth/login";
    }
    
}
