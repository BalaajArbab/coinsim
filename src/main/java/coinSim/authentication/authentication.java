package coinSim.authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class authentication {
    // Account valid
    public boolean validCredentials(String creds) {
        File myObj = new File("users.txt");
        if (myObj.exists()) {
            Scanner sc;
            try {
                sc = new Scanner(myObj);
                //returns true if there is another line to read  
                while (sc.hasNextLine()) {
                    if (sc.nextLine().equals(creds)) {
                    	return true;
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } //file to be scanned  
            //closes the scanner
        } else {
            System.out.println("The file does not exist.");
            return false;
        }
		return false;
    }
}