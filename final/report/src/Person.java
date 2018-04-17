import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A Person represents a real-life person. 
 */
class Person implements Comparable<Person> {
	private String name;
	private int age;
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
    	public int compareTo(Person other) {
		int nameAsc = this.name.compareTo(other.name);
		int ageDesc = other.age - this.age;
		return ageDesc != 0 ? ageDesc : nameAsc;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Person)
			&& (((Person)o).name.equals(this.name))
			&& (((Person)o).age == this.age);
	}

	@Override
	public int hashCode() {
		return this.name.length(); 
	}

	public void print() {
		System.out.println(this.name + " " + this.age);
	}
}

class Driver {
	public static void main(String args[]) {
		List<Person> people = new ArrayList<>();
		Person shail = new Person("Shail", 23);
		Person shail2 = new Person("Shail", 23);
		System.out.println(shail.equals(shail2));
		people.add(new Person("Shail", 23));
		people.add(new Person("Mike", 50));
		people.add(new Person("Rakesh", 50));
		Collections.sort(people);
		people.forEach(Person::print);
	}
}