Welcome to CoinSim


To run the program, navigate to Runner.java in coinSim.gui and run it. 
This will bring up a splash screen presenting the option to either Login or Create an account.

You may create a new account for yourself, or login with the existing accounts which are stored in users.txt.
	users.txt stores the credentials as follows
		username,encryptedPassword
	The accounts provided have their password set to "test"

Once your account is created, you may log in using those credentials
Once a valid user name and password combo is entered the trading window is opened.

In the CoinSim trading window, the yellow banner at the top indicates the available coins that you can trade with.

The buttons in the bottom right allow you to add/remove traders and make changes to their trading strategies.
	For a pre-configured traders list you may click on the "Populate Ledger With Traders" button.
After making changes to the trading strategies for traders you must click on the "Confirm Strategy Selection" button.
  
Once the desired traders, coins, and strategies have been entered, you can use the "Perform Trade" button to action a trade.
	Due to CoinGecko API limitations, a trade can only be performed every 30 seconds.