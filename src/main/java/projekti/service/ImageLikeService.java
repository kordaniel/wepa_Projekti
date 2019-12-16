package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static projekti.MyApplication.ANON_USERNAME;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;
import projekti.model.ImageLike;
import projekti.model.ImageLikeId;
import projekti.repository.ImageLikeRepository;

@Service
public class ImageLikeService {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ImageLikeRepository imageLikeRepository;
    
    public void addNewLike(Long imageId) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String authorizedUsername = securityContext.getAuthentication().getName();
        
        if (ANON_USERNAME.equals(authorizedUsername)) {
            return;
        }
        
        Account account = accountService.findByUsername(authorizedUsername);
        
        if (account == null) {
            return;
        }
        
        ImageLikeId imageLikeId = new ImageLikeId(account.getId(), imageId);
        ImageLike imageLike = new ImageLike();
        imageLike.setId(imageLikeId);
        
        imageLikeRepository.save(imageLike);
    }
    
}
