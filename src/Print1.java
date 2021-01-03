import java.util.List;

public class Print1 {

	public static void printOlderThan(List<Person> list,
			int age) {
		for (Person p : list)
			if (p.getAge() >= age)
				p.printPerson();
	}

	public static void printWithinAgeRange(
			List<Person> list, int low, int high) {
		for (Person p : list)
			if (low <= p.getAge() && p.getAge() < high)
				p.printPerson();
	}
	
	public static void main(String[] args) {
		printOlderThan(Person.pList, 100);
	}
}