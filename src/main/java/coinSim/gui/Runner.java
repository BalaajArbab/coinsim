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

/**
 * The {@code Runner} parent class. Is the splash screen providing the user the option to login or create an account
 * with credentials entered during login.
 * 
 * @author  Anubhav A.
 */
public class Runner implements ActionListener {
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel titleLabel;
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

	/**
	 * Constructs the UI interface for the Runner Page
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
		titleLabel = new JLabel("Welcome to CoinSim");
		titleLabel.setBounds(110, 20, 130, 50);
		panel.add(titleLabel);
		
		

		// Adding Login Button
		button = new JButton("Login");
		button.setBounds(10, 80, 150, 25);

		// Adding button action
		button.addActionListener(new Runner());
		panel.add(button);

		// Adding Login Button
		button2 = new JButton("Create Account");
		button2.setBounds(180, 80, 150, 25);

		// Adding button action
		button2.addActionListener(new Runner());
		panel.add(button2);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		/**
		 * Method is called when the login button is clicked which leads to the login page
		 */
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				LoginUI.main(Args);
			}
		});

		/**
		 * Method is called when the Create Account button is clicked which leads to the Create Account page
		 */
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				CreateAccountUI.main(Args);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}