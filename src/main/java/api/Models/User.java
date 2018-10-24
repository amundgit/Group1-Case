package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String password;

    private Integer role = 0;

    private String sessionId;

    private String status = "active";

    public User() {}

    public User(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }

    public String getSessionId(){
    	return sessionId;
    }

    public void setSessionId(String sessionId){
    	this.sessionId = sessionId;
    }

    public Integer getRole() {
		return role;
	}

	public void setRole(Integer id) {
		role = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}