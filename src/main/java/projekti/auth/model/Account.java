package projekti.auth.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Account extends AbstractPersistable<Long> {
    
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
    
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
    
    @JoinTable(
            name = "account_following",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Account> following = new ArrayList<>();
    
    @ManyToMany(mappedBy = "following")
    private List<Account> followers = new ArrayList<>();
    
}