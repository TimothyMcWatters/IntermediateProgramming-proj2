import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
This program:
Extends project1 that uses the Vigenere Cipher to encrypt passwords. 
Has a superclass Account, and sub classes User and Bot that works with passwords; User for people, and Bot for robots
Also has CompanyAccounts for an array of Account's, and of course the AccountTester class for testing the other Classes
The project will also read information in from a text file, and will rely on both polymorphism and inheritance.

@author Timothy McWatters
@version 1.0

COP3022    Project 2
File Name: Bot.java
*/

public class Bot extends Account {
	private String botFileName = "";
	private String category = "";
	private GregorianCalendar dateUpdated = null;
	private String createdBy = "";
	
	/**
	 * Default Constructor for the Bot Class
	 */
	public Bot() {
		super();
		setBotFileName("");
		setCategory(""); 
		setDateUpdated(null);
		setCreatedBy("");
	} // end of default constructor
	
	/**
	 * Constructor using 6 parameters for the Bot Class plus calls on the encrypt() and setAccountId() methods in the super class
	 * @param clearPassword = the users unencrypted password
	 * @param key = the users key to be used in the Vigenere Cipher
	 * @param botFileName = the file name of the bot
	 * @param category = the bots category (IDS, SysAdm, or HelpDesk)
	 * @param dateUpdated = the date the bot was last updated in string format... need to tokenize, change to int and create an GregoriaCalendar object
	 * @param createdBy = the creators name or handle
	 */
	public Bot(String clearPassword, String key, String botFileName, String category, String dateUpdatedString, String createdBy) {
		super(clearPassword, key);
		setBotFileName(botFileName);
		setCategory(category); 
		setCreatedBy(createdBy);
		
		StringTokenizer date = new StringTokenizer(dateUpdatedString, "/");
		int month = Integer.parseInt(date.nextToken());
		int dayOfMonth = Integer.parseInt(date.nextToken());
		int year = Integer.parseInt(date.nextToken());
		GregorianCalendar dateUpdatedObject = new GregorianCalendar(year, month, dayOfMonth);
		setDateUpdated(dateUpdatedObject);

	} // end of constructor w/6  parameters

	/**
	 * returns the bots file name
	 * @return the botFileName = bots file name
	 */
	public String getBotFileName() {
		return botFileName;
	} // end of getBotFileName method

	/**
	 * sets the bots file name 
	 * @param botFileName = file name of the bot to set
	 */
	public void setBotFileName(String botFileName) {
		this.botFileName = botFileName;
	} // end of setBotFileName method

	/**
	 * returns the bots category
	 * @return the category = the bots category (IDS, SysAdm, HelpDesk)
	 */
	public String getCategory() {
		return category;
	} // end of getCategory method

	/**
	 * sets the bots category (IDS, SysAdm, HelpDesk)
	 * @param category = the bots category (IDS, SysAdm, HelpDesk)
	 */
	public void setCategory(String category) {
		this.category = category;
	} // end of setCategory method

	/**
	 * returns the date the bot was last updated
	 * @return the dateUpdated = the date the bot was last updated
	 */
	public GregorianCalendar getDateUpdated() {
		return dateUpdated;
	} // end of getDateUpdated method

	/**
	 * sets the date the bot was last updated
	 * @param dateUpdated = the date the bot was last updated
	 */
	public void setDateUpdated(GregorianCalendar dateUpdated) {
		this.dateUpdated = dateUpdated;
	} // end of setDateUpdated method
	
	/**
	 * returns who the bot was created by
	 * @return createdBy = the creators name or handle
	 */
	public String getCreatedBy() {
		return createdBy;
	} // end of getCreatedBy method

	/**
	 * sets the department code that the user belongs to
	 * @param deptCode = the department code to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	} // end of setCreatedBy method

	
	public String toString() {
		int dayOfMonth = 0;
		int monthOfYear = 0;
		int yearOfCalendar = 0;
		if (dateUpdated != null) {
			dayOfMonth = getDateUpdated().get(Calendar.DAY_OF_MONTH);
			monthOfYear = getDateUpdated().get(Calendar.MONTH);
			yearOfCalendar = getDateUpdated().get(Calendar.YEAR);
		} else {
			dayOfMonth = 0;
			monthOfYear = 0;
			yearOfCalendar = 0;
			
		}
		return (super.toString() + String.format("%-20s %-20s         %-15s %d/%d/%d", getBotFileName(), getCreatedBy(), getCategory(), monthOfYear, dayOfMonth, yearOfCalendar));
	} // end of toString method
	
} // end of Bot class

