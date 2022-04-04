package coinSim.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import coinSim.authentication.AccountCreateAuth;
import coinSim.authentication.Encryption;
import coinSim.authentication.authentication;

/**
 * The {@code CreateAccountUI} parent class. Contains the UI for creating the
 * account.
 * 
 * @author Anubhav A.
 * @author Rishabh J.
 * 
 */
public class CreateAccountUI implements ActionListener {

	private static JLabel userLabel;
	private static JLabel titleLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JButton buttonBack;
	private static JLabel success;
	private static JFrame frame;
	private static JPanel panel;
	private static String[] Args;

	/**
	 * Constructs the UI interface for the CreateAccount Page
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Args = args;
		// creating frame
		frame = new JFrame();
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// creating panel
		panel = new JPanel();
		// Put Panel on the frame
		frame.add(panel);

		// Configuring the panel
		panel.setLayout(null);

		// Adding Title Label
		titleLabel = new JLabel("Create Account Page");
		titleLabel.setBounds(110, 20, 130, 25);
		panel.add(titleLabel);

		// Adding labels
		userLabel = new JLabel("User");
		userLabel.setBounds(50, 50, 80, 25);
		panel.add(userLabel);

		// Adding textfield with a default length of 20
		userText = new JTextField(20);
		userText.setBounds(130, 50, 165, 25);
		panel.add(userText);

		// Adding password label and password textbox
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 90, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(130, 90, 165, 25);
		panel.add(passwordText);

		// Adding Login Button
		button = new JButton("Confirm");
		button.setBounds(80, 130, 80, 25);

		// Adding button action
		button.addActionListener(new CreateAccountUI());
		panel.add(button);

		// Adding buttonBack Button
		buttonBack = new JButton("Back");
		buttonBack.setBounds(170, 130, 80, 25);
		// Adding button action
		buttonBack.addActionListener(new LoginUI());
		panel.add(buttonBack);

		// Adding Success label i.e if login is successful
		success = new JLabel("");
		success.setBounds(130, 150, 300, 25);
		panel.add(success);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		/**
		 * Method is called when the confirm button is clicked which leads to the login
		 * page once account creation is confirmed
		 */
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// Collects the username and password from the text fields
				String user = userText.getText();
				String password = passwordText.getText();
				// Passes the password to the hashCode function which returns the hashed
				// password
				int passHash = Encryption.hashCode(password);
				// creds set to the user credentials formatted correctly
				String creds = "\n" + user + "," + passHash;

				// passes the creds to validCredentials to check if the user already exists
				boolean invalid = AccountCreateAuth.validCredentials(user);

				// If it is not invalid (user does not exist) writes the credentials to the file
				if (!invalid) {
					try {
						FileWriter myWriter = new FileWriter("users.txt", true);
						myWriter.write(creds);
						myWriter.close();
						success.setText("Account Created Successful!");
						frame.dispose();
						// On successful account creation login is called
						LoginUI.main(Args);

					} catch (IOException e) {
						System.out.println("File reading error");
						e.printStackTrace();
					}
				} else {
					success.setText("Account Exists");
				}
			}
		});

		/**
		 * Method is called when the back button is clicked which leads to the Runner
		 * page
		 */
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				Runner.main(Args);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}