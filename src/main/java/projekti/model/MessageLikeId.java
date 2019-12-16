package projekti.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class MessageLikeId implements Serializable {
    
    @Column(name = "account_id")
    private Long accountId;
    
    @Column(name = "message_id")
    private Long messageId;
    
}
