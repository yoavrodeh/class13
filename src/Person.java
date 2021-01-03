import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Person {

	public enum Sex {
		MALE, FEMALE
	}

	private String name;
	private LocalDate birthday;
	private Sex gender;

	public Person(String name, Sex gender,
			LocalDate birthday) {
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
	}

	public Sex getGender() {
		return gender;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return (int) birthday.until(LocalDate.now(),
				ChronoUnit.YEARS);
	}

	public void printPerson() {
		System.out.format("%s, %s, age %d%n", 
				name, gender, getAge());
	}

	public static List<Person> pList = Arrays.asList(
			new Person("Malcolm X", Sex.MALE,
					LocalDate.parse("1925-05-19")),
			new Person("Rosa Parks", Sex.FEMALE,
					LocalDate.parse("1913-02-04")),
			new Person("Martin Luther King", Sex.MALE,
					LocalDate.parse("1929-01-15")));

	public static void main(String[] args) {
		for (Person p : pList)
			p.printPerson();
	}
}