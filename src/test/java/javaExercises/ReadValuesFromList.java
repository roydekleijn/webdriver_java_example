package javaExercises;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class ReadValuesFromList {

	@Test
	public void readValues() {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(3);
		numbers.add(5);
		numbers.add(15);
		numbers.add(20);

		numbers.size();

		for (Integer number : numbers) {
			System.out.println(number);
		}

		List<String> text = new ArrayList<String>();
		text.add("I'm");
		text.add("learning");
		text.add("Java");
		text.add("right");
		text.add("now!");

		for (String str : text) {
			System.out.println(str);
		}
	}
}
