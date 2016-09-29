package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DFSSpecificTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void DFSLadder1() {
		String start = "SMART";
		String end = "MONEY";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderDFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		System.out.println("DFSLadder1 "+diff);
	
	}
	
	@Test
	public void emptyLadderDFS1() {
		String start = "SMART";
		String end = "ALOOF";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderDFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		System.out.println("emptyLadderDFS1 "+diff);
	
	}
	
	@Test
	public void emptyLadderDFS2() {
		String start = "JAZZY";
		String end = "MONEY";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderDFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		if (ladder.size()!= 0){
			fail("Not correct size");
		}
		System.out.println("emptyLadderDFS2 "+ diff);
	}
	
	@Test
	public void zeroRungDFS() {
		String start = "SMART";
		String end = "START";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderDFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		System.out.println("zeroRungDFS "+diff);

	}
	
	@Test
	public void DFSLadder2() {
		String start = "SMART";
		String end = "BRAIN";
		Main.initialize();
		long startTime=System.currentTimeMillis();
		ArrayList<String> ladder = Main.getWordLadderDFS(start, end);
		long diff=Math.abs(startTime-System.currentTimeMillis());
		if (diff>30000){
			fail("Too slow");
		}
		System.out.println("DFSLadder2 "+diff);

	} 
	@Test
	public void DFSLadder3(){
		
		
		
		
	}
}
