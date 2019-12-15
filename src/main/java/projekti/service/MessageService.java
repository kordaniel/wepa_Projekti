package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static projekti.MyApplication.ANON_USERNAME;
import projekti.auth.model.Account;
import projekti.auth.repository.AccountRepository;
import projekti.auth.service.AccountService;
import projekti.model.Message;
import projekti.repository.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private AccountService accountService;
    
    public List<Message> findByAccountSignatureSortedByCreationdate(String signature, int pageNum, int perPage) {
        Account account = accountService.findBySignature(signature);
        if (account == null) {
            return null;
        }
        
        return findByAccountSortedByCreationdate(account, pageNum, perPage);
    }
    
    public List<Message> findByAccountSortedByCreationdate(Account account, int pageNum, int perPage) {
        //Sorting examples to be found in w3.ex last messages and exams and questions
        Pageable pageable = PageRequest.of(pageNum, perPage, Sort.by("createDateTime").descending());
        return messageRepository.findByAccount(account, pageable);
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
    /*
    public void create() {
        Account acc = accountRepository.findBySignature("danielko");
        Message mess1 = new Message();
        mess1.setAccount(acc);
        mess1.setContent("Hello World");
        messageRepository.save(mess1);
        Message mess2 = new Message();
        mess2.setAccount(acc);
        mess2.setContent("Second message to the woorrllldddddddddddddddddddddd!!!11");
        messageRepository.save(mess2);
    }*/
    
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
