package projekti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.auth.service.AccountService;

@Controller
public class DefaultController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping({"/", "/welcome"})
    public String helloWorld(Model model) {
        model.addAttribute("message", "World!");
        
        return "index";
    }
}
