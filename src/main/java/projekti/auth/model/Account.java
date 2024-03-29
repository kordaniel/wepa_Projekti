package projekti.auth.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import projekti.model.Album;
import projekti.model.Message;
import projekti.model.MessageLike;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Account extends AbstractPersistable<Long> implements UserDetails {
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    @NotEmpty
    @Size(min = 6, max = 32)
    private String username;
    
    @NotEmpty
    private String password;
    
    @Transient
    private String passwordConfirm;
    
    @NotEmpty
    @Size(min = 5, max = 32)
    private String fullName;
    
    // user provides when she is creating the account, used as
    // the public identification. f.ex. when showing
    // profile page /users/identification
    @NotEmpty
    @Size(min = 8, max = 8)
    private String signature;
    
    @OneToOne
    private Album album;
    
    @OneToMany(mappedBy = "account")
    private List<Message> messages = new ArrayList<>();
    
    @OneToMany(mappedBy = "account")
    private List<MessageLike> messageLikes = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "follower", fetch = FetchType.EAGER)
    private List<AccountRelation> following = new ArrayList<>();
    
    @OneToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private List<AccountRelation> followers = new ArrayList<>();
    
    public List<Account> getActiveFollowing() {
        return following.stream()
                .filter(accrel -> !accrel.getBlocked())
                .map(accrel -> accrel.getFollowing())
                .collect(Collectors.toList());
    }
    
    public List<Account> getActiveFollowers() {
        return followers.stream()
                .filter(accrel -> !accrel.getBlocked())
                .map(accrel -> accrel.getFollower())
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        
        for (Role role : getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
