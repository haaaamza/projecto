import java.io.*;
import java.util.*;

public class Expression {
	static String delimiters="+-*%()";
	
	
	// Returns the value of the arithmetic Expression described by expr
	// Throws an exception if the Expression is malformed
	static Integer evaluate(String expr) throws Exception {
	    /* The code below gives you an example of utilization of the
	     * StringTokenizer class to break the Expression string into
	     * its components */
	   // StringTokenizer st = new StringTokenizer( expr , delimiters , true );    
        
	    /* This is just an example of how to use the StringTokenizer */
	   /* while ( st.hasMoreTokens() ) {
		String element = st.nextToken();
		System.out.println("Element ="+element);
	    }    
        */
	    /* YOU WRITE YOUR CODE HERE */
	    
	    StringTokenizer sf = new StringTokenizer( expr, delimiters, true);
	    StringTokenizer work = new StringTokenizer( expr, delimiters, true); //work and work2 are Tokenizers for the exceptions
	    StringTokenizer work2 = new StringTokenizer( expr, delimiters, true);
	 
	    //Here I am currently listing out all of the exceptions...
	    
	   Stack <String> check= new Stack <String>();
	    while (work.hasMoreTokens() ){
	    	String token=new String();
	    	token=work.nextToken();
	    	if (token.equals("(")){
	    		check.push("(");
	    	}
	    	if (token.equals(")")&&!check.isEmpty()){
	    		check.pop();
	    	}else if (token.equals(")")&&check.isEmpty()){
	    		throw new Exception ();
	    	}
	    } 
	    if (!check.isEmpty()){
	    	throw new Exception ();
	    } //This is just to check if the brackets are in even numbers
	    Stack <String> check2= new Stack <String>();
	    while (work2.hasMoreTokens() ){
	    	check2.push(work2.nextToken());
	    }
	    if (check2.peek().equals("+")||check2.peek().equals("-")||check2.peek().equals("*")
	    		||check2.peek().equals("%")){
	    	throw new Exception(); //This is to make sure that the last term is not an operator
	    }
	  for (int k=0; k<check2.size(); k++){
	    	if ((check2.elementAt(k).equals("+")||check2.elementAt(k).equals("-")||
	    			check2.elementAt(k).equals("*")||check2.elementAt(k).equals("%"))&&
	    			(check2.elementAt(k+1).equals("-"))){
	    		throw new Exception(); //This is to make sure that there are no negative numbers
	    	}
	    	if ((check2.elementAt(k).equals("+")||check2.elementAt(k).equals("-")||
	    			check2.elementAt(k).equals("*")||check2.elementAt(k).equals("%"))&&
	    			check2.elementAt(k+1).equals(")")){
	    		throw new Exception(); //This throws an exception if an operator is next to a right bracket
	    	}
	    }
	    
	  //actual code
	  //In This program rather than using two stacks, I used a string and a stack.
	  //I first converted the string into a postfix expression, because it was easier for me to comprehend
        Stack <String> P= new Stack <String> ();//creating the stack.
        String L= new String();
        
        while (sf.hasMoreTokens() ){
        	  String next=new String();
              next=sf.nextToken();
        	for (int i=0; i<10; i++){
        		if (Integer.parseInt(next)==i){
        			String m= ""+i +" ";
        			L+=m;
        		}//adding operand to String
        	}
        	
        if(next.equals("(")) {
        		P.push("(");//if left bracket is found, then add it to the stack
        	}
        if (next.equals(")")){
        		while (!P.isEmpty() && !P.peek().equals("(")){
        			L+=P.pop()+" ";	} //if right bracket is found but not left, then Pop the stack and add it to the String
        		P.pop();//when left is found, discard it from the stack
        	}
      
        if (next.equals("+")||next.equals("-")||next.equals("*")
        			||next.equals("%")){
        		if (P.isEmpty()||P.peek().equals("(")){
        			P.push(next); //if an oper is found and the stack is empty or the stack's top is a
        			//left bracket, then push the operator onto the stack.
        		}else{
        			while (!P.isEmpty()&&!P.peek().equals("(")){//if the stack is not empty and !
        				//contain a left parentheses then
        				P.pop(); //pop the stack
        				L+=P.peek()+" "; //and add the peek to the string 
        			}
        			P.push(next);
        		}
        	}
        	
        }
        while ( !P.isEmpty() ) {
        	L+=P.pop()+" ";
        } //if the stack is not empty, pop the stack and add the popped value to P.
        //here we are creating a new stack 
        Stack<Integer> X=new Stack <Integer>(); 
        StringTokenizer sL = new StringTokenizer(L);//this tokenizes the postfix expression
        
        while ( sL.hasMoreTokens() ){
        	
        	String nexter= new String();
        	nexter=sL.nextToken();
        	
        	for (int i=0; i<10; i++){
        		if (Integer.parseInt(nexter)==i){
        			X.push(i); //if an int is found, then push into stack
        			}	
        		}
        	
        if (nexter.equals("+")||nexter.equals("-")||nexter.equals("*")
        			||nexter.equals("%")){ //if operator is found then 
        	
        		int A=X.pop();
        		int B=X.pop(); //pop the two previous values
        		int C=0; 
        		if (nexter.equals("+")) C=A+B; //and compute the two values with C
        		if (nexter.equals("-")) C=A-B;
        		if (nexter.equals("*")) C=A*B;
        		if (nexter.equals("%")) C=A%B;
        		X.push(C); //push the value C back into the stack
        	}	
        }
       
        return (X.pop()); //the final value should be the only remaining value in the stack.
        
	    
	}	
		
	public static void main(String args[]) throws Exception {
		String line;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                                      	                        
		do {
			line=stdin.readLine();
			if (line.length()>0) {
				try {
					Integer x=evaluate(line);
					System.out.println(" = " + x);
				}
				catch (Exception e) {
					System.out.println("Malformed Expression: "+e);
				}
			}
		} while (line.length()>0);
	}
}