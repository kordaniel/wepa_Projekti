package projekti.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.auth.model.Account;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Album extends AbstractPersistable<Long> {
    
    @Transient
    private static final int MAX_NUM_ENTRIES = 10;
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    private String name;
    
    @OneToOne(mappedBy = "album")
    private Account account;
    
    @OneToMany(mappedBy = "album")
    private List<FileObject> images = new ArrayList<>();
    
}
