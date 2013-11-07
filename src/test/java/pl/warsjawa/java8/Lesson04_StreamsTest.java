package pl.warsjawa.java8;

import org.junit.Ignore;
import org.junit.Test;
import pl.warsjawa.java8.people.Person;
import pl.warsjawa.java8.people.Phone;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;
import static pl.warsjawa.java8.people.Sex.FEMALE;
import static pl.warsjawa.java8.people.Sex.MALE;

/**
 * - What is Stream<T>
 * - More complex operations on Stream, (map, filter, forEach, sorted)
 * - Only toList() Collector
 */
public class Lesson04_StreamsTest {

	public static final List<Person> PEOPLE = Arrays.asList(
			new Person("Jane", FEMALE, 62, 169, LocalDate.of(1986, Month.DECEMBER, 21), new Phone(10, 555100200)),
			new Person("Bob", MALE, 71, 183, LocalDate.of(1982, Month.FEBRUARY, 5), new Phone(10, 555100201)),
			new Person("Steve", MALE, 85, 191, LocalDate.of(1980, Month.MAY, 4), new Phone(11, 555100200), new Phone(11, 555100201), new Phone(11, 555100202)),
			new Person("Alice", FEMALE, 54, 178, LocalDate.of(1984, Month.AUGUST, 17), new Phone(12, 555100202)),
			new Person("Eve", FEMALE, 61, 176, LocalDate.of(1987, Month.FEBRUARY, 9), new Phone(10, 555100200))
	);

	@Test
	public void shouldFindFemales() {
		final boolean anyFemale = PEOPLE.
				stream().
				anyMatch(p -> p.getSex() == FEMALE);

		assertThat(anyFemale).isTrue();
	}

	@Test
	public void shouldReturnNamesSorted() {
		final List<String> names = PEOPLE.
				stream().
				map(Person::getName).
				sorted().
				collect(toList());

		assertThat(names).containsExactly("Alice", "Bob", "Eve", "Jane", "Steve");
	}

	/**
	 * Are all people below 80 kg?
	 */
	@Test
	public void areAllPeopleSlim() {
		final boolean allSlim = PEOPLE.stream().allMatch(p -> p.getWeight() < 80);

		assertThat(allSlim).isFalse();
	}

	@Test
	public void findTallestPerson() {
		final Optional<Person> max = PEOPLE.stream().max((o1, o2) -> o1.getHeight() - o2.getHeight());

		assertThat(max.get()).isEqualTo(PEOPLE.get(2));
	}

	@Test
	public void countMales() {
		final long malesCount = PEOPLE.stream().filter(p -> p.getSex() == MALE).count();

		assertThat(malesCount).isEqualTo(2);
	}

	@Test
	public void twoOldestPeople() {
		final List<Person> oldest = PEOPLE.stream()
                .sorted((o1, o2) -> o1.getDateOfBirth().compareTo(o2.getDateOfBirth()))
                .limit(2).collect(toList());

		assertThat(oldest).containsExactly(PEOPLE.get(2), PEOPLE.get(1));
	}

	@Test
	public void totalWeight() {
		final int totalWeight = PEOPLE.stream().mapToInt(p -> p.getWeight()).sum();

		assertThat(totalWeight).isEqualTo(333);
	}

	@Test
	public void findUniqueCountryCodes() {
        Stream<Phone> phoneStream = PEOPLE.stream().flatMap(p -> p.getPhoneNumbers().stream());
        final List<Integer> distinctCountryCodes = phoneStream.map(p -> p.getCountryCode()).distinct().collect(toList());

		assertThat(distinctCountryCodes).containsOnly(10, 11, 12);
	}

	/**
	 * For each person born after LocalDate.of(1985, Month.DECEMBER, 25) add name to 'names'
	 */
	@Test
	public void forEachYoungPerson() {
		List<String> names = new ArrayList<>();

        PEOPLE.stream().filter(p -> p.getDateOfBirth().compareTo(LocalDate.of(1985, Month.DECEMBER, 25)) > 0)
                .map(Person::getName)
                .forEach(names::add);

		// PEOPLE.stream()...forEach()

		assertThat(names).containsExactly("Jane", "Eve");
	}

}
