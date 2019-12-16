package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;
import projekti.model.Message;
import projekti.repository.MessageRepository;
import static projekti.MyApplication.ANON_USERNAME;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private AccountService accountService;
    
    public List<Message> findByAccountUsernameFollowing(String accountUsername, int pageNum, int perPage) {
        Account account = accountService.findByUsername(accountUsername);
    
        List<Account> messagesByAccounts = account.getActiveFollowing();
        messagesByAccounts.add(account);
        
        return messageRepository.findByAccountIn(messagesByAccounts, getPageableByCreationDateDescending(pageNum, perPage));
    }
    
    public List<Message> findByAccountUsernameSortedByCreationdate(String username, int pageNum, int perPage) {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            return null;
        }
        
        return findByAccountSortedByCreationdate(account, pageNum, perPage);
    }
    
    public List<Message> findByAccountSortedByCreationdate(Account account, int pageNum, int perPage) {
        //Sorting examples to be found in w3.ex last messages and exams and questions
        //Pageable pageable = PageRequest.of(pageNum, perPage, Sort.by("createDateTime").descending());
        return messageRepository.findByAccount(account, getPageableByCreationDateDescending(pageNum, perPage));
    }
    
    public void create(String message) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String authorizedUsername = securityContext.getAuthentication().getName();
        
        if (ANON_USERNAME.equals(authorizedUsername)) {
            return;
        }
        
        Account poster = accountService.findByUsername(authorizedUsername);
        Message messageObject = new Message();
        messageObject.setAccount(poster);
        messageObject.setContent(message);
        messageRepository.save(messageObject);
    }
    
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
    
    public Message getOne(Long id) {
        return messageRepository.getOne(id);
    }
    
    private Pageable getPageableByCreationDateDescending(int pageNum, int perPage) {
        return PageRequest.of(pageNum, perPage, Sort.by("createDateTime").descending());
    }
}
