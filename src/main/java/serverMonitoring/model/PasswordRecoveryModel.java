package serverMonitoring.model;

import java.io.Serializable;

/**
 * Object for password recovery page
 */
public class PasswordRecoveryModel implements Serializable {

    private String email;

    public PasswordRecoveryModel() {
    }

    public PasswordRecoveryModel(String email) {
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
        return "PasswordRecoveryModel{" +
                "email='" + email + '\'' +
                '}';
    }
}
