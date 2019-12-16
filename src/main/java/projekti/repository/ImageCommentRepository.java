package projekti.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.FileObject;
import projekti.model.ImageComment;

public interface ImageCommentRepository extends JpaRepository<ImageComment, Long> {
    
    //public List<ImageComment> findByFileObject(FileObject fileObject, Pageable pgbl);
    
}
