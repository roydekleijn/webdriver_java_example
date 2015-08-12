package javaExercises;

import org.testng.annotations.Test;

public class IfStatement {

	@Test
	public void passOrFail() {
		final int score = 40;

		if (score >= 65) {
			System.out.println("Je bent geslaagd");
		} else {
			System.out.println("Je bent gezakt");
		}
	}
}
