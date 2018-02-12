package src;

import java.nio.file.Path;

/**
* 
* 
* @author  team-107
* @version 1.0
* @since   2018-02-10 
*/


public interface SettingsInterface {
	
	
	/**
	 * 
	 * @return the email of the professor that is set
	 */
	public String getProfEmail(); 
	
	/**
	 * 
	 * @param a String to set the profEmail
	 */
	public void setProfEmail(String profEmail); 
	
	/**
	 * 
	 * @return gives the ccis email set.
	 */
	public String getCcisEmail(); 
	
	/**
	 * 
	 * @param ccisEmail String 
	 */
	public void setCcisEmail(String ccisEmail);
	
	/**
	 * 
	 * @return email of OSCCR that is set
	 */
	public String getOsccrEmail();
	
	/**
	 * 
	 * @param osccrEmail String
	 */
	public void setOsccrEmail(String osccrEmail);
	
	/**
	 * 
	 * @return the path of excel file containing student data. 
	 */
	public Path getStudentDetailLocation(); 
	
	/**
	 * 
	 * @param set path of excel file containing student data. 
	 */
	public void setStudentDetailLocation(Path studentDetailLocation); 
	
	/**
	 * 
	 * @return the path of where report is supposed to be stored. 
	 */
	public Path getSaveReportLocation();
	
	/**
	 * 
	 * @param the path of where report is supposed to be stored. 
	 */
	public void setSaveReportLocation(Path saveReportLocation); 
	
}
