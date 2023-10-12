package pl.miq3l.EmpikApp.domain.userclick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserClick {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_COUNT")
    private Long requestCount;

    public UserClick() {
    }

    public UserClick(Long id, String login, Long requestCount) {
        this.id = id;
        this.login = login;
        this.requestCount = requestCount;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Long getRequestCount() {
        return requestCount;
    }
}
