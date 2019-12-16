package projekti.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;
import projekti.model.Album;
import projekti.model.FileObject;
import projekti.repository.FileObjectRepository;

@Service
public class ImageObjectService {
    
    private final static String[] SUPPORTED_MEDIATYPES = new String[]
        {"image/png", "image/jpg", "image/jpeg", "image/gif"};
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    @Autowired
    private AccountService accountService;
    
    public List<FileObject> findByAccountUsernameFollowingSortedByCreationDate(String username, int pageNum, int perPage) {
        Account account = accountService.findByUsername(username);    
        if (account == null) {
            return null;
        }
        
        List<Album> albums = account.getActiveFollowing().stream()
                                    .map(ac -> ac.getAlbum())
                                    .collect(Collectors.toList());
        if (albums == null) {
            return null;
        }
        
        albums.add(account.getAlbum());
        
        return fileObjectRepository.findByAlbumIn(albums,
                getPageableByCreationDateDescending(pageNum, perPage));
        
    }
    
    public FileObject saveAlbumImage(MultipartFile multipartFile, Album album) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()
                || !Arrays.stream(SUPPORTED_MEDIATYPES).anyMatch(multipartFile.getContentType()::equals)) {
            return null;
        }
        
        FileObject fo = new FileObject();
        fo.setName(multipartFile.getOriginalFilename());
        fo.setContentType(multipartFile.getContentType());
        fo.setSize(multipartFile.getSize());
        fo.setContent(multipartFile.getBytes());
        fo.setAlbum(album);
        
        return fileObjectRepository.save(fo);
    }
    
    public void save(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()
                || !Arrays.stream(SUPPORTED_MEDIATYPES).anyMatch(multipartFile.getContentType()::equals)) {
            return;
        }
        
        FileObject fo = new FileObject();
        fo.setName(multipartFile.getOriginalFilename());
        fo.setContentType(multipartFile.getContentType());
        fo.setSize(multipartFile.getSize());
        fo.setContent(multipartFile.getBytes());
        
        fileObjectRepository.save(fo);
    }
    
    public FileObject getOne(Long id) {
        return fileObjectRepository.getOne(id);
    }
    
    private Pageable getPageableByCreationDateDescending(int pageNum, int perPage) {
        return PageRequest.of(pageNum, perPage, Sort.by("createDateTime").descending());
    }
    
    public List<FileObject> findAll() {
        return fileObjectRepository.findAll();
    }
    
}
