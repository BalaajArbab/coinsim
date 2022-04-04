/**
 * Database structure to hold Coin objects.
 * @author Balaaj Arbab
 */

package coinSim.coinData;

import java.util.Hashtable;
import java.util.ArrayList;

public class CoinDB {
	
	// Singleton
	private static CoinDB instance;
	
	public static String[] coinList = new String[] {"bitcoin", "ethereum", "dogecoin", "solana", "dash", "terra-luna", "avalanche-2", "aave", "maker"};
	
	private Hashtable<String, Coin> CoinDict;
	
	private CoinDB()
	{
		CoinDict = new Hashtable<String, Coin>();
	}
	
	/**
     * Implements the Singleton design pattern to create a transaction database of cryptocoins
     * to be used throughout the program.
     */
	public static CoinDB GetInstance()
	{
		if (instance == null)
		{
			instance = new CoinDB();
		}
		
		return instance;
	}
	
	/**
	 * 		  Adds a coin to the DB.
	 * 
     * @param id
     * 		  id of the coin to be added
     * @param coin
     * 		  Coin object representing the coin to be added.
     */
	public boolean AddCoin(String id, Coin coin)
	{
		if (!this.CoinDict.containsKey(id))
		{
			this.CoinDict.put(id, coin);
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param id
	 * 		  id of the Coin object to be returned.
	 * 
     * @return returns a Coin object
     */
	public Coin GetCoin(String id)
	{
		return this.CoinDict.get(id);
	}
	
	/**
	 * @param id
	 * 		  List of ids of the Coin objects to be returned.
	 * 
     * @return returns a list of Coin objects.
     */
	public ArrayList<Coin> GetCoins(ArrayList<String> ids)
	{
		ArrayList<Coin> coinsToReturn = new ArrayList<Coin>();
		
		for (String id : ids)
		{
			if (this.CoinDict.containsKey(id))
			{
				coinsToReturn.add(this.CoinDict.get(id));
			}
		}
		
		return coinsToReturn;
	}
	
	/**
	 * Prints all coins currently in the database.
     */
	public void PrintCoins()
	{
		for (Coin coin : CoinDict.values())
		{
			System.out.println(coin);
		}
	}

}
