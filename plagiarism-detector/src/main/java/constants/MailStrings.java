package constants;

public class MailStrings {
	private MailStrings() {

	}

	/**
	 * The default subject line for mails to suspected students
	 */
	public static final String SUBJECT_FOR_STUDENTS =  "URGENT: See me at my office";

	/**
	 * The default body for mails to suspected students
	 */
	public static final String BODY_FOR_STUDENTS = "Hello Students,\n\n" +
			"Please stop by my office tomorrow.\n" +
			"I want to discuss something important." +
			"\nThanks." +
			"\n\nBest," +
			"\nProfessor X";
}
