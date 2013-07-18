package serverMonitoring.util.mail.impl;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * Handel's mail sending functionality for password recover page.
 */
@Component
public class PasswordRecoveryMailImpl {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param from    - must contain address of sender,
     * @param to      - must contain address of target,
     * @param subject - must be included with short description,
     * @param message - main message of mail.
     * @throws RuntimeException if any param is empty
     */
    public void sendMail(String from, String to, String subject, String message) {

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        try {
            if (from != null && to != null) {
                if (subject != null && message != null) {
                    simpleMessage.setFrom(from);
                    simpleMessage.setTo(to);
                    simpleMessage.setSubject(subject);
                    simpleMessage.setText(message);
                    mailSender.send(simpleMessage);
                } else {
                    throw new MessagingException("Subject and message of mail is empty!");
                }
            } else {
                throw new MessagingException("Address of object and target is empty!");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
