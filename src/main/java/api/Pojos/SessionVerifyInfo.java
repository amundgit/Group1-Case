package api.Pojos;

public class SessionVerifyInfo {

    private String sessionId;
    private Integer role;

    public SessionVerifyInfo() {
    }

    public SessionVerifyInfo(String sessionId, Integer role) {
        this.sessionId = sessionId;
        this.role = role;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}