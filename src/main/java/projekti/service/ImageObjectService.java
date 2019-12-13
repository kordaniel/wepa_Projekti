package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projekti.model.ImageObject;
import projekti.repository.ImageObjectRepository;

@Service
public class ImageObjectService {
    
    @Autowired
    ImageObjectRepository imageObjectRepository;
    
    public void save(MultipartFile multipartFile) {
        
    }
    
}
