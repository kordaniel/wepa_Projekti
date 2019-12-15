package projekti.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.auth.model.Account;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Message extends AbstractPersistable<Long> {
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    @ManyToOne
    private Account account;
    
    @Column(length = 32767)
    private String content;
}
