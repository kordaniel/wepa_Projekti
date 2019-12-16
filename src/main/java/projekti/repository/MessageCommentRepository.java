package projekti.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.Message;
import projekti.model.MessageComment;

public interface MessageCommentRepository extends JpaRepository<MessageComment, Long> {
    
    public List<MessageComment> findByMessage(Message message, Pageable pgbl);
    
}
