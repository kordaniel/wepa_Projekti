package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projekti.model.Message;
import projekti.model.MessageComment;
import projekti.repository.MessageCommentRepository;

@Service
public class MessageCommentService {
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private MessageCommentRepository messageCommentRepository;
    
    public List<MessageComment> findByMessageSortedByCreationDate(Long messageId, int pageNum, int perPage) {
        Message message = messageService.getOne(messageId);
        return messageCommentRepository.findByMessage(message, getPageableByCreationDateDescending(pageNum, perPage));
    }
    
    public void create(Long messageId, String comment) {
        Message message = messageService.getOne(messageId);
        MessageComment newComment = new MessageComment();
        newComment.setMessage(message);
        newComment.setComment(comment);
        messageCommentRepository.save(newComment);
    }
    
    private Pageable getPageableByCreationDateDescending(int pageNum, int perPage) {
        return PageRequest.of(pageNum, perPage, Sort.by("createDateTime").descending());
    }
    
}
