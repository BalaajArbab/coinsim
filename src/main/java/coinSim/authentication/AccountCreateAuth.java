package coinSim.authentication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The {@code AccountCreateAuth} parent class. Checks if the user already exists
 * by checking the user name entered in the text field against the users.txt
 * file
 * 
 * @author Rishabh J.
 */
public class AccountCreateAuth {
	/**
	 * @param user is the username entered in the account creation text field
	 * @return boolean based on username match
	 */
	public static boolean validCredentials(String user) {
		// String array credentials
		String[] credentials;
		File myObj = new File("users.txt");
		Scanner sc;
		try {
			sc = new Scanner(myObj);
			// returns true if there is another line to read
			while (sc.hasNextLine()) {
				// credentials[0] is username, credentials[1] is password
				credentials = sc.nextLine().split(",");
				if (credentials[0].equals(user)) {
					return true;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist.");
			return false;
		}
		// closes the scanner
		sc.close();
		return false;
	}
}
