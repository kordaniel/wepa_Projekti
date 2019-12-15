package projekti.auth.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountRelation extends AbstractPersistable<Long> {
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    //@NotEmpty
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id", nullable = false)
    private Account follower;
    
    //@NotEmpty
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "following_id", nullable = false)
    private Account following;
    
    //@NotEmpty
    private boolean blocked;
    
    public boolean getBlocked() {
        return this.blocked;
    }
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountRelation)) return false;
        
        AccountRelation other = (AccountRelation) o;
        
        return getId() != null && getId().equals(other.getId());
    }
    
    @Override
    public int hashCode() {
        return 31;
    }*/
}
