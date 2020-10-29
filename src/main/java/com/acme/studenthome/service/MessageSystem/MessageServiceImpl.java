package com.acme.studenthome.service.MessageSystem;

import com.acme.studenthome.domain.model.MessageSystem.Message;
import com.acme.studenthome.domain.repository.MessageSystemRepository.MessageRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.AccountRepository;
import com.acme.studenthome.domain.service.MessageSystemService.MessageService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository senderRepository;

    @Autowired
    private AccountRepository receiverRepository;

    @Override
    public Page<Message> getAllMessagesBySenderIdAndReceiverId(Long senderId, Long receiverId, Pageable pageable) {
        return messageRepository.findBySenderIdAndReceiverId(senderId,receiverId,pageable);
    }

    @Override
    public Page<Message> getAllMessagesBySenderId(Long senderId, Pageable pageable) {
        return messageRepository.findBySenderId(senderId,pageable);
    }

    @Override
    public Page<Message> getAllMessagesByReceiverId(Long receiverId, Pageable pageable) {
        return messageRepository.findByReceiverId(receiverId,pageable);
    }

    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public Message createMessage(Long senderId, Long receiverId, Message studentOpinion) {
        return receiverRepository.findById(receiverId).map(receiver -> {
            return senderRepository.findById(senderId).map(sender-> {
                studentOpinion.setReceiver(receiver);
                studentOpinion.setSender(sender);
                return messageRepository.save(studentOpinion);
            }).orElseThrow(()->new ResourceNotFoundException(
                    "Sender", "Id", senderId));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Receiver", "Id", receiverId));
    }

    @Override
    public Message getMessageByIdAndSenderIdAndReceiverId(Long messageId, Long senderId, Long receiverId) {
        return messageRepository.findByIdAndSenderIdAndReceiverId(messageId,senderId,receiverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Message not found with Id " + messageId +
                                " and SenderId " + senderId + " and ReceiverId " + receiverId));
    }

    @Override
    public Message updateMessage(Long messageId, Long senderId, Long receiverId, Message messageRequest) {
        SenderAndReceiverExist(senderId, receiverId);
        return messageRepository.findById(messageId).map(studentOpinion -> {
            studentOpinion.setContent(messageRequest.getContent());
            return messageRepository.save(studentOpinion);
        }).orElseThrow(() -> new ResourceNotFoundException("Message", "Id", messageId));
    }

    @Override
    public ResponseEntity<?> deleteMessage(Long messageId, Long senderId, Long receiverId) {
        SenderAndReceiverExist(senderId, receiverId);
        return messageRepository.findById(messageId).map(comment -> {
            messageRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Message", "Id", messageId));
    }

    @Override
    public void sendEmail(Message message) {

    }

    public void SenderAndReceiverExist(Long senderId, Long receiverId) {
        if (!senderRepository.existsById(senderId))
            throw new ResourceNotFoundException("Sender", "Id", senderId);
        if (!receiverRepository.existsById(receiverId))
            throw new ResourceNotFoundException("Receiver", "Id", receiverId);
    }
}
