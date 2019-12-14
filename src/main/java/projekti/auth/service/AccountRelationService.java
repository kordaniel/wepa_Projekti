package projekti.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.auth.repository.AccountRelationRepository;

@Service
public class AccountRelationService {
    
    @Autowired
    private AccountRelationRepository accountRelationRepository;
    
    
    
}
