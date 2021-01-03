import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Print3 {
	public static void printPersons(List<Person> list,
			Predicate<Person> tester) {
		for (Person p : list)
			if (tester.test(p))
				p.printPerson();
	}

	public static void processPersons(List<Person> list,
			Predicate<Person> tester,
			Consumer<Person> action) {
		for (Person p : list)
			if (tester.test(p))
				action.accept(p);
	}

	public static void main(String[] args) {
		processPersons(Person.pList,
				p -> p.getGender() == Person.Sex.FEMALE,
				p -> System.out.println(p.getName()));
	}
}
