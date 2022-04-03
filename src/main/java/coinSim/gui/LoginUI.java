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

import coinSim.authentication.authentication;

public class LoginUI implements ActionListener {

	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JButton button2;
	private static JLabel success;
	private static boolean finalResult;
	private static String user;
	private static String pass;
	private static JFrame frame;
	private static String[] Args;

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

		// Adding labels
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);

		// Adding textfield with a default length of 20
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);

		// Adding password label and password textbox
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);

		// Adding Login Button
		button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);

		// Adding button action
		button.addActionListener(new LoginUI());
		panel.add(button);

		/*
		 * 
		 * //Adding Create Account Button button2 = new JButton("Create Account");
		 * button2.setBounds(100,80,200,25);
		 * 
		 * //Adding button action button2.addActionListener(new createAccountUI());
		 * panel.add(button2);
		 */

		// Adding Success label i.e if login is successful
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String user = userText.getText();
		String password = passwordText.getText();
		int passHash = authentication.hashCode(password);
		String creds = user + "," + passHash;
		// System.out.println(user +","+password);

		boolean result = authentication.validCredentials(creds);

		System.out.println(result);
		if (result == true) {

			success.setText("Login Successful!");
			frame.dispose();
			// And then call OurUI
			OurUI.main(Args);
		}

		else {
			success.setText("Incorrect Credentials!");
		}
	}

}
