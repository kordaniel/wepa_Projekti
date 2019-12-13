package projekti.auth.service;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekti.auth.model.Account;
import projekti.auth.repository.AccountRepository;
import projekti.auth.repository.RoleRepository;

@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public void save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRoles(new HashSet<>(roleRepository.findAll()));
        account.setSignature(account.getSignature().replaceAll("\\s", ""));
        accountRepository.save(account);
    }
    
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    @Override
    public Account findBySignature(String signature) {
        return accountRepository.findBySignature(signature);
    }
    
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findByFullNameContainingIgnoreCase(String fullName) {
        return accountRepository.findByFullNameContainingIgnoreCase(fullName);
    }
    
}
