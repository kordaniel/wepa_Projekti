package projekti.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;
import projekti.model.Album;
import projekti.repository.AlbumRepository;

@Service
public class AlbumService {
    
    @Autowired
    private AccountService accountService;
    
    
    @Autowired
    private ImageObjectService imageObjectService;
    
    @Autowired
    private AlbumRepository albumRepository;
    
    public void save(Album album) {
        albumRepository.save(album);
    }
    
    public void saveImageToAlbum(Long albumId,
            MultipartFile multipartFile) throws IOException {
        
        Album album = albumRepository.getOne(albumId);
        
        if (album == null || album.getImages().size() >= 10) {
            return;
        }
        
        imageObjectService.saveAlbumImage(multipartFile, album);
    }
    
    public Album getOne(Long id) {
        return albumRepository.getOne(id);
    }
    
    public Album findByAccount(String accountSignature) {
        Account account = accountService.findBySignature(accountSignature);
        return albumRepository.findByAccount(account);
    }
    
}
