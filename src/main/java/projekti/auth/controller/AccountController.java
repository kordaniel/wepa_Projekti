package projekti.auth.controller;

import projekti.auth.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String registration(@ModelAttribute Account accountForm,
            BindingResult bindingResult) {
        
        if (accountForm == null) {
            return "redirect:/registration";
        }
        accountValidator.validate(accountForm, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        
        accountService.save(accountForm);
        securityService.autoLogin(accountForm.getUsername(), accountForm.getPassword());
        
        //TODO: redirect to users page
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
    
    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "user/all_users";
    }
    
    @GetMapping("/users/{signature}")
    public String user(@PathVariable String signature, Model model) {
        //add error handling
        Account acc = accountService.findBySignature(signature);
        model.addAttribute("account", acc);
        return "user/user";
    }
    
}
