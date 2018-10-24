package api.Pojos;

public class SessionInfo{

    private String name;
    private String sessionId;

    public SessionInfo(String name, String sessionId){
       this.name = name;
       this.sessionId = sessionId;
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