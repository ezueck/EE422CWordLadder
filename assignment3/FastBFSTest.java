package assignment3;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;
import java.util.*;

public class FastBFSTest {

	@Test
	public void test() {
		Main.initialize();
		Scanner kb = new Scanner(System.in);	// input Scanner for commands
		ArrayList<String> input;
		while(true){
			System.out.println("inputpls");
			input = Main.parse(kb);
			ArrayList<String> ladder = Main.getWordLadderBFSFFast(input.get(0), input.get(1));
			Main.printLadder(ladder);
		}
	}

}
