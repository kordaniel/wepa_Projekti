package projekti.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
//import javax.persistence.FetchType;
//import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
    
    private String comment;
    
    @ManyToOne
    private Album album;
    
    //@OneToMany(mappedBy = "fileobject", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ImageComment> comments = new ArrayList<>();
    
    @OneToMany(mappedBy = "fileobject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageLike> likes = new ArrayList<>();
    
    //@Lob
    //@Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.BinaryType") //heroku postgresql
    private byte[] content;
    /*
    public int getCommentsCount() {
        if (comments == null) {
            return 0;
        }
        return comments.size();
    }*/
    
    public int getLikesCount() {
        if (likes == null) {
            return 0;
        }
        return likes.size();
    }
}
