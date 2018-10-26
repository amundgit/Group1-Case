package api.Pojos;

public class SessionInfo {

    private Integer id;
    private String name;
    private String sessionId;

    public SessionInfo(Integer id, String name, String sessionId) {
       this.id = id;
       this.name = name;
       this.sessionId = sessionId;
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