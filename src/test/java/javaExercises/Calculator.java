package javaExercises;

import org.testng.annotations.Test;

public class Calculator {

	@Test
	public void calculate() {
		System.out.println(this.sum(7, 8));

		System.out.println(this.multiply(4, 5));
	}

	public int sum(final int a, final int b) {
		return a + b;
	}

	public int multiply(final int a, final int b) {
		return a * b;
	}
}
