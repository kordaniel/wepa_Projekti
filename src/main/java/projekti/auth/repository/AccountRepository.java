package projekti.auth.repository;

import projekti.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Account findByUsername(String username);
    Account findBySignature(String signature);
    
}
