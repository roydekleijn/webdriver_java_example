package javaExercises;

import org.testng.annotations.Test;

public class CalculatorAdvanced {

	@Test
	public void calcTest() {
		System.out.println(calculate(Operator.MULTIPLY, 10, 7));
	}

	public enum Operator {
		ADD, SUBSTRACT, MULTIPLY, DIVIDE
	}

	private int calculate(Operator operator, int a, int b) {
		int result = 0;
		if (operator.equals(Operator.ADD)) {
			result = a + b;
		} else if (operator.equals(Operator.SUBSTRACT)) {
			result = a - b;
		} else if (operator.equals(Operator.MULTIPLY)) {
			result = a * b;
		} else if (operator.equals(Operator.DIVIDE)) {
			result = a / b;
		}
		return result;
	}
}
