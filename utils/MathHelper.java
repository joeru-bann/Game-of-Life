package utils;

import java.util.Random;

public class MathHelper {

	private static final Random RANDOM = new Random();
	
	public static boolean randomBoolean() {
		return RANDOM.nextBoolean();
	}
}