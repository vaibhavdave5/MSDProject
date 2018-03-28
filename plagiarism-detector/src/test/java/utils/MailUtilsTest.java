package utils;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for mail utilities
 * @author Shail Shah
 */
public class MailUtilsTest {

	/**
	 * Positive tests for checking if the provided string is valid
	 */
	@Test
	public void isValidEmailTrue() {
		Set<String> validEmails = new HashSet<>(Arrays.asList(
				"a@b.com",
				"a@bc.com",
				"email@domain.com",
				"john.doe@domain.com",
				"john@ccs.neu.edu",
				"john+spam@gmail.com",
				"john@123.123.123.123",
				"1234567890@mail.com",
				"email@mail-dash.com",
				"_______@underscore.com",
				"email@domain.name",
				"email@domain.co.in",
				"john-doe@gmail.com"));
		validEmails.forEach(e -> assertTrue(MailUtils.isValidEmail(e)));
	}

	/**
	 * Negative tests for checking if the provided string is valid
	 */
	@Test
	public void isValidEmailFalse() {
		Set<String> invalidEmails = new HashSet<>(Arrays.asList(
				null,
				"",
				"NoDomain",
				"(#*&%^@mail.com",
				"@mail.com",
				"John Doe<johndoe@mail.com>",
				"johnmail.com",
				"a",
				"a@b",
				"ab@c"));
		invalidEmails.forEach(e -> assertFalse(MailUtils.isValidEmail(e)));
	}

	/**
	 * Test sending a mail with proper parameters
	 * Remove (expected = RuntimeException.class) if running locally
	 * @throws IOException IOExceptions are thrown
	 * @throws URISyntaxException URISyntaxException are thrown
	 */

	/* Can't be tested on Jenkins, because Jenkins doesn't have a mail client
	@Test
	public void sendMailValidEmail() throws IOException, URISyntaxException {
		String recipient = "shail@ccs.neu.edu";
		String subject = "URGENT: See me at my office";
		String body = "Hello Student,\n\n" +
				"I need to talk to you about academic integrity. Please stop by my office tomorrow at 1 PM." +
				"\nThanks." +
				"\n\nBest," +
				"\nProfessor X\n";

		MailUtils.sendMail(recipient, subject, body);
	}
	*/



	/**
	 * Test sending a mail with improper recipient
	 * @throws IOException IOExceptions are thrown
	 * @throws URISyntaxException URISyntaxException are thrown
	 */
	@Test(expected = RuntimeException.class)
	public void sendMailInvalidEmail() throws IOException, URISyntaxException {
		String recipient = "(#*&%^@mail.com";
		String subject = "See me at my office";
		String body = "Hello Student,\n\n" +
				"I need to talk to you about academic integrity. Please stop by my office tomorrow at 1 PM." +
				"\nThanks." +
				"\n\nBest," +
				"\nProfessor X\n";

		MailUtils.sendMail(recipient, subject, body);
	}
}