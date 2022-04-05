# Welcome to CoinSim
Developed by: Rishabh, Balaaj, Arjav, Anubhav 

## Running the program
To run the program, navigate to Runner.java in coinSim.gui and run it. 
This will bring up a splash screen presenting the option to either Login or Create an account.
For best results, use a 1920x1080 resolution or greater screen, with scale set to 100%.
Lower resolutions may cause unintended GUI rendering issues.

## Logging in
You may create a new account for yourself, or login with the existing accounts which are stored in users.txt.
	users.txt stores the credentials as follows
		username,encryptedPassword
	The accounts provided have their password set to "test"

Once your account is created, you may log in using those credentials
Once a valid user name and password combo is entered the trading window is opened.

## Trading UI
In the CoinSim trading window, the yellow banner at the top indicates the available coins that you can trade with.

The buttons in the bottom right allow you to add/remove traders and make changes to their trading strategies.
To add coins to a trader, select it in the window and type either one coin or a list of coins separated by spaces.
For a pre-configured traders list you may click on the "Populate Ledger With Traders" button.
After making changes to the trading strategies for traders you must click on the "Confirm Strategy Selection" button. This will lock in the selected trading strategies for all traders.
  
Once the desired traders, coins, and strategies have been entered, you can use the "Perform Trade" button to action a trade.
Due to CoinGecko API limitations, a trade can only be performed every 30 seconds.
