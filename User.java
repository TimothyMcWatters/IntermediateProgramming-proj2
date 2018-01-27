/**
This program:
Extends project1 that uses the Vigenere Cipher to encrypt passwords. 
Has a superclass Account, and sub classes User and Bot that works with passwords; User for people, and Bot for robots
Also has CompanyAccounts for an array of Account's, and of course the AccountTester class for testing the other Classes
The project will also read information in from a text file, and will rely on both polymorphism and inheritance.

@author Timothy McWatters
@version 1.0

COP3022    Project 2
File Name: User.java
*/

public class User extends Account {
	private String username = "";
	private String fullName = "";
	private int deptCode = 0;
	
	/**
	 * Default Constructor for the User Class
	 */
	public User() {
		super();
		setUserName("");
		setFullName(""); 
		setDeptCode(0);
	} // end of default constructor
	
	/**
	 * Constructor using 5 parameters for the User Class plus calls on the encrypt() and setAccountId() methods in the super class
	 * @param clearPassword = the users unencrypted password
	 * @param key = the users key to be used in the Vigenere Cipher
	 * @param username = the users username
	 * @param fullName = the users full name
	 * @param deptCode = the code for the department the user belongs to
	 */
	public User(String clearPassword, String key, String username, String fullName, int deptCode) {
		super(clearPassword, key);
		setUserName(username);
		setFullName(fullName); 
		setDeptCode(deptCode);
	} // end of constructor w/5  parameters

	/**
	 * returns the users username
	 * @return the username = users username
	 */
	public String getUserName() {
		return username;
	} // end of getUserName method

	/**
	 * sets the users username 
	 * @param username the username to set
	 */
	public void setUserName(String username) {
		this.username = username;
	} // end of setUserName method

	/**
	 * returns the users actual full name
	 * @return the fullName = the users actual full name
	 */
	public String getFullName() {
		return fullName;
	} // end of getFullName method

	/**
	 * sets the users actual full name
	 * @param fullName the users actual full name to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	} // end of setFullName method

	/**
	 * returns the code of the department user belongs to
	 * @return the deptCode
	 */
	public int getDeptCode() {
		return deptCode;
	} // end of getDeptCode method

	/**
	 * sets the department code that the user belongs to
	 * @param deptCode = the department code to set
	 */
	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	} // end of setDeptCode method

	
	public String toString() {
		return (super.toString() + String.format("%-20s %-20s %-5d", getUserName(), getFullName(), getDeptCode()));
	} // end of toString method
	
} // end of User class

