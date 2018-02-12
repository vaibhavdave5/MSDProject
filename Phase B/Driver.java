package src;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

/**
* It is the entry point of the application.
* It is responsible for communication to various modules of the system.
* 
* @author  team-107
* @version 1.0
* @since   2018-02-10 
*/


public interface Driver {
	
	/**
	 * Given the student directory paths, generate and return Student objects.
	 * @param paths A set of paths, with each path pointing to a student directory
	 * @return A list of Student objects
	 */
	public List<Student> getStudentSubmissions(Set<Path> paths);	
	
	/**
	 * Given a list of Student objects, return an object that describes highly 
	 * suspicious and moderately suspicious student pairs.
	 * The object also contains a list of students whose submissions are not 
	 * suspected of copying.
	 * 
	 * @param  a list of students
	 * @return an SummaryInterface object that divides each possible student pair based on level of suspicion.
	 */
	public SummaryInterface viewSummary(List<Student> students);
	
	
	/**
	 * Given a student pair, returns an object of type ReportInterface.
	 * @param studentPair StudentPair
	 * @return report ReportInterface
	 */
	public ReportInterface generateReport(StudentPair studentPair);
	
	/**
	 * Given a report, saves the report in .pdf format at the specified path.
	 * @param report 
	 */
	public void saveReport(ReportInterface report);
	
	/**
	 * Given a report, email it to the authorities .
	 * @param report 
	 */
	public void emailReport(ReportInterface report, String ...reciepiants);
	
	/**
	 * Get the names of the students in the student pair.
	 * @param studentPair
	 * @return a string array of length 2 with the student names 
	 */
	public String[] revealNames(StudentPair studentPair);
	
	
	/**
	 * Change the settings as per the recommendations.
	 * @return a {@link SettingsInterface}. 
	 */
	public SettingsInterface viewSettings();
	
	
}
