import java.util.List;

public class Print2 {

	interface CheckPerson {
		boolean test(Person p);
	}

	public static void printPersons(List<Person> list,
			CheckPerson tester) {
		for (Person p : list)
			if (tester.test(p))
				p.printPerson();
	}

	public static void main(String[] args) {
		class Check1 implements CheckPerson {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.FEMALE;
			}
		}
		printPersons(Person.pList, new Check1());

		System.out.println("-------------");
		printPersons(Person.pList, new CheckPerson() {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.FEMALE;
			}
		});

		System.out.println("-------------");
		printPersons(Person.pList, (Person p) -> {
			return p.getGender() == Person.Sex.FEMALE;
		});

		System.out.println("-------------");
		printPersons(Person.pList,
				p -> p.getGender() == Person.Sex.FEMALE);
	}

}
