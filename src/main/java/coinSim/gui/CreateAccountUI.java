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

public class CreateAccountUI implements ActionListener {

	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JButton buttonBack;
	private static JLabel success;
	private static JFrame frame;
	private static JPanel panel;
	private static String[] Args;

	public static void main(String[] args) {
		Args=args;
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
		button = new JButton("Create New Account");
		button.setBounds(10, 80, 200, 25);

		// Adding button action
		button.addActionListener(new CreateAccountUI());
		panel.add(button);
		
		// Adding buttonBack Button
		buttonBack = new JButton("Back");
		buttonBack.setBounds(220, 80, 80, 25);
		// Adding button action
		buttonBack.addActionListener(new LoginUI());
		panel.add(buttonBack);

		// Adding Success label i.e if login is successful
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);

		frame.setVisible(true);
		
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String user = userText.getText();
				String password = passwordText.getText();
				int passHash = Encryption.hashCode(password);
				String creds = "\n" + user + "," + passHash;

				boolean invalid = AccountCreateAuth.validCredentials(user);
				if (!invalid) {
					try {
						FileWriter myWriter = new FileWriter("users.txt", true);
						myWriter.write(creds);
						myWriter.close();
						System.out.println("Successfully wrote to the file.");
						// Call login either from here or from Runner.java on create account success
						success.setText("Account Created Successful!");
						
						frame.dispose();
						// And then call LoginUI
						LoginUI.main(Args);
						
						
					} catch (IOException e) {
						System.out.println("File reading error");
						e.printStackTrace();
					}
				} else {
					System.out.println("Account Exists");
					success.setText("Account Exists");
				}
			}
		});

		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				// And then call OurUI
				Runner.main(Args);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}