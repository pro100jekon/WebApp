package com.epam.smyrnov.mail;

import com.epam.smyrnov.util.HashingSha256;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailVerification {

	public static void sendVerificationMail(String recipientEmail, Properties applicationProperties) {
		String senderEmail = applicationProperties.getProperty("mail.smtp.authentication.sender.email");
		String senderPassword = applicationProperties.getProperty("mail.smtp.authentication.sender.password");
		String emailMessage = generateMessage(recipientEmail, applicationProperties);
		Session session = Session.getDefaultInstance(applicationProperties, new SmtpAuthenticator(senderEmail, senderPassword));
		MimeMessage message = new MimeMessage(session);
		try {
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(recipientEmail));
			message.setSubject("Registration verification");
			message.setText(emailMessage);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String generateMessage(String recipientEmail, Properties properties) {
		String link = HashingSha256.hash(recipientEmail);
		String appHost = properties.getProperty("app.host");
		String appPort = properties.getProperty("app.port");
		String appName = properties.getProperty("app.name");
		StringBuilder bodyText = new StringBuilder();
		bodyText.append("Press this link to proceed: ")
				.append(appHost).append(":").append(appPort).append("/")
				.append(appName).append("/verify?hash=").append(link);
		return bodyText.toString();
	}
}
