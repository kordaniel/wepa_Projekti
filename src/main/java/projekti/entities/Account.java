package projekti.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Account extends AbstractPersistable<Long> {
    
    //@NotEmpty
    //@Size(min = 6, max = 30)
    private String username;// = "";
    
    //@NotEmpty
    //@Size(min = 8, max = 30)
    private String password;// = "";
    
    //@NotEmpty
    //@Size(min = 5, max = 30)
    private String fullName;// = "";
    
    // user provides when she is creating the account, used as
    // the public identification. f.ex. when showing
    // profile page /users/identification
    //@NotEmpty
    private String signature;// = "";
    
    @JoinTable(
            name = "account_following",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id"))
    @ManyToMany//(fetch = FetchType.LAZY)
    private List<Account> following = new ArrayList<>();
    
    @ManyToMany(mappedBy = "following")
    private List<Account> followers = new ArrayList<>();
    
    public Account(String username, String password, String fullName, String signature) {
        this.username  = username;
        this.password  = password;
        this.fullName  = fullName;
        this.signature = signature;
    }
}
