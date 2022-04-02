package coinSim.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import coinSim.authentication.authentication;

public class CreateAccountUI implements ActionListener{

	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	
	
	public static void main(String[] args) {
		
		
		
		//creating frame
		JFrame frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			
		//creating panel
		JPanel panel = new JPanel();
		//Put Panel on the frame
		frame.add(panel);
		
		//Configuring the panel
		panel.setLayout(null);
		
		//Adding labels
		 userLabel = new JLabel("User");
		userLabel.setBounds(10,20,80,25);
		panel.add(userLabel);
		
		//Adding textfield with a default length of 20
		 userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		//Adding password label and password textbox
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		
		//Adding Login Button
		button = new JButton("Create New Account");
		button.setBounds(10,80,200,25);
		
		//Adding button action
		button.addActionListener(new CreateAccountUI());
		panel.add(button);
		
		
		//Adding Success label i.e if login is successful
		success = new JLabel("");
		success.setBounds(10,110,300,25);
		panel.add(success);
		
	
		
		
	
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String user = userText.getText();
		String password = passwordText.getText();
		System.out.println(user +","+ password);	
		
		
		/*
		 boolean result =authentication.validCredentials(creds);
		
		System.out.println(result);
		if(result == true)
		{
			
			success.setText("Login Successful!");
			frame.dispose();
			//And then call OurUI
			OurUI.main(Args);
		}
		
		else
		{
			success.setText("Incorrect Credentials!");
		}
		 */
	}

}