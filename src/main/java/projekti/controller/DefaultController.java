package projekti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping({"/", "/welcome"})
    public String helloWorld(Model model) {
        model.addAttribute("message", "World!");
        
        return "index";
    }
    
    @GetMapping("*")
    public String redirect() {
        return "redirect:/";
    }
}
