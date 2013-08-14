package serverMonitoring.util.mail.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import serverMonitoring.logic.service.AnonymousService;
import serverMonitoring.model.ftl.SystemSettingsModel;
import serverMonitoring.util.mail.CustomMailDelivery;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Handel's mail sending functionality.
 */
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@Component("CustomMailDeliveryImpl")
public class CustomMailDeliveryImpl implements CustomMailDelivery {

    protected static Logger logger = Logger.getLogger(CustomMailDeliveryImpl.class);

    private AnonymousService anonymousService;

    private Transport transport;

    private SystemSettingsModel settingsModel;

    private Session session;

    private MimeMessage message;

    private InternetAddress fromAddress, toAddress;

    private Properties properties;

    @Autowired
    public void setAnonymousService(AnonymousService anonymousService) {
        this.anonymousService = anonymousService;
    }

    /**
     * @param from    - must contain address of sender,
     * @param to      - must contain address of target,
     * @param subject - must be included with short description,
     * @param body    - main message of mail.
     * @throws SendFailedException if any param is empty.
     */
    public void sendMail(String from, String to, String subject, String body) throws SendFailedException {

        settingsModel = anonymousService.getSettingsByName("default");

        // defining properties
        properties = new Properties();
        properties.put("mail.smtp.host", settingsModel.getSmtpServerHost());
        properties.put("mail.smtp.port", settingsModel.getSmtpServerPort());
        properties.put("mail.smtp.user", settingsModel.getUsername());
        properties.put("mail.smtp.password", settingsModel.getPassword());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // InternetAddress initialisation
        try {
            fromAddress = new InternetAddress(from);
            toAddress = new InternetAddress(to);

            // new Authenticator session
            session = (javax.mail.Session) Session.getDefaultInstance(properties, null);

        } catch (AddressException e) {
            throw new RuntimeException();
        }

        // executing mail sending
        try {
            if (from != null && to != null) {
                if (subject != null && body != null) {

                    // defining message
                    message = new MimeMessage(session);

                    message.setFrom(fromAddress);
                    message.addRecipient(Message.RecipientType.TO, toAddress);
                    message.setSubject(subject);
                    message.setText(body);
                    message.setSentDate(new Date());
                    message.setHeader("MIME-Version", "1.0");
                    message.setHeader("Content-Type", "text/html");

                    // Transport initialisation
                    if (transport == null) {
                        transport = session.getTransport("smtp");

                        if (!transport.isConnected())
                            transport.connect(
                                    settingsModel.getSmtpServerHost(),
                                    settingsModel.getSmtpServerPort(),
                                    settingsModel.getUsername(),
                                    settingsModel.getPassword());
                    }

                    transport.sendMessage(message, message.getAllRecipients());

                } else {
                    throw new MessagingException("Subject and message of mail is empty!");
                }
            } else {
                throw new MessagingException("Address and target of mail is empty!");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            if (transport != null) try {
                transport.close();
            } catch (MessagingException ignore) {
            }
        }
    }
}
