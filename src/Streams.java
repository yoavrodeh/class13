import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args) {
		Stream<Person> s = Person.pList.stream();
		s.forEach(p -> System.out.print(p.getAge()+" "));
		System.out.println();
		// 95 107 91 

		s = Person.pList.stream();
		Stream<String> names = s.map(p -> p.getName());
		Stream<String> mNames = names
				.filter(n -> n.charAt(0) == 'M');
		List<String> mNamesList = mNames
				.collect(Collectors.toList());
		System.out.println(mNamesList);
		// [Malcolm X, Martin Luther King]

		System.out.println(
				Person.pList.stream().map(Person::getName)
						.filter(n -> n.charAt(0) == 'M')
						.collect(Collectors.toList()));
		// [Malcolm X, Martin Luther King]

		Person p1 = Person.pList.stream().filter(
				p -> p.getGender() == Person.Sex.FEMALE)
				.findFirst()
				.orElse(null);
		p1.printPerson();
		// Rosa Parks, FEMALE, age 107

		System.out.println(Person.pList.stream()
				.filter(p -> p.getAge() > 100)
				.count());
		// 1
		
		System.out.println(Person.pList.stream()
				.map(Person::getAge)
				.reduce(0, (a,b) -> a + b));
		// 293
	}
}
