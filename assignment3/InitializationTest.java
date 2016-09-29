package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class InitializationTest {

	@Test
	public void test() {
		long startTime=System.currentTimeMillis();
		Main.initialize();
		long diff=Math.abs(startTime-System.currentTimeMillis());
		System.out.println(diff);
	}

}
