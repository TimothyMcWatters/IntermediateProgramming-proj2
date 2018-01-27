import java.util.GregorianCalendar;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
This program:
Extends project1 that uses the Vigenere Cipher to encrypt passwords. 
Has a superclass Account, and sub classes User and Bot that works with passwords; User for people, and Bot for robots
Also has CompanyAccounts for an array of Account's, and of course the AccountTester class for testing the other Classes
The project will also read information in from a text file, and will rely on both polymorphism and inheritance.

@author Timothy McWatters
@version 1.0

COP3022    Project 2
File Name: AccountTester.java
*/

public class AccountTester {
	public static void main(String[] args) {
		Scanner fileIn = null; //default empty
		try {
			// Attempt to open the file
			fileIn = new Scanner(new FileInputStream("data.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
			System.exit(0);
		}
		CompanyAccounts companyAcct1 = new CompanyAccounts("Cool Guy Construction", "2779 Grand Bay Court, Navarre Florida", CompanyAccounts.PARTIALLY_FILLED_ARRAY_SIZE);
		
		while (fileIn.hasNextLine()) {
			String theString = fileIn.nextLine();
			String[] theTokens = theString.split(",");
			if (theTokens[0].equalsIgnoreCase("u")) {
				String username = theTokens[1]; 
				String fullName = theTokens[2];
				int deptCode = Integer.parseInt(theTokens[3]);
				String clearPassword = theTokens[4];
				String key = theTokens[5];
				companyAcct1.addAccount(new User(clearPassword, key, username, fullName, deptCode));
			} else if (theTokens[0].equalsIgnoreCase("b")) {
				String botFileName = theTokens[1]; 
				String category = theTokens[2];
				String dateUpdated = theTokens[3];
				String createdBy = theTokens[4];
				String clearPassword = theTokens[5];
				String key = theTokens[6];
				companyAcct1.addAccount(new Bot(clearPassword, key, botFileName, category, dateUpdated, createdBy));
			}
		}
		System.out.println(companyAcct1.toString());
		
		//creates an instance of the CompanyAccounts Class (tests default CompanyAccounts constructor)
	     CompanyAccounts companyAcct2 = new CompanyAccounts();
	     
	     //completes Account info for acct2 (tests setCompanyName(), and setCompanyAddress())
	     companyAcct2.setCompanyName("Horrible Guy Company");
	     companyAcct2.setCompanyAddress("432 Failure Lane, Pensacola Florida");
	     
	     //creates an 6 instances of the User Class (tests both default User and User constructor w/ parameters
	     //you should get 2 password errors here because your passwords will be empty for now (default constructors)
	     User user1 = new User();
	     Bot bot1 = new Bot();
	     
	     //populates both above instances
	     user1.setUserName("PKFD");
	     user1.setFullName("Pink Floyd");
	     user1.setDeptCode(1009);
	     user1.setKey("Brick");
	     user1.setClearPassword("Another$3");
	     
	     bot1.setBotFileName("king.txt");
	     bot1.setCategory("IDS");
	     GregorianCalendar ITReleaseDate = new GregorianCalendar(2017, 9, 8);
	     bot1.setDateUpdated(ITReleaseDate);
	     bot1.setCreatedBy("Stephen King");
	     bot1.setKey("sc"); //key is only 2 characters, I will update in a later test
	     bot1.setClearPassword("#KingsIT17");
	     
	     //tests the addAccount() method
	     companyAcct2.addAccount(user1);
	     companyAcct2.addAccount(bot1);
	     
	     //makes sure that the above instances were actually created and populated through default constructors
	     System.out.println(companyAcct2.toString());
	     
	     //test the setKey() method because bot1 only as a 2 character key, needs to be 5 characters
	     bot1.setKey("Scary");
	     
	     //tests that the key was changed in the previous step using setKey() method
	     System.out.println(companyAcct2.toString());
		
	     //tests the getNextIDNum() method from the Account class using an instance of the Bot class
	     System.out.println("The next account ID would be " + bot1.getNextIDNum());
	     
	     //tests the deleteAccount() method, in turn also testing the findAccount() and getAccount() methods 
	     //deleting account 1002, 1005, and 1006 because they had an incorrect passwords
	     companyAcct1.deleteAccount(1002); //password has no special characters
	     companyAcct1.deleteAccount(1005); //password has no numbers
	     companyAcct1.deleteAccount(1006); //password is not at least 8 characters long
	     companyAcct1.deleteAccount(1007); //password has an extra special character (!)
	     
	     //tests getAccount() to ensure that account 1002, 1005, and 1006 were actually deleted
	     companyAcct1.getAccount(1002);
	     companyAcct1.getAccount(1005);
	     companyAcct1.getAccount(1006);
	     companyAcct1.getAccount(1007);
	     
	     //testing the final print out of all accounts
	     System.out.println(companyAcct1.toString());
	     System.out.println(companyAcct2.toString());
	}	
} // end of AccountTester class
		