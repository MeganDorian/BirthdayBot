package phoenix.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users", schema = "bdays")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "user_name")
    private String userName;
    private String login;
    private String password;
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_activity")
    private Date lastActivity;

    public int getId() {
        return id;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLastActivity(Date date) {
        this.lastActivity = date;
    }

    public Date getLastActivity() {
        return this.lastActivity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return id+" "+userName+" "+login+" "+password+" "+lastActivity+" "+status;
    }
}