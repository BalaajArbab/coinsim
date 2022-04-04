package coinSim.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import coinSim.authentication.Encryption;
import coinSim.authentication.authentication;

/**
 * The {@code LoginUI} parent class. Contains instantiation of Login UI.
 * 
 * @author Anubhav A.
 * @author Rishabh J.
 * 
 */
public class LoginUI implements ActionListener {

	private static JLabel userLabel;
	private static JLabel titleLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JButton buttonBack;
	private static JLabel success;
	private static boolean finalResult;
	private static String user;
	private static String pass;
	private static JFrame frame;
	private static String[] Args;

	/**
	 * Constructs the UI interface for the Login Page
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
		JPanel panel = new JPanel();
		// Put Panel on the frame
		frame.add(panel);

		// Configuring the panel
		panel.setLayout(null);

		// Adding title label
		titleLabel = new JLabel("Login Page");
		titleLabel.setBounds(130, 20, 80, 25);
		panel.add(titleLabel);

		// Adding labels
		userLabel = new JLabel("User");
		userLabel.setBounds(20, 50, 80, 25);
		panel.add(userLabel);

		// Adding textfield with a default length of 20
		userText = new JTextField(20);
		userText.setBounds(100, 50, 165, 25);
		panel.add(userText);

		// Adding password label and password textbox
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(20, 90, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(100, 90, 165, 25);
		panel.add(passwordText);

		// Adding Login Button
		button = new JButton("Login");
		button.setBounds(80, 130, 80, 25);

		// Adding button action
		button.addActionListener(new LoginUI());
		panel.add(button);

		// Adding buttonBack Button
		buttonBack = new JButton("Back");
		buttonBack.setBounds(170, 130, 80, 25);
		// Adding button action
		buttonBack.addActionListener(new LoginUI());
		panel.add(buttonBack);

		// Adding Success label i.e if login is successful
		success = new JLabel("");
		success.setBounds(100, 150, 300, 25);
		panel.add(success);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		/**
		 * Method is called when the login button is clicked which leads to the ourUI
		 * page
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
				String creds = user + "," + passHash;

				// passes the creds to validCredentials to check if the entered credentials
				// match stored records
				boolean result = authentication.validCredentials(creds);

				// On authorization proceed to OurUI
				if (result == true) {
					success.setText("Login Successful!");
					frame.dispose();
					OurUI.main(Args);
				}

				// Inform user of incorrect details
				else {
					success.setText("Incorrect Credentials!");
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
				// And then call Runner
				Runner.main(Args);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}
