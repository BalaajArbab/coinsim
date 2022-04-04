package coinSim.authentication;

/**
 * The {@code Encryption} parent class. Is used to hash the string password.
 * 
 * @author Rishabh J.
 */
public class Encryption {
	public static int hashCode(String password) {
		return (int) password.hashCode();
	}
}
