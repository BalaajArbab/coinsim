package coinSim.authentication;

/**
 * The {@code Encryption} parent class. Is used to hash the string password.
 * 
 * @author Rishabh J.
 */
public class Encryption {
	/**
	 * Password is hashed and returned
	 * 
	 * @param password which is plain text (un-hashed)
	 * @return hashed password
	 */

	public static int hashCode(String password) {
		return (int) password.hashCode();
	}
}
