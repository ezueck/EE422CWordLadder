package assignment3;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

public class ParseTest {

	@Test
	public void parseTest() {
		Scanner kb = new Scanner(System.in);
		ArrayList<String> test = Main.parse(kb);
		System.out.println(test.size());
	}

}
