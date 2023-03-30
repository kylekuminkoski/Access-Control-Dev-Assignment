package access;

public class Session {
	String userName;
	String ownerName = null;
	boolean isOwnerViewing;
	
	public Session() {
		this.userName = null;
		this.isOwnerViewing = false;
	}
	
	
	public Session(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
		}
	
	public void setUserName(String userName) {
		 this.userName = userName;
		}
	
	public String getOwner() {
		
		return this.ownerName;
	}
	
	public void setOwner(String ownerName) {
		
		 this.ownerName = ownerName;
	}

	public boolean isSessionCreated() {
		return this.ownerName != null;
	}
	
	
	public boolean getOwnerViewing() {
		return this.isOwnerViewing;
	}
	
	public void setOwnerViewing(boolean bool) {
		this.isOwnerViewing = bool;
	}
	
	public Session logout() {
		
		return new Session();
	}
	
}


