package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.model.FileObject;
import projekti.repository.FileObjectRepository;


@Service
public class ImageService {
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    public FileObject getOne(Long id) {
        return fileObjectRepository.getOne(id);
    }
    
}
