package projekti.service;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projekti.model.FileObject;
import projekti.repository.FileObjectRepository;

@Service
public class ImageObjectService {
    
    private final static String[] SUPPORTED_FILETYPES = new String[]
        {"image/png", "image/jpg", "image/jpeg", "image/gif"};
    
    @Autowired
    FileObjectRepository fileObjectRepository;
    
    public void save(MultipartFile multipartFile) throws IOException {
        if (!Arrays.stream(SUPPORTED_FILETYPES).anyMatch(multipartFile.getContentType()::equals)) {
            return;
        }
        
        FileObject fo = new FileObject();
        fo.setName(multipartFile.getName());
        fo.setMediaType(multipartFile.getContentType());
        fo.setSize(multipartFile.getSize());
        fo.setContent(multipartFile.getBytes());
        
        fileObjectRepository.save(fo);
    }
    
}
