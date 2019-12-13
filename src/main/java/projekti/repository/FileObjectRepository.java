package projekti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.FileObject;

public interface FileObjectRepository extends JpaRepository<FileObject, Long> {
    
}
