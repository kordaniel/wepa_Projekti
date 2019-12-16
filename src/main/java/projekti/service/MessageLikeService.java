package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projekti.repository.MessageLikeRepository;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;
import projekti.model.MessageLike;
import static projekti.MyApplication.ANON_USERNAME;
import projekti.model.MessageLikeId;

@Service
public class MessageLikeService {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageLikeRepository messageLikeRepository;
    
    
    public void addNewLike(Long messageId) {
        //This method looses the creation time of the messagelike
        //(it simply creates a new one and tries to save it.
        //Should be investigated when there is time. For now the
        //datetimes are not used anyways...
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String authorizedUsername = securityContext.getAuthentication().getName();
        
        if (ANON_USERNAME.equals(authorizedUsername)) {
            return;
        }
        
        Account account = accountService.findByUsername(authorizedUsername);
        
        if (account == null) {
            return;
        }
        
        MessageLikeId messageLikeId = new MessageLikeId(account.getId(), messageId);
        MessageLike messageLike = new MessageLike();
        messageLike.setId(messageLikeId);
        
        messageLikeRepository.save(messageLike);
    }
}
