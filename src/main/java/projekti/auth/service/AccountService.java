package projekti.auth.service;

import java.util.List;
import projekti.auth.model.Account;

public interface AccountService {
    
    void save(Account account);
    Account findByUsername(String username);
    Account findBySignature(String signature);
    List<Account> findAll();
    List<Account> findByFullNameContainingIgnoreCase(String fullName);
}
