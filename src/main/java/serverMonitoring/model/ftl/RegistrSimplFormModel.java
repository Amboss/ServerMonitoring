package serverMonitoring.model.ftl;

import java.io.Serializable;

/**
 * class formSingleSelect for employee registration page
 */
public class RegistrSimplFormModel implements Serializable {

    private String state;

    private String level;

    private String activeState;

    public RegistrSimplFormModel() {
    }

    public RegistrSimplFormModel(String state, String level, String activeState) {
        this.state = state;
        this.level = level;
        this.activeState = activeState;
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

    /**
     * activeState setter & getter
     */
    public String getActiveState() {
        return activeState;
    }

    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }

    @Override
    public String toString() {
        return "RegistrSimplFormModel{" +
                "state='" + state + '\'' +
                ", level='" + level + '\'' +
                ", activeState='" + activeState + '\'' +
                '}';
    }
}