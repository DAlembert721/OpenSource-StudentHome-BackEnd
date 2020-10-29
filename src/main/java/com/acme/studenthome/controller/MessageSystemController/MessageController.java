package com.acme.studenthome.controller.MessageSystemController;

import com.acme.studenthome.domain.model.MessageSystem.Message;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.StudentOpinion;
import com.acme.studenthome.domain.service.MessageSystemService.MessageService;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentOpinionService;
import com.acme.studenthome.resource.MessageSystemResource.MessageResource;
import com.acme.studenthome.resource.MessageSystemResource.SaveMessageResource;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.SaveStudentOpinionResource;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.StudentOpinionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageService messageService;

    @GetMapping("messages")
    public Page<MessageResource> getAllMessages(Pageable pageable) {
        Page<Message> messagePage =
                messageService.getAllMessages(pageable);
        List<MessageResource> resources = messagePage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("receivers/{receiverId}/messages")
    public Page<MessageResource> getAllMessagesByReceiver(
            @PathVariable Long receiverId, Pageable pageable) {
        Page<Message> messagePage =
                messageService.getAllMessagesByReceiverId(receiverId, pageable);
        List<MessageResource> resources = messagePage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("senders/{senderId}/messages")
    public Page<MessageResource> getAllMessagesBySender(
            @PathVariable Long senderId, Pageable pageable) {
        Page<Message> messagePage =
                messageService.getAllMessagesBySenderId(senderId, pageable);
        List<MessageResource> resources = messagePage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("receivers/{receiverId}/senders/{senderId}/messages")
    public Page<MessageResource> getAllMessagesByReceiversAndSenders(
            @PathVariable (name = "receiverId") Long receiverId,
            @PathVariable (name = "senderId") Long senderId, Pageable pageable) {
        Page<Message> messagePage = messageService.
                getAllMessagesBySenderIdAndReceiverId(senderId,receiverId, pageable);
        List<MessageResource> resources = messagePage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("receivers/{receiverId}/senders/{senderId}/messages/{messageId}")
    public MessageResource getMessageByIdAndlandLordAndStudent(
            @PathVariable (name = "receiverId") Long receiverId,
            @PathVariable (name = "senderId") Long senderId,
            @PathVariable(name = "messageId") Long messageId) {
        return convertToResource(messageService.
                getMessageByIdAndSenderIdAndReceiverId(
                        messageId,senderId,receiverId));
    }

    @PostMapping("receivers/{receiverId}/senders/{senderId}/messages")
    public MessageResource createMessage(
            @PathVariable (name = "receiverId") Long receiverId,
            @PathVariable (name = "senderId") Long senderId,
            @Valid @RequestBody SaveMessageResource resource) {
        return convertToResource(messageService.createMessage(
                senderId,receiverId, convertToEntity(resource)));
    }

    @PutMapping("receivers/{receiverId}/senders/{senderId}/messages/{messageId}")
    public MessageResource updateStudentOpinion(
            @PathVariable (name = "receiverId") Long receiverId,
            @PathVariable (name = "senderId") Long senderId,
            @PathVariable(name = "messageId") Long messageId,
            @Valid @RequestBody SaveMessageResource resource) {
        return convertToResource(messageService.updateMessage(
                messageId,senderId,receiverId,convertToEntity(resource)));
    }

    @DeleteMapping("receivers/{receiverId}/senders/{senderId}/messages/{messageId}")
    public ResponseEntity<?> deleteStudentOpinion(
            @PathVariable (name = "receiverId") Long receiverId,
            @PathVariable (name = "senderId") Long senderId,
            @PathVariable(name = "messageId") Long messageId) {
        return messageService.deleteMessage(messageId,senderId,receiverId);
    }

    private Message convertToEntity(SaveMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    private MessageResource convertToResource(Message entity) {
        return mapper.map(entity, MessageResource.class);
    }
}
