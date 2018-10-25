package api.Pojos;

public class UserPojo {

    private Integer id;
    private String name;
    private String sessionId;
    private Integer role;

    public UserPojo(Integer id, String name, String sessionId) {
       this.id = id;
       this.name = name;
       this.sessionId = sessionId;
    }

    public UserPojo(Integer role) {
       this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSessionId(){
    	return sessionId;
    }

    public void setSessionId(String sessionId){
    	this.sessionId = sessionId;
    }

}