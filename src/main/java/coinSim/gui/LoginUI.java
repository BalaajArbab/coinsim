package coinSim.gui;

import coinSim.authentication.authentication;

public class LoginUI extends authentication {
	public boolean userLogin() {
	    System.out.println("Logging In");
	    authentication auth = new authentication();
	    // calling validCredentials method of authentication
	    boolean validUser = auth.validCredentials();
	    return validUser;
	  }
}
