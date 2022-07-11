package model;

public class ClientDTO {
	private String clientId;
	private String clientPassword;
	private String clientName;
	private String clientEmail;
	public ClientDTO() {
		super();
	}
	
	public ClientDTO(String clientId, String clientPassword, String clientName, 
			String clientEmail) {
		this.clientId = clientId;
		this.clientPassword = clientPassword;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
	}
	
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	@Override
	public String toString() {
		return "ClientDTO[clientId = "+ clientId + ", clientPassword = "+ clientPassword +
				", clientName = "+ clientName + ", clientEmail = "+ clientEmail + "]";
	}
}
