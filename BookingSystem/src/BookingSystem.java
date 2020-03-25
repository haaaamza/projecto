
import java.util.Scanner;
import java.util.Random;

public class BookingSystem {
    

    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);
    
    //returns a random String from the above array. 
    private static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    private static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
    //End of provided code. 
    
    public static void main(String[] args){
        //Student Name: Shajeed Islam
        //Student Number: 260662982
        //Your code goes here.
    	
    	System.out.println("Type in Hotel name and press enter");
		Scanner name = new Scanner(System.in);
    	Scanner Option;
    	String hotelName = name.nextLine();
    	int amountRoom = getRandomNumberOfRooms();
    	int i; 
    	
    	Room[] room = new Room[amountRoom];
    	for (i = 0; i < amountRoom; i++) {
    		room[i] = new Room(getRandomType());
    	}
    	
    	Hotel(room, hotelName, amountRoom) theHotel;
    	function();
    	
    	
    	
    	
    	
    	private static void function() {
    	
    		System.out.println("Welcome to the " + hotelName + " Booking service! What do you want to do?");
    		System.out.println("1. Make a reservation");
    		System.out.println("2. Cancel a reservation");
    		System.out.println("3. See an Invoice");
    		System.out.println("4. See the hotel info");
    		System.out.println("5. Exit the Booking Service");
    		
    		Option = new Scanner(System.in);
    		
    		if (Option.nextInt == 1) {
        		System.out.println("Type in name and press enter")
        		Scanner name = new Scanner(System.in);
        		System.out.println("Type in the desired room type (double, queen, or king)");
        		Scanner type = new Scanner(System.in);
        		theHotel.createReservation(name.nextLine, type.nextLine);
        		function();
        	}
        	if (Option.nextInt == 2) {
        		System.out.println("Type in name and press enter");
        		Scanner name = new Scanner(System.in);
        		System.out.println("Type in the room type Reserved (double, queen, or king)");
        		Scanner type = new Scanner(System.in);
        		theHotel.cancelReservation(name.nextLine, type.nextLine);
        		function();
        	}
        	if (Option.nextInt == 3) {
        		System.out.println("Type in name and press enter");
        		Scanner name = new Scanner(System.in);
        		theHotel.printInvoice(name.nextLine);
        		function();
        	}
        	if (Option.nextInt == 4) {
        		System.out.println("Hotel name: " + hotelName);
        		System.out.println("Queen available: " + theHotel.counter("queen"));
        		System.out.println("Double available: " + theHotel.counter("double"));
        		System.out.println("King available: " + theHotel.counte("king"))
        		function();
        	}
        	if (Option.nextInt == 5) {
        		System.out.println("Thanks! Goodbye!");
        	}
        	if (Option.nextInt != 1 || Option.nextInt !=2 || Option.nextInt !=3 ||Option.nextInt !=4 || Option.nextInt !=5) {
        		System.out.println("Invalid option. try again");
        		function();
        	}
    	}	
    }
}

public class Room{
	

	private String type;
	private double price;
	private boolean availability;
	
	public Room(String type) {
		this.type = type;
		if (type == "double") {
			this.price = 90;
		}else {
			if (type == "queen"){
				this.price = 110;
			} else {
				if (type == "king") {
					this.price = 150;
				} else {
					return;
				}
			}
		}
		this.availability = true;
	}
	//constructor
	/* If input is not double, queen, king, throw exception
	 */
	public double getPrice() {
		return price;
	}
	public String getType() {
		return type;
	}
	public boolean getAvailability() {
		return availablility;
	}
	public void changeAvailability() {
		availability = !availability;
	}
	public findAvailableRoom(String a){
		for (int i = 0; i < amountRoom; i++) {
			if (room[i].getAvailability == true && room[i].getType == a /*careful of reference type of string*/) {
				return room[i]
			}
		}
	
	}	
}



public class Reservation{
	private String name;
	private Room roomReserved;

	public Reservation(Room() roomReserved, String name) {
		this.roomReserved = roomReserved;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getRoom{
		return roomReserved;
	}	
}




public class Hotel{
	
	private int amountRoom;
	private String hotelName;
	private Room[] room;
	// now populate rooms
	private Reservation[] reservationsMade = new Reservation[amountRoom];
	
	
	
	public Hotel(Room[] room, String hotelName, int amountRoom) {
		this.room = room;
		this.hotelName = hotelName;
		this.amountRoom = amountRoom
	}
	
	
	
	
	private static void addReservation(Reservation() inputReservation) {
		/*first find an empty slot in the reservationsMade object array
		 *Then put this input reservation into that slot
		 */
		for(int i= 0; i< amountRoom; i++) {
			if (reservationsMade[i]== null) {
				reservationsMade[i] = inputReservation;
				return;
			}
		}
	}
	
	private static void removeReservation(String name, String type) {
		for(int i = 0; i < amountRoom; i++) {
			if (reservationsMade[i].getName = name /*careful of reference type*/ && reservationsMade.getRoom.getType = type) {
				reservationsMade[i]=null;
				room[i].changeAvailability;
				return;
				
			}
		}
	}
	public static void createReservation(String clientName, String type) {
		if(room[].findAvailableRoom(type) == null) {
			System.out.println("There are no more rooms of this type");
		}else {
			Reservation(room[].findAvailableRoom(type), clientName) reserveTemp;
			addReservation(reserveTemp);
			room[].findAvailableRoom(type).changeAvailability;
			System.out.println("Reservation has been successful");	
		}
	}
	
	
	public static void cancelReservation(String clientName, String type) {
		for(i=0; i< amountRoom; i++) {
			
			if(reservationsMade[i].getName = clientName /*careful of reference type*/ && reservationsMade.getRoom.getType = type) {
				removeReservation(clientName, type);
				return;
			}
		}
		System.out.println("There is no such reservation in the system")
		
		// a try catch method should be used
	}

	public static void printInvoice(String clientName) {
		double bill=0;
		for (i = 0; i < amountRoom; i++) {
			if (reservationsMade[i].getName == clientName) {
				bill = bill + reservationsMade[i].getRoom.getPrice
			}
		}
		System.out.println("Invoice total: " + bill);
	}
	
	private static int counter(String type) {
		int count;
		for (i = 0; i < amountRoom; i++) {
			if (room[i].getAvailability == true && room[i].getType == type) {
				count++;
			}
		}
		return count;
	}
	
}