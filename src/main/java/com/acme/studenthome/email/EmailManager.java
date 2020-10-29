package com.acme.studenthome.email;

import com.acme.studenthome.domain.model.MessageSystem.Message;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailManager {
/*https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mail.html*/

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void placeMessage(Message message) {

        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        String sender="" + message.getSender().getFirstName() + " " + message.getSender().getLastName();
        String receiver="" + message.getReceiver().getFirstName() + " " + message.getReceiver().getLastName();
        msg.setFrom(message.getSender().getEmailAddress());
        msg.setTo(message.getReceiver().getEmailAddress());
        msg.setSubject("Recibió una notificación de "+ sender);
        msg.setText("Estimado " + receiver  + ",\n\n"
                    + sender + "le ha enviado el siguiente mensaje:\n"
                    + message.getContent()
                    + "\n\n Gracias por subscribirse a StudentHome");
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
