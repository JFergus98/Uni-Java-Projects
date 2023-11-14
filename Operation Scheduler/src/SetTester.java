import java.util.Set;
import java.util.Arrays; 
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class SetTester {
	public static void main(String[] args) {
		SetTester testerObj = new SetTester();
		testerObj.menu();
	}
	   void menu() 
	   {
			SetTester testerObj = new SetTester();
			Sets testerObj1 = new Sets();
		    System.out.println("Enter 1 choose day of appointment: ");
		    System.out.println("Enter 2 to exit: ");
		    Scanner user = new Scanner(System.in);
	        int choice=user.nextInt();
	        switch (choice) {
	        case 1: 
	        		testerObj.play();
	        		break;
	        case 2: 
        			System.exit(0);
	                break;
	        default: 
	        	System.out.println("Invalid choice");
	    }
	   }
	   void play() {
			Sets testerObj1 = new Sets();
			testerObj1.newMonth();
			testerObj1.genMonth();
			testerObj1.newUserM();
			testerObj1.genUserM();
			testerObj1.newUserD();
			testerObj1.genUserD();
			testerObj1.display();
			System.out.println(testerObj1.getDayInt());
			System.out.println(testerObj1.getMonthInt());
			
	   }
	   
}

