// STUDENT_NAME: Mian Hamza
// STUDENT_ID: 260655263

import java.util.*;
import java.io.*;




public class Scrabble{

    static HashSet<String> myDictionary; // this is where the words of the dictionary are stored

    // DO NOT CHANGE THIS METHOD
    // Reads dictionary from file
    public static void readDictionaryFromFile(String fileName) throws Exception {
        myDictionary=new HashSet<String>();

        BufferedReader myFileReader = new BufferedReader(new FileReader(fileName) );

        String word;
        while ((word=myFileReader.readLine())!=null) myDictionary.add(word);

	myFileReader.close();
    }



    /* Arguments: 
        char availableLetters[] : array of characters containing the letters that remain available
        String prefix : Word assembled to date
        Returns: String corresponding to the longest English word starting with prefix, completed with zero or more letters from availableLetters. 
	         If no such word exists, it returns the String ""
     */
     public static String longestWord(char availableLetters[], String prefix) {
        
	 
	 String longest = "";
	 String newstring="";
	 String newer="";
	 if (myDictionary.contains(prefix)&&prefix.length()>longest.length()){
		 longest = prefix;}//updating prefix if it is already a word

	 //creating loop
	 for  (int i=0; i<availableLetters.length; i++){
		 
		 newstring= prefix + availableLetters[i];//adding new letter to prefix
		 char[] remLet= new char[availableLetters.length-1]; 
		 
		
		 for (int k=0;k<availableLetters.length; k++){
			 if (k<i){
			 remLet[k]=availableLetters[k];
			 }else if(k==i) {
				 k++;
			 } else if (k>i) {
				 remLet[k-1]=availableLetters[k];
			 } //creating new loop with remaining letters
		 }
		 newer= longestWord(remLet, newstring);//calling the method recursively
				 if (newer.length() > longest.length()){//
				 longest = newer;
				 } 		 //updating value of string with longest
	 }
	 
	 
	
	 return longest;//returning longest string


    }

    
    
    /* main method
        You should not need to change anything here.
     */
    public static void main (String args[]) throws Exception {
       
	// First, read the dictionary
	try {
	    readDictionaryFromFile("englishDictionary.txt");
        }
        catch(Exception e) {
            System.out.println("Error reading the dictionary: "+e);
        }
        
        
        // Ask user to type in letters
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in) );
        char letters[]; 
        do {
            System.out.println("Enter your letters (no spaces or commas):");
            
            letters = keyboard.readLine().toCharArray();

	    // now, enumerate the words that can be formed
            String longest = longestWord(letters, "");
	    System.out.println("The longest word is "+longest);
        } while (letters.length!=0);

        keyboard.close();
        
    }
}