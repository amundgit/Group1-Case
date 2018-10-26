package api;

public class Messages {

	private String error;
	private String message;
	private Integer role;

	public String getError() {
		return error;
	}

	public void setError(String error){
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role){
		this.role = role;
	}
	
}