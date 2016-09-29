package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
public class BFSSpecificTests {

	@Test
	public void longBFSLadder() {
		String start = "SMART";
		String end = "MONEY";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderBFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		if (ladder.size()!= 11){
			fail("Not correct size");
		}
	}
	
	@Test
	public void emptyLadderA() {
		String start = "SMART";
		String end = "ALOOF";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderBFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		if (ladder.size()!= 0){
			fail("Not correct size");
		}
	}
	
	@Test
	public void emptyLadderB() {
		String start = "JAZZY";
		String end = "MONEY";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderBFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		if (ladder.size()!= 0){
			fail("Not correct size");
		}
	}
	
	@Test
	public void zeroRungA() {
		String start = "SMART";
		String end = "START";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderBFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		if (ladder.size()!= 2){
			fail("Not correct size");
		}
	}
	
	@Test
	public void normalLadder() {
		String start = "SMART";
		String end = "BRAIN";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderBFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		if (ladder.size()!= 9){
			fail("Not correct size");
		}
	}

}
