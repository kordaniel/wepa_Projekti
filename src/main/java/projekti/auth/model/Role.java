package projekti.auth.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role extends AbstractPersistable<Long> {
    
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;
    
}
