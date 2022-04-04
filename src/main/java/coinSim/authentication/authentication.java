package coinSim.authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The {@code authentication} parent class. Is used to authenticate the user
 * with credentials entered during login.
 * 
 * @author Rishabh J.
 */
public class authentication {
	// validCredentials receives the entered creds from login method
	public static boolean validCredentials(String creds) {
		// Opens the file containing credentials
		File myObj = new File("users.txt");
		Scanner sc;
		try {
			sc = new Scanner(myObj);
			// returns true if the line being read matches the creds
			while (sc.hasNextLine()) {
				if (sc.nextLine().equals(creds)) {
					return true;
				}
			}
			// closes the scanner
			sc.close();
		}
		// file not found
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		// match not found
		return false;
	}

}