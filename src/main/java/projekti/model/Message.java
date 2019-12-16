package projekti.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    //not tested, which way does the orphanRemoval work. Should be that if the
    //message is deleted, then the messageLikes also get deleted, not the other
    //way around!!
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageLike> likes = new ArrayList<>();
    
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageComment> comments = new ArrayList<>();
    
    @Column(length = 32767)
    private String content;
    
    public int getLikesCount() {
        if (likes == null) {
            return 0;
        }
        return likes.size();
    }
    
    public int getCommentsCount() {
        if (comments == null) {
            return 0;
        }
        return comments.size();
    }
    
}
