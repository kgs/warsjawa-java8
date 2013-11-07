package pl.warsjawa.java8;

import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - What is a functional interface? @FunctionalInterface
 * - Using lambdas instead of plain old Java classes (JButton)
 */
public class Lesson03_FunctionalInterfacesTest {

	private final Random random = new Random();

	@Test
	public void testActionListenerLambda() {
		JButton button = new JButton();
		button.addActionListener(e -> {
            System.out.println(e.getModifiers());
            System.out.println(e.getActionCommand());
        });
	}

	@Test
	public void testRunnableLambda() {
		Runnable run = () -> {
            System.out.println("Runnable!");
        };
	}

	@Test
	public void testComparatorLambda() {
		final Comparator<String> strLenComparator = (o1, o2) -> Integer.compare(o1.length(), o2.length());

		assertThat(strLenComparator.compare("abc", "def")).isZero();
		assertThat(strLenComparator.compare("abc", "defg")).isLessThan(0);
		assertThat(strLenComparator.compare("abc", "de")).isGreaterThan(0);
	}

	@Test
	public void testCustomFunctionalInterface() {
		final RandomSource source = () -> random.nextInt(2) * 2 - 1;

		Supplier<Integer> sourceSupplier = null;

		assertThat(source.oneOrMinusOne()).isIn(-1, 1);
		assertThat(sourceSupplier.get()).isIn(-1, 1);
	}

}

