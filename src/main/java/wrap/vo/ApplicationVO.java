package wrap.vo;

public class ApplicationVO {

	private String customerId;
	private String command = "UploadFile";
	private String timestamp ;
	private String environment = "PRODUCTION";
	private String targetId ;
	private String softwareId ="Soft X Version 1";
	private String fileType = "NDCAPXMLI";

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String dateStr) {
		this.timestamp = dateStr;
	}

	public String getCustomerId() {
		return customerId;
	}

	// send Id
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTargetId() {
		return targetId;
	}

	// sign id 
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

}
