package projekti.auth.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
//import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role extends AbstractPersistable<Long> {
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    //@NotEmpty
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;
    
}
