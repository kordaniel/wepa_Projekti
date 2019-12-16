package projekti.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import projekti.auth.model.Account;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ImageLike implements Serializable {
    
    @EmbeddedId
    private ImageLikeId id;
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "fileobject_id", insertable = false, updatable = false)
    private FileObject fileobject;
    
}
