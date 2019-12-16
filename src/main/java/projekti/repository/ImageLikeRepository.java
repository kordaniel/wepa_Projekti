package projekti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.ImageLike;

public interface ImageLikeRepository extends JpaRepository<ImageLike, Long> {
    
}
