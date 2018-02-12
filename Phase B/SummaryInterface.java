package src;

import java.util.Set;

/**
* 
* The SummaryInterface object helps in catagorizing the students in green(safe), yellow or red(danger) sets.
* 
* @author  team-107
* @version 1.0
* @since   2018-02-10 
*/


public interface SummaryInterface {

	/**
	 * returns the set of student pairs with considerably similar code.
	 * @return studentPairs Set<StudentPair>
	 */
	public Set<StudentPair> getHighProbabilityStudentsPairs();
	
	/**
	 * returns a set of student pairs with moderately similar code.
	 * @return studentPairs Set<StudentPair>
	 */
	public Set<StudentPair> getModerateProbabilityStudentsPairs();
	
	/**
	 * returns the set of students no similar code.
	 * 
	 * @return students Set<Student>
	 */
	public Set<Student> getNoProbabilityStudents();
	
	/**
	 * Accepts a set of student pairs and returns the set of students no similar code.
	 * @param studentPairs Set<StudentPair>
	 * @return students Set<Student>
	 */
	public Set<StudentPair> setHighProbabilityStudentsPairs(Set<StudentPair> studentPairs);
	
	/**
	 * Accepts a set of student pairs and returns the set of students no similar code.
	 * @param studentPairs Set<StudentPair>
	 * @return students Set<Student>
	 */
	public Set<StudentPair> setModerateProbabilityStudentsPairs(Set<StudentPair> studentPairs);
	
	/**
	 * Accepts a set of student pairs and returns the set of students no similar code.
	 * @param studentPairs Set<StudentPair>
	 * @return students Set<Student>
	 */
	public Set<Student> setNoProbabilityStudents(Set<Student> student);
	
}
