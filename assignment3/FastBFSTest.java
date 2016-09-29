package assignment3;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;
import java.util.*;

public class FastBFSTest {

	@Test
	public void test() {
		long startTime=System.currentTimeMillis();
		Main.initialize();
		long diff=Math.abs(startTime-System.currentTimeMillis());
		System.out.println(diff);
		Scanner kb = new Scanner(System.in);	// input Scanner for commands
		ArrayList<String> input;
		while(true){
			System.out.println("inputpls");
			input = Main.parse(kb);
			startTime = System.currentTimeMillis();
			ArrayList<String> ladder = Main.getWordLadderBFSFFast(input.get(0), input.get(1));
			diff=Math.abs(startTime-System.currentTimeMillis());
			System.out.println(diff);
			Main.printLadder(ladder);
		}
	}

}
