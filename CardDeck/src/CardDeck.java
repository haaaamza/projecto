
import java.util.*;

class CardDeck {
    LinkedList<Integer> deck;

    // constructor, creates a deck with n cards, placed in increasing order
    CardDeck(int n) {
	deck = new LinkedList<Integer> ();
	for (int i=1;i<=n;i++) deck.addLast(new Integer(i));
    }

    // executes the card trick
    public void runTrick() {

	while (!deck.isEmpty()) {
	    // remove the first card and remove it
	    Integer topCard = deck.removeFirst();
	    System.out.println("Showing card "+topCard);

	    // if there's nothing left, we are done
	    if (deck.isEmpty()) break;
	    
	    // otherwise, remove the top card and place it at the back.
	    Integer secondCard = deck.removeFirst();
	    deck.addLast(secondCard);

	    System.out.println("Remaining deck: "+deck);

	}
    }



    public void setupDeck(int n) {
	/* WRITE YOUR CODE HERE */
    LinkedList<Integer> duck= new LinkedList<Integer>();
    int j=n;
    while (j>=1){ //here I am just writing out the program in, say a reverse form
    	duck.addFirst(duck.getLast()); //this duplicates the last card and adds it to the front of the deck
    	duck.removeLast(); //This removes the last card(that was previously duplicated
    	duck.addFirst(j); //add's the value of Integer j to the deck.
    	j--;
    }
    deck=duck;//changing original value of deck...
    }



    public static void main(String args[]) {
	// this is just creating a deck with cards in increasing order, and running the trick. 
	CardDeck d = new CardDeck(10);
	d.runTrick();

	// this is calling the method you are supposed to write, and executing the trick.
	CardDeck e = new CardDeck(0);
	e.setupDeck(10);
	e.runTrick();
    }
}

  