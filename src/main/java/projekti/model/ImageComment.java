package projekti.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ImageComment extends AbstractPersistable<Long> {
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    @ManyToOne
    private Album album;
    private String comment;
    @OneToOne
    private FileObject fileObject;
    
}
