package serverMonitoring.util.mail;

/**
 * Handel's mail sending functionality for password recover page.
 */
public interface PasswordRecoveryMail {

    /**
     * sends mail with included params
     */
    public void sendMail(String from, String to, String subject, String msg);

}
