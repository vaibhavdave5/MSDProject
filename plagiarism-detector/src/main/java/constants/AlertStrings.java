package constants;

public class AlertStrings {
	public static final String NO_DIRECTORY_SELECTED_MESSAGE = "Make sure to select a directory containing the student repo directories in the format\n" +
			"student-<number>\nE.g.\nstudent-101\nstudnet-104\n... \n";

	public static final String ONLY_ONE_STUDENT_SELECTED_MAKE_SURE_TO_SELECT_ATLEAST_TWO_STUDENTDS = "Only one student selected. Make sure to select atleast two studentds.";

	public static final String HOMEWORK_NUMBER_MESSAGE = "Make sure to enter a homework number in the format" +
			"HW<number>\nE.g.\nHW2\nHW3\n...\n";

	public static final String NO_EXEL_FILE_MESSAGE = "Please select an excel file which maps student ID to the the student names " +
			"and their email addresses.\n" + "The excel file should be in following format.\n"
			+ "ID | name | email\n" + "101 | John Doe | john@gmail.com\n" + "102 | Alice Rup | alice@yahoo.com\n...";
}
