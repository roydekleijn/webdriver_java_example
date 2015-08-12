package javaExercises;

import org.testng.annotations.Test;

public class Variables {

	@Test
	public void printVariables() {
		final int i = 2;
		final float f = 12.5f;
		final String text = "Training selenium WebDriver";

		System.out.println(i);
		System.out.println(f);
		System.out.println(text);
	}
}
