package projekti.auth.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.auth.model.Account;
import projekti.auth.model.AccountRelation;
import projekti.auth.repository.AccountRelationRepository;

@Service
public class AccountRelationService {
    
    @Autowired
    private AccountRelationRepository accountRelationRepository;
    
    @Autowired
    private AccountService accountService;
    
    @Transactional
    public void deleteFollower(String follower, String following) {
        Account accFollower  = accountService.findBySignature(follower);
        Account accFollowing = accountService.findBySignature(following);
        if (accFollower == null || accFollowing == null) {
            return;
        }
        
        AccountRelation relation = accountRelationRepository
                .findByFollowerAndFollowing(accFollower, accFollowing);
        if (relation != null) {
            accountRelationRepository.deleteById(relation.getId());
        }
    }
    
    @Transactional
    public void createFollower(String follower, String followed) {
        Account accFollower  = accountService.findBySignature(follower);
        Account accFollowed  = accountService.findBySignature(followed);
        
        if (accFollower == null || accFollowed == null
                || accountRelationRepository.
                 findByFollowerAndFollowing(accFollower, accFollowed) != null) {
            return;
        }

        /*
        // Prevent user from following themselves
        if (Objects.equals(accFollower.getId(), accFollowed.getId())) {
            return;
        }*/
        
        AccountRelation relation = new AccountRelation();
        relation.setFollower(accFollower);
        relation.setFollowing(accFollowed);
        relation.setBlocked(false);
        accountRelationRepository.save(relation);
    }
    
    @Transactional
    public void blockFollowing(String follower, String followed) {
        AccountRelation relation = getRelationOrNull(follower, followed);
        if (relation != null) {
            relation.setBlocked(true);
        }
    }
    
    @Transactional
    public void unblockFollowing(String follower, String followed) {
        AccountRelation relation = getRelationOrNull(follower, followed);
        if (relation != null) {
            relation.setBlocked(false);
        }
    }
    
    public List<AccountRelation> findAll() {
        return accountRelationRepository.findAll();
    }
    
    private AccountRelation getRelationOrNull(String follower, String followed) {
        Account accFollower = accountService.findBySignature(follower);
        Account accFollowed = accountService.findBySignature(followed);
        
        if (accFollower == null || accFollowed == null) {
            return null;
        }
        
        return accountRelationRepository.
                findByFollowerAndFollowing(accFollower, accFollowed);
    }
    
}
