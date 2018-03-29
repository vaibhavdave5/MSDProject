package utils;

import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * Utilities for sending emails
 * @author Shail Shah
 */
public class MailUtils {
	private static final Logger logger = Logger.getLogger(MailUtils.class);

	private MailUtils(){}
	/**
	 * Send a mail by opening a new email client
	 * @param recipient the recipient of the email
	 * @param subject the subject of the email
	 * @param body the body of the email
	 * @throws RuntimeException
	 */
	public static void sendMail(String recipient, String subject, String body) throws IOException, URISyntaxException {
		if(!Desktop.isDesktopSupported()){
			String errorString = "No support for Desktop";
			logger.error(errorString);
			throw new UnsupportedOperationException(errorString);
		}

		Desktop desktop = Desktop.getDesktop();

		if(!desktop.isSupported(Desktop.Action.MAIL)) {
			String errorString = "Mailing is not supported";
			logger.error(errorString);
			throw new UnsupportedOperationException(errorString);
		}

		try {
			URI mailto = getURI(recipient, subject, body);
			desktop.mail(mailto);
		} catch(Exception e) {
			logger.error(e);
			throw e;
		} 
	}

	/**
	 * Generate the URI containing metadata about the email
	 * @param recipient the recipient of the email
	 * @param subject the subject of the email
	 * @param body the body of the email
	 * @return the URI containing metadata about the email
	 * @throws URISyntaxException
	 */                           
	private static URI getURI(String recipient, String subject, String body) throws URISyntaxException {
		if(!isValidEmail(recipient))
			throw new IllegalArgumentException("Email address of recipient isn't valid");

		subject = encodeText(subject);
		body = encodeText(body);

		return new URI(String.format("mailto:%s?subject=%s&body=%s", recipient, subject, body));
	}

	private static String encodeText(String text){
		String encodedText = null;
		try {
			encodedText = URLEncoder.encode(text, "UTF-8")
					.replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(")
					.replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return encodedText;
	}

	/**
	 * Derived from RFC 5322 standard
	 * https://www.regular-expressions.info/email.html
	 *
	 * @param email a string containing a potential email address
	 * @return true if the provided string is an email
	 */
	public static boolean isValidEmail(String email) {
		String validEmailPattern = "\\A[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z";
		return email != null
				&& email.matches(validEmailPattern);
	}
}
