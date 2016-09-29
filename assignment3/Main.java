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
	
	static String startEmpty;
	static String endEmpty;
	static ArrayList<ArrayList<Integer>> adjacentList;
	static ArrayList<String> dict;
	
	// static variables and constants only here.
	public static void main(String[] args) throws Exception {
		
		//intialize everything and the scanner 
		initialize();
		Scanner kb = new Scanner(System.in);	// input Scanner for commands
		
		while(true){
			ArrayList<String> input = parse(kb);
			printLadder(getWordLadderDFS("HELPS","HEARD"));//Heard->Helps
		}
		
	}
	/*
	 * initializes an adjacency list for our dictionary 
	 * it takes a long time to process (~1 minute) but it makes everything very fast 
	 * 
	 */
	public static void initialize() {
		
		//make a dictionary list 
		dict = makeDictionaryList();
		
		//initialize the adjacency list
		adjacentList = new ArrayList<ArrayList<Integer>>();
		
		//intialize it 
		for(int i = 0; i<dict.size(); i++){
			adjacentList.add(new ArrayList<Integer>());
		}
	
		//iterate through all the words to find its adjacent words 
		for(int wordCount = 0; wordCount<dict.size(); wordCount++ ){
			
			//for all the words in the dictionary check if adjacent
			for(int connectCount = wordCount+1 ; connectCount<dict.size(); connectCount++ ){
				
				//they are adjacent, so add to their corresponding lists
				if(adjacent(dict.get(wordCount), dict.get(connectCount))){
					adjacentList.get(wordCount).add(connectCount);
					adjacentList.get(connectCount).add(wordCount);
				}
			}
		}
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
		input.add(startWord.toUpperCase());
		input.add(endWord.toUpperCase());	
		
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
		
    	//global fields for printing 
    	startEmpty = start;
    	endEmpty = end;
    	
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
					//if word of the dictionary is a adjacent of parent and not already in queue
					if(adjacent(parent, newWord) && !queue.contains(newWord)){
						
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
		
		//reverse it 
		Collections.reverse(finalLadder);
		
		return finalLadder;
		
	}
    
	/*
	 * Tries to find a word ladder between the start word and the end word
	 * Uses breadth first search algorithm and our adjacentList 
	 * @param String start word of ladder, String end word of ladder 
	 * @return ArrayList of the ladder between start and end 
	 */
    public static ArrayList<String> getWordLadderBFSFFast(String start, String end) {
		
    	//global fields for printing 
    	startEmpty = start;
    	endEmpty = end;
    	
		//check if they are of equal length, if not there's no word ladder 
		if(start.length()!=end.length()){
			return null;
		}
		
		//make our queue and add the start word to it
		ArrayList<Integer> indexQueue = new ArrayList<Integer>();
		indexQueue.add(dict.indexOf(start));
		
		//make an array to keep track of parentage (start word has no parent)
		ArrayList<Integer> parents = new ArrayList<Integer>();
		parents.add(-1);
		
		//iterate through our queue 
		boolean foundEnd = false;
		for(int queueCount = 0; queueCount<indexQueue.size() && !foundEnd; queueCount++){
			
			//get adjacent words to the queue word
			ArrayList<Integer> listConnections = adjacentList.get(indexQueue.get(queueCount));
			
			//add all adjacent words to queue 
			for(int newWordCount = 0; newWordCount<listConnections.size(); newWordCount++){
				
				//if not in queue already
				int newWordIndex = listConnections.get(newWordCount);
				if(!indexQueue.contains(newWordIndex)){
					
					//add new word to queue 
					indexQueue.add(newWordIndex);
					parents.add(queueCount);
					String newWord = dict.get(newWordIndex);
					
					//check if we found the end of the ladder 
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
		int ladder = indexQueue.size() - 1;
		String toAdd = dict.get(indexQueue.get(ladder));
		while(ladder>0){
			finalLadder.add(toAdd);
			ladder = parents.get(ladder);
			toAdd = dict.get(indexQueue.get(ladder));
		}
		
		//add the start word
		finalLadder.add(toAdd);
		
		//reverse it and return
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
     * Builds a set of words included in the dictionary 
     * The dictionary id used to search valid words for a ladder 
     * @return: Set<String> of all the dictionary words 
     */
	public static ArrayList<String> makeDictionaryList () {
		
		//make array list
		ArrayList<String> words = new ArrayList<String>();
		Scanner infile = null;
		
		//look for file 
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) { //throw exception if no file was found 
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		
		//add all words in dictionary
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		
		//return array list
		return words;
	}
	
	
	/* 
	 * prints the word ladder between two words 
	 * @param: ArrayList<string> ladder between the words 
	 */
	public static void printLadder(ArrayList<String> ladder) {
		
		//deal with no ladder 
		if( ladder.size() == 0){
			System.out.println("no word ladder can be found between " + startEmpty.toLowerCase() + " and " + endEmpty.toLowerCase() + ".");
			return;
		}
		
		//get start and end 
		String startWord = ladder.get(0);
		String endWord = ladder.get(ladder.size() - 1 );
		
		//print initial message 
		System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + startWord.toLowerCase() + " and " + endWord.toLowerCase() + ".");
		
		//print all the words in our ladder 
		for(int i=0;i<ladder.size();i++){
			System.out.println(ladder.get(i).toLowerCase());
		}
	}
	
	/*
	 * Checks to see if a word is adjacent to another word
	 * @param: String word we start with, String adjacent word that we want to check
	 * @return: boolean if it is adjacent
	 */
	private static boolean adjacent(String word, String adjacent){
		
		//iterate through all the letters
		for(int index = 0; index<word.length(); index++){
			
			//if Strings are equal aside from a the letter in index, it is a valid adjacent
			String changedWord= word.substring(0, index) + word.substring(index+1, word.length());
			String changedadjacent= adjacent.substring(0, index)+ adjacent.substring(index+1, adjacent.length()); 	
			if(changedWord.equals(changedadjacent)){
				return true;
			}
		}
		
		//word is not adjacent
		return false;
	}
}
