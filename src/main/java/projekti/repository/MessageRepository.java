package projekti.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.auth.model.Account;
import projekti.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findByAccount(Account account);
    List<Message> findByAccount(Account account, Pageable pgbl);
    List<Message> findByAccountIn(Collection<Account> accounts);
    List<Message> findByAccountIn(Collection<Account> accounts, Pageable pgbl);
}
