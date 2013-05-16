package serverMonitoring.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Employee entity model with constructor
 * validation setting
 * Hibernate table creation settings
 */
@Entity
@Table(name = "employee_entity", catalog = "server_monitoring_db")
public class EmployeeEntity implements Serializable {

    /*
     * a unique identifier of the entity.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /*
     * Name of the employee.
     */
    //@NotEmpty(message = "{employee_name.required}")
    //@Length(min = 3, message = "{employee_name.length}")
    //@Pattern(regexp = "^[a-zA-Z0-9_-,]$", message = "{employee_name.content}")
    @Column(name = "employee_name", nullable = false, length = 20)
    private String employee_name;

    /*
     * a unique userName for login.
     */
    //@NotEmpty(message = "{login.required}")
    //@Length(min = 5, max = 16, message = "{login.length}")
    //@Pattern(regexp = "^[a-zA-Z0-9_-]{5,16}$", message = "{login.content}")
    @Column(name = "login", nullable = false, length = 20)
    private String login;

    /*
     * employee password hash (SHA-2 algorithm).
     */
    //@NotEmpty(message = "{password.required}")
    @Length(min = 5, max = 50, message = "{password.length}")
    //@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])"
    //        + "(?=.*[@#$%]).{5,16})", message = "{password.content}")
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    /*
     * e-mail address to send notifications.
     */
    //@NotEmpty(message = "{email.required}")
    //@Length(min = 5, max = 16, message = "{email.length}")
    //    !!! !!! !!! check regular expression for e-mail    !!! !!! !!!
    //@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])"
    //        + "(?=.*[@#$%]).{5,16})", message = "{email.content}")
    @Column(name = "email", nullable = false, length = 20)
    private String email;

    /*
     * date of creating an employee.
     */
    @Column(name = "created", nullable = false, length = 15)
    private Timestamp created;

    /*
     * date of last login.
     */
    @Column(name = "lastLogin", nullable = false, length = 15)
    private Timestamp lastLogin;

    /*
     * Black List, an employee with a value of  "0" - can't log in.
     * 0 = not active
     * 1 = active
     */
    @Column(name = "active", nullable = false)
    private Integer active;

    /*
     * Access level.
     * 0 = regular role
     * 1 = admin role
     */
    //@NotEmpty(message = "{admin.required}")
    //@Pattern(regexp = "^[0,1]{1}$")
    @Column(name = "admin", nullable = false)
    private Integer admin;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Long id, String employee_name, String login, String password,
                          String email, Timestamp created, Timestamp lastLogin,
                          Integer active, Integer admin) {
        this.id = id;
        this.employee_name = employee_name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.created = created;
        this.lastLogin = lastLogin;
        this.active = active;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object foo) {
        if (this == foo) return true;
        else if (!(foo instanceof EmployeeEntity)) return false;
        EmployeeEntity that = (EmployeeEntity) foo;
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", lastLogin=" + lastLogin +
                ", active=" + active +
                ", admin=" + admin +
                '}';
    }
}
