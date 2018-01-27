/**
This program:
Extends project1 that uses the Vigenere Cipher to encrypt passwords. 
Has a superclass Account, and sub classes User and Bot that works with passwords; User for people, and Bot for robots
Also has CompanyAccounts for an array of Account's, and of course the AccountTester class for testing the other Classes
The project will also read information in from a text file, and will rely on both polymorphism and inheritance.

@author Timothy McWatters
@version 1.0

COP3022    Project 2
File Name: CompanyAccounts.java
*/

public class CompanyAccounts {
	//constants
	public static final int NOTFOUND = -1;
	public static final int PARTIALLY_FILLED_ARRAY_SIZE = 50;
	
	//instance variables
	private String companyName = "";
	private String companyAddress = "";
	private int numOfElements = 0;
	private Account[] companyAccounts = null;
	
	/**
	 * Default constructor for the CompanyAccounts class
	 */
	public CompanyAccounts() {
		setCompanyName("");
		setCompanyAddress("");
		this.companyAccounts = new Account[PARTIALLY_FILLED_ARRAY_SIZE];
	} // end of default constructor
	
	/**
	 * Constructor using 3 parameters for the CompanyAccounts class
	 * @param companyName = the Accounts company name
	 * @param companyAddress = the Accounts company address
	 * @param arraySize = the size of the partially filled array
	 */
	public CompanyAccounts(String companyName, String companyAddress, int arraySize) {
		setCompanyName(companyName);
		setCompanyAddress(companyAddress);
		this.companyAccounts = new Account[arraySize];
	} //end of CompanyAccounts constructor w/ 3 parameters

	/**
	 * returns the company name
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	} // end of getCompanyName method

	/**
	 * sets the company name
	 * @param companyName = the company's name to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	} // end of setCompanyName method

	/**
	 * returns the company address
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	} // end of getCompanyAddress method

	/**
	 * sets the company address
	 * @param companyAddress = the company's address to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	} // end of setCompanyAddress method 

	/**
	 * adds an account to the CompanyAccounts companyAccounts array and increments the numOfElements instance variable by 1
	 * @param account = an Account to add
	 */
	public void addAccount(Account acccount) {
		companyAccounts[numOfElements++] = acccount;
	} // end of addAccount method
	
	/**
	 * uses the findAccount() method to search for an return the Account object associated with the parameter, or returns null if that accountName is not found
	 * @param accountName = the account name of Account object to get
	 * @return Account = the Account object or null if the account is not found
	 */
	public Account getAccount(int accountId) {
		int findAccountResult = findAccount(accountId);
		if (findAccountResult != -1) {
			return companyAccounts[findAccountResult];
		} else {
			System.out.printf("Account ID#%s does not exist.\n", accountId);
			return null;
		}
	} // end of getAccount method

	/**
	 * uses findAccount() method to find and delete an account
	 * this method also re-organizes the users array to get rid of null or empty objects in the middle of the array (typically this would be done with another method and just 
	 * called in this method, but the program requirements deemed it should be done in this method)
	 * @param accountId = the ID of account to delete
	 * @return boolean = true if account was found and deleted, or false if not
	 */
	public boolean deleteAccount(int accountId) {
		int findAccountResult = findAccount(accountId);
		int j = 0;
		if (findAccountResult >= 0) {
			companyAccounts[findAccountResult] = null;
			for (int i = 0; i < companyAccounts.length; i++) {
				if ((companyAccounts[i] != null) && (companyAccounts[i].getAccountId() != 0)) {
					companyAccounts[j] = companyAccounts[i];
					j++;
				}
			}
			numOfElements = j;
			for (int k = j; k < companyAccounts.length; k++) {
				companyAccounts[k] = null;
			}
			return true;
		} else {
			return false;
		}
	} // end of deleteAccount method
	
	/**
	 * attempts to find an account ID in the CompanyAccounts companyAccounts array, returns NOTFOUND constant if not found
	 * @param accountId = the ID of the account to find
	 * @return int = the array index number that was found, or NOTFOUND constant if the account ID was not found
	 */
	private int findAccount(int accountId) {
		for (int indexNumber = 0; indexNumber < companyAccounts.length; indexNumber++) {
			if (companyAccounts[indexNumber] == null) {
				continue;
			}
			else if (companyAccounts[indexNumber].getAccountId() == accountId) {
				return indexNumber;
			}
			else { 
				continue;
			}
		}
		return NOTFOUND;
	} // end of findAccount method

	/* returns a nicely formatted String representing the user to include accountID, encrypted password, clear password and key
	 * @return a formatted String
	 */
	public String toString() {
		String completeString = ("\n" + getCompanyName() + "\t" + getCompanyAddress() + "\n" +
								"Acct ID    ClearPass            Key        EncryPass           User/Bot name        Full Name            Dept    Bot Purpose     Bot last updated\n");
			for (int l = 0; l < this.numOfElements; l++) {
				completeString += companyAccounts[l].toString() + "\n";
			} 
			return completeString;
	} // end of toString method

}  // end of CompanyAccounts class
