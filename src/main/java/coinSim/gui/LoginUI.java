package coinSim.gui;


import coinSim.authentication.authentication;

public class LoginUI extends authentication {
	public boolean userLogin() {
	    System.out.println("Logging In");
	    
	    authentication auth = new authentication();
	    
	    
	    String user = "rishabh";
	    String pass = "password1";
	    String creds = user + "," + pass;
	    // calling validCredentials method of authentication
	    boolean validUser = auth.validCredentials(creds);
	    return validUser;
	  }
}
