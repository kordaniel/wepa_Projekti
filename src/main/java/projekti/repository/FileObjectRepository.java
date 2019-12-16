package projekti.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.model.Album;
import projekti.model.FileObject;

public interface FileObjectRepository extends JpaRepository<FileObject, Long> {
    
    List<FileObject> findByAlbum(Album album);
    List<FileObject> findByAlbum(Album album, Pageable pgbl);
    List<FileObject> findByAlbumIn(Collection<Album> albums);
    List<FileObject> findByAlbumIn(Collection<Album> albums, Pageable pgbl);
    
}
