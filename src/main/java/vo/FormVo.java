package vo;

import javax.swing.JTextField;

public class FormVo {
	private String pin;
	private String senderId;
	private String signId;
	private String executionDate;
	private String messageId;
	private String paymentId;
	private String instrutionId;
	private String endToEndId;

	public FormVo(JTextField[] certTextS, JTextField[] prefixsTexts) {
		this.pin = certTextS[1].getText();
		this.signId = certTextS[2].getText();
		this.senderId = certTextS[3].getText();
		this.executionDate = certTextS[4].getText();

		this.messageId = prefixsTexts[0].getText();
		this.paymentId = prefixsTexts[1].getText();
		this.instrutionId = prefixsTexts[2].getText();
		this.endToEndId = prefixsTexts[3].getText();
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getInstrutionId() {
		return instrutionId;
	}

	public void setInstrutionId(String instrutionId) {
		this.instrutionId = instrutionId;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

}
