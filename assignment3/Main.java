/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Eduardo Zueck Garces
 * ez2959
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */

package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		printLadder(getWordLadderDFS("HELPS","HEARD"));//Heard->Helps
		
	}
	
	public static void initialize() {
		
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		
		ArrayList<String> input = new ArrayList<String>();
		String startWord = keyboard.next();
		
		//return empty if quit 
		if(startWord.equals("/quit")){
			return input;
		}
		
		//add start/end words to ArrayList
		String endWord = keyboard.next();
		input.add(startWord);
		input.add(endWord);	
		
		return input;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		//Timing
		long startTime=System.currentTimeMillis();
		ArrayList<String> wordLadder=new ArrayList<String>();
		Set<String> dict = makeDictionary();
		LinkedList<String> result=DFS(start,end,new ArrayList<String>(),dict,0);
		if(result!=null && result.size()!=0){
			wordLadder.add(start);
			while(result.size()!=0){
				wordLadder.add(result.removeFirst());
			}
		}
		long diff=Math.abs(startTime-System.currentTimeMillis());
		System.out.println(diff);
		return wordLadder;
	}
	
	//Precondition: start,end same length, wordsHit initialized to empty arraylist,wordDict set
	public static LinkedList<String> DFS(String start, String end,ArrayList<String> wordsHit,Set<String> wordDict,int depth){
		depth++;
		//System.out.println(depth);
		if(start.length()!=end.length()){
			return null;
		}
		if(start.equals(end)){
			LinkedList<String> result=new LinkedList<String>();
			return result;
		}
		
		String changedStart;
		String changedDictWord;
		wordsHit.add(start);
		for(String s : wordDict){
			if(s.length()!=start.length() || wordsHit.indexOf(s)!=-1){
				continue;
				
			}
			for(Integer letter:optomize(s,end)){
			//for(int letter=0;letter<start.length();letter++){
				changedDictWord=s.substring(0, letter)+s.substring(letter+1,start.length());
				changedStart=start.substring(0, letter)+start.substring(letter+1,start.length());
				if(changedDictWord.equals(changedStart)){
					//System.out.println("---------------"+start);
					//System.out.println(s);
					LinkedList<String> result=DFS(s,end,wordsHit,wordDict,depth);
					if(result!=null){
						result.addFirst(s);
						return result;
					}
				}
			}	
		}
		return null;
	}
	
	private static ArrayList<Integer> optomize(String start,String end){
		ArrayList<Integer> indexesToIterate=new ArrayList<Integer>();
		ArrayList<Integer> otherIndexes=new ArrayList<Integer>();
		for(int i=0;i<start.length();i++){
			if(!start.substring(i,i+1).equals(end.substring(i,i+1))){
				indexesToIterate.add(i);
			}
			else{
				otherIndexes.add(i);
			}
		}
		indexesToIterate.addAll(otherIndexes);
		return indexesToIterate;
	}
	
	/*
	 * Tries to find a word ladder between the start word and the end word
	 * Uses breadth first search algorithm 
	 * @param String start word of ladder, String end word of ladder 
	 * @return ArrayList of the ladder between start and end 
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		Set<String> dict = makeDictionary();
		
		//check if they are of equal length, if not there's no word ladder 
		if(start.length()!=end.length()){
			return null;
		}
		
		//make our queue and add the start word to it
		ArrayList<String> queue = new ArrayList<String>();
		queue.add(start);
		
		//make an array to keep track of parentage (start word has no parent)
		ArrayList<Integer> parents = new ArrayList<Integer>();
		parents.add(-1);
		
		//iterate through our queue 
		boolean foundEnd = false;
		for(int queueCount = 0; queueCount<queue.size() && !foundEnd; queueCount++){
			String parent = queue.get(queueCount);
			
				for(String newWord : dict){
					//if word of the dictionary is a permutation of parent and not already in queue
					if(permutation(parent, newWord) && !queue.contains(newWord)){
						//add the word to the queue 
						queue.add(newWord);
						parents.add(queueCount);
						
						//check if we found the end of hte ladder 
						if(newWord.equals(end)){
							foundEnd = true;
							break;
						}
					}
				}		
			}
		
		//make our list for the ladder
		ArrayList<String> finalLadder = new ArrayList<String>();
		
		//if we didn't find a ladder just return null
		if(!foundEnd){
			return finalLadder;
		}
		
		//build our ladder 
		int ladder = queue.size() - 1;
		String toAdd = queue.get(ladder);
		while(ladder>0){
			finalLadder.add(toAdd);
			ladder = parents.get(ladder);
			toAdd = queue.get(ladder);
		}
		
		//final element (start word)
		finalLadder.add(queue.get(ladder));
		
		//reverse it 
		Collections.reverse(finalLadder);
		
		return finalLadder;
		
	}
    

    /*
     * Builds a set of words included in the dictionary 
     * The dictionary id used to search valid words for a ladder 
     * @return: Set<String> of all the dictionary words 
     */
	public static Set<String> makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	/* 
	 * prints the word ladder between two words 
	 * @param: ArrayList<string> ladder between the words 
	 */
	public static void printLadder(ArrayList<String> ladder) {
		System.out.println(ladder.size());
		for(int i=0;i<ladder.size();i++){
			System.out.println(ladder.get(i));
			
		}
	}
	
	/*
	 * Checks to see if permutations is a letter away from word 
	 * @param: String word we start with, String permutation that we want to check
	 * @return: boolean if permutation is a letter away
	 */
	public static boolean permutation(String word, String permutation){
		
		//iterate through all the letters
		for(int index = 0; index<word.length(); index++){
			
			//if Strings are equal aside from a the letter in index, it is a valid permutation
			String changedWord= word.substring(0, index) + word.substring(index+1, word.length());
			String changedPermutation= permutation.substring(0, index)+ permutation.substring(index+1, permutation.length()); 	
			if(changedWord.equals(changedPermutation)){
				return true;
			}
		}
		//couldn't find a way to change word to be permutation
		return false;
	}
}
