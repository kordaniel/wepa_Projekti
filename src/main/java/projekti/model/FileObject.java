package projekti.model;

import java.time.LocalDateTime;
//import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
//import javax.persistence.FetchType;
//import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class FileObject extends AbstractPersistable<Long> {
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    private String name;
    
    private String contentType;
    
    private Long size;
    
    @ManyToOne
    private Album album;
    
    //@Lob
    //@Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.BinaryType") //heroku postgresql
    private byte[] content;
    
}
