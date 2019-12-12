package projekti;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @PostConstruct
    public void init() {
        if (accountRepository.findByUsername("admin") != null) return;
        
        accountRepository.save(new Account(
                "administrator",
                passwordEncoder.encode("administrator"),
                "Admin superuser",
                "rootroot"));
    }
    
}
