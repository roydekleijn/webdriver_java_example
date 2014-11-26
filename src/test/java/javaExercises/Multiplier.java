package javaExercises;

import org.testng.annotations.Test;

public class Multiplier {

	@Test
	public void multiply() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				System.out.println(i + " x " + j + " = " + i * j);
			}
		}
	}

}
