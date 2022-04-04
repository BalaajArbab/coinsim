package coinSim.authentication;

public class Encryption {
	public static int hashCode(String password) {
		System.out.println(password.hashCode());
		return (int) password.hashCode();
	}
}
