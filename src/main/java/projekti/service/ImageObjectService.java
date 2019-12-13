package projekti.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projekti.model.FileObject;
import projekti.repository.FileObjectRepository;

@Service
public class ImageObjectService {
    
    private final static String[] SUPPORTED_MEDIATYPES = new String[]
        {"image/png", "image/jpg", "image/jpeg", "image/gif"};
    
    @Autowired
    FileObjectRepository fileObjectRepository;
    
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
    
    public List<FileObject> findAll() {
        return fileObjectRepository.findAll();
    }
    
}
