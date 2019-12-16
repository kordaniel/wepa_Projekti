package projekti.model;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
//import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//@Profile("default")
public class FileObject extends AbstractPersistable<Long> {
    // @NotEmpty annotation in this class results in application
    // crashing. Atleast with H2-database.
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    //@NotEmpty
    private String name;
    
    //@NotEmpty
    private String contentType;
    
    //@NotEmpty
    private Long size;
    
    //@NotEmpty
    @Lob
    @Basic(fetch = FetchType.LAZY)
    //@Type(type = "org.hibernate.type.BinaryType") //heroku postgresql
    private byte[] content;
    
}
