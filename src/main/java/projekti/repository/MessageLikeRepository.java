package projekti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.MessageLike;

public interface MessageLikeRepository extends JpaRepository<MessageLike, Long> {
    
}
