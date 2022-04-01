package coinSim.gui;

import coinSim.authentication.login;

public class Runner extends login{
	public static void main(String[] args) {
	    // creating object of LoginUI
		LoginUI lgn = new LoginUI();
	    // calling userLogin method of LoginUI
	    boolean result = lgn.userLogin();
	    System.out.print(result);
	  }
}