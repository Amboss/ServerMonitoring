package serverMonitoring.model;

import java.io.Serializable;

/**
 * Object for password recovery page
 */
public class PasswordRecoveryObject implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
