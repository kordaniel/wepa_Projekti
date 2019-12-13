package projekti.model;
/*
import java.time.LocalDateTime;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Profile("production")*/
public class PSQLFileObject/* extends AbstractPersistable<Long> implements FileObjectInterface*/ {
    // @NotEmpty annotation in this class results in application
    // crashing. Atleast with H2-database.
    /*
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
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] content;
    */
}
