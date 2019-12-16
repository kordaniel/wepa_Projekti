package projekti.auth.service;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.auth.model.Account;
import projekti.auth.repository.AccountRepository;
import projekti.auth.repository.RoleRepository;
import projekti.model.Album;
import projekti.service.AlbumService;

@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AlbumService albumService;
    
    @Override
    @Transactional
    public void save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRoles(new HashSet<>(roleRepository.findAll()));
        account.setSignature(account.getSignature().replaceAll("\\s", ""));
        
        Album accountAlbum = new Album();
        accountAlbum.setName(account.getFullName() + "s album");
        albumService.save(accountAlbum);
        
        account.setAlbum(accountAlbum);
        
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

    @Override
    public List<Account> findByFullNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String searchWord) {
        return accountRepository.findByFullNameContainingIgnoreCase(searchWord);
    }
    
}
