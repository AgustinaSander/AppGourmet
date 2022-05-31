package domain;

public class Email {

	private static final String sender = "appgourmet@hotmail.com";
	private String recipient;
	private String content;
	private String subject;
	
	public Email(String recipient, String content, String subject) {
		this.recipient = recipient;
		this.content = content;
		this.subject = subject;
	}

	public static String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "Email [recipient=" + recipient + ", content=" + content + ", subject=" + subject + "]";
	}
}
