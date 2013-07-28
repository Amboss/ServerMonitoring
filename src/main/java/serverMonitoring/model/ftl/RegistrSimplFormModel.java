package serverMonitoring.model.ftl;

import java.io.Serializable;

/**
 * class formSingleSelect for employee registration page
 */
public class RegistrSimplFormModel implements Serializable {

    private String state;

    private String level;

    public RegistrSimplFormModel() {
    }

    public RegistrSimplFormModel(String state, String level) {
        this.state = state;
        this.level = level;
    }

    /**
     * state setter & getter
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * level setter & getter
     */
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "RegistrSimplFormModel{" +
                "state='" + state + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}