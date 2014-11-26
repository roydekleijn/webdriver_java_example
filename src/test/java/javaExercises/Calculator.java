package javaExercises;

import org.testng.annotations.Test;

public class Calculator {

	@Test
	public void calculate() {
		System.out.println(sum(7, 8));
		
		System.out.println(multiply(4, 5));
	}
	
	public int sum(int a, int b) {
		return a + b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
}
