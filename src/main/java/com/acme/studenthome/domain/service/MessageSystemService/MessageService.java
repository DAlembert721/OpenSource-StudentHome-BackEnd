package com.acme.studenthome.domain.service.MessageSystemService;

import com.acme.studenthome.domain.model.MessageSystem.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    Page<Message> getAllMessagesBySenderIdAndReceiverId (Long senderId, Long receiverId, Pageable pageable);
    Page<Message> getAllMessagesBySenderId (Long senderId, Pageable pageable);
    Page<Message> getAllMessagesByReceiverId (Long receiverId, Pageable pageable);
    Page<Message> getAllMessages (Pageable pageable);

    Message createMessage(Long senderId, Long receiverId, Message studentOpinion);
    Message getMessageByIdAndSenderIdAndReceiverId(Long messageId,Long senderId, Long receiverId);
    Message updateMessage(Long messageId, Long senderId, Long receiverId, Message messageRequest);
    ResponseEntity<?> deleteMessage(Long messageId, Long senderId, Long receiverId);

    void sendEmail(Message message);
}
