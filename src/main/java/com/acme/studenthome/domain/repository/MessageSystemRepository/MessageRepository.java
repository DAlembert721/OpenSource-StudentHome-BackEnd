package com.acme.studenthome.domain.repository.MessageSystemRepository;

import com.acme.studenthome.domain.model.MessageSystem.Message;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.StudentOpinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository  extends JpaRepository<Message, Long> {
    Page<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId, Pageable pageable);
    Page<Message> findBySenderId(Long senderId, Pageable pageable);
    Page<Message> findByReceiverId(Long receiverId, Pageable pageable);
    Optional<Message> findByIdAndSenderIdAndReceiverId(Long id, Long senderId, Long receiverId);
}
