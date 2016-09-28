package assignment3;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class BFSTest {

	@Test
	public void test() {
		Scanner kb = new Scanner(System.in);
		ArrayList<String> input = Main.parse(kb);
		ArrayList<String> ladder = Main.getWordLadderBFS(input.get(0), input.get(1));
		Main.printLadder(ladder);
	}

}
