package projekti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.auth.model.Account;
import projekti.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    
    Album findByAccount(Account account);
    
}
