import java.util.List;
import java.util.function.Consumer;

public class References {

	public static void doAll(List<Person> l,
			Consumer<Person> c) {
		for (Person p : l)
			c.accept(p);
	}

	public static void printAge(Person p) {
		System.out.println(p.getAge());
	}

	public static void main(String[] args) {
		doAll(Person.pList, p -> References.printAge(p));
		// is the same as:
		doAll(Person.pList, References::printAge);
		// 95
		// 107
		// 91

		StringBuilder b1 = new StringBuilder();
		doAll(Person.pList, p -> b1.append(p));
		// is the same as:
		StringBuilder b2 = new StringBuilder();
		doAll(Person.pList, b2::append);
		System.out.println(b2);
		// Person@66a29884Person@4769b07bPerson@cc34f4d

		doAll(Person.pList, p -> p.printPerson());
		// is the same as:
		doAll(Person.pList, Person::printPerson);
		// Malcolm X, MALE, age 95
		// Rosa Parks, FEMALE, age 107
		// Martin Luther King, MALE, age 91
	}
}
