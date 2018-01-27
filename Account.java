/**
This program:
Extends project1 that uses the Vigenere Cipher to encrypt passwords. 
Has a superclass Account, and sub classes User and Bot that works with passwords; User for people, and Bot for robots
Also has CompanyAccounts for an array of Account's, and of course the AccountTester class for testing the other Classes
The project will also read information in from a text file, and will rely on both polymorphism and inheritance.

@author Timothy McWatters
@version 1.0

COP3022    Project 2
File Name: Account.java
*/

public class Account {
	private String clearPassword = "";
	private String encryptedPassword = "";
	private String key = "";
	private int accountId = 0;
	private static int nextIDNum = 1000;
	
	/**
	 * Default Constructor for the Account Class
	 */
	public Account() {
		setKey("");
		setClearPassword("");
		setAccountId();
	} // end of default constructor
	
	/**
	 * Constructor using 2 String parameters for the Account Class plus calls on the encrypt() and setAccountId() methods
	 * @param clearPassword = the users unencrypted password
	 * @param key = the users key to be used in the Vigenere Cipher 
	 */
	public Account(String clearPassword, String key) {
		setKey(key);
		setClearPassword(clearPassword);
		setAccountId();
		encrypt();
	} // end of constructor w/ 2 String parameters

	/**
	 * returns the users unencrypted password
	 * @return the clearPassword = the users unencrypted password
	 */
	public String getClearPassword() {
		return clearPassword;
	} // end of getClearPassword method

	/**
	 * sets the unencrypted password and calls encrypt() method setting the encrypted password also.
	 * @param clearPassword the clearPassword to set
	 */
	public void setClearPassword(String clearPassword) {
		int numNumbers = 0;
		int numSpecialChars = 0;
		int numCharactersNotAllowed = 0;
		
		for (int i = 0; i < clearPassword.length(); i++) {
			if ((clearPassword.charAt(i) >= 48) && (clearPassword.charAt(i) <= 57)) {
				numNumbers++;
			}
			else if ((clearPassword.charAt(i) >= 35) && (clearPassword.charAt(i) <= 38)) {
				numSpecialChars++;
			}
			else if (((clearPassword.charAt(i) >= 65) && (clearPassword.charAt(i) <= 90)) ||
					((clearPassword.charAt(i) >= 97) && (clearPassword.charAt(i) <= 122))) {
				continue;
			} else {
				numCharactersNotAllowed++;
			}
		}
		if ((clearPassword.length() >= 8) && (numNumbers > 0) && (numSpecialChars > 0) && (numCharactersNotAllowed < 1)) {
			this.clearPassword = clearPassword;
			encrypt();
		} else {
			System.out.println("ERROR: Your password is but must: be >= 8 characters, contain at least 1 number and 1 special character"
					+ " (#, $, %, &) but no other special characters allowed");
			this.clearPassword = "";
			this.encryptedPassword = "";
		}
	} // end of setClearPassword method

	/**
	 * returns the users encrypted password
	 * @return the encryptedPassword
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	} // end of getEncryptedPassword method

	/**
	 * returns the key to be used in the Vigenere Cipher
	 * @return the key = key to be used in Vigenere Cipher
	 */
	public String getKey() {
		return key;
	} // end of getKey method

	/**
	 * sets the key to be used in the Vigenere Cipher and calls encrypt() method setting the encrypted password also.
	 * @param key the key to set = key to be used in Vigenere Cipher
	 */
	public void setKey(String key) {
		this.key = key;
		encrypt();
	} // end of setKey method
	
	/**
	 * sets the accountId using the next available id based off the nextIDNum static variable
	 */
	public void setAccountId() {
		this.accountId = nextIDNum++;
	} // end of setAccountId method
	
	/**
	 * returns the account Id associated with this account 
	 * @return the account Id = unique Id associated with this account 
	 */
	public int getAccountId() {
		return accountId;
	} // end of getAccountId method
	
	/**
	 * returns the next account Id in que 
	 * @return the getNextIDNum = next account Id available
	 */
	public int getNextIDNum() {
		return nextIDNum;
	} // end of getNextIDNum method
	
	
	/**
	 * uses the Vigenere Cipher, the unencrypted password (clearPassword), and the key 
	 * to encrypt the users password populating/storing the encryptedPassword instance variable
	 */
	private void encrypt() {
		int newKeyIndexNum = 0;
		String newKey = this.key; // newKey will be used to ensure the key and password are the same length
		String intermediatePassword = "";
		//  Instead of hard-coding lengths, this while loop ensures that the key starts over again if the 
		// password is longer than the key regardless of the actual numbers. 
		while (newKey.length() < clearPassword.length()) {
			newKey += newKey.charAt(newKeyIndexNum); 
			newKeyIndexNum++;
		}
		
		// Determines how many char's to shift the password based off the newKey found above
		// then iterates through the clearPassword and shifts each character while appending the
		// shifted character to the encryptedPassword one char at a time while keeping them between 33-122 inclusive
		for (int j = 0; j < this.clearPassword.length(); j++) {
	         int shiftedASCIICharactersNumber = (clearPassword.charAt(j) + (newKey.charAt(j) - 33));
	         if (shiftedASCIICharactersNumber < 123) {
	        	 intermediatePassword += (char)(shiftedASCIICharactersNumber);
	         
	         } 
	         else {
	        	 intermediatePassword += (char)((shiftedASCIICharactersNumber % 122) + 32);
	         }
	      }
		this.encryptedPassword = intermediatePassword;
	} // end of encrypt method

	/* returns a nicely formatted String representing the Account to include 
	 * accountId, encrypted password, clear password and key
	 * @return a formatted String
	 */
	public String toString() {
		return String.format("%-10d %-20s %-10s %-20s", getAccountId(), getClearPassword(), getKey(), getEncryptedPassword());
	} // end of toString method
	
} // end of Account class
