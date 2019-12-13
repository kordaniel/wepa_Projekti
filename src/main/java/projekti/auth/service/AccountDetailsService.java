package projekti.auth.service;

import projekti.auth.repository.AccountRepository;
import projekti.auth.model.Account;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.auth.model.Role;

@Service
public class AccountDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) throw new UsernameNotFoundException("User not found: " + username);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        
        for (Role role : account.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                /*true,
                true,
                true,
                true,*/
                grantedAuthorities
        );
    }
    
}
