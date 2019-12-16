package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projekti.model.FileObject;
import projekti.model.ImageComment;
import projekti.repository.ImageCommentRepository;

//@Service
public class ImageCommentService {
    
    //@Autowired
    //private ImageService imageService;
    
    //@Autowired
    //private ImageCommentRepository imageCommentRepository;
    
    public List<ImageComment> findByImageSortedByCreationDate(Long imageId, int pageNum, int perPage) {
        //FileObject fo = imageService.getOne(imageId);
        //return imageCommentRepository.findByFileObject(fo, getPageableByCreationDateDescending(pageNum, perPage));
        return null;
    }
    
    public void create(Long imageId, String comment) {
        /*
        FileObject fo = imageService.getOne(imageId);
        ImageComment newComment =new ImageComment();
        newComment.setFileobject(fo);
        newComment.setComment(comment);
        imageCommentRepository.save(newComment);*/
    }
    
    private Pageable getPageableByCreationDateDescending(int pageNum, int perPage) {
        return PageRequest.of(pageNum, perPage, Sort.by("createDateTime").descending());
    }
}
