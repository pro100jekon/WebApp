package com.epam.smyrnov.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
	private String senderEmail;
	private String accountPassword;

	public SmtpAuthenticator(String senderEmail, String accountPassword) {
		this.senderEmail = senderEmail;
		this.accountPassword = accountPassword;
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(senderEmail, accountPassword);
	}
}

