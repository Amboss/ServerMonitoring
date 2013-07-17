package serverMonitoring.model;

import java.io.Serializable;

/**
 * Object for password recovery page
 */
public class PasswordRecoveryObject implements Serializable {

    private String email;

    public PasswordRecoveryObject() {
    }

    public PasswordRecoveryObject(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PasswordRecoveryObject{" +
                "email='" + email + '\'' +
                '}';
    }
}
