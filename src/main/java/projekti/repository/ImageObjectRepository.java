package projekti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.ImageObject;

public interface ImageObjectRepository extends JpaRepository<ImageObject, Long> {
    
}
