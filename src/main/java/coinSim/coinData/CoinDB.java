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
	
	private Hashtable<String, Coin> CoinDict;
	
	private CoinDB()
	{
		CoinDict = new Hashtable<String, Coin>();
	}
	
	public static CoinDB GetInstance()
	{
		if (instance == null)
		{
			instance = new CoinDB();
		}
		
		return instance;
	}
	
	public boolean AddCoin(String id, Coin coin)
	{
		if (!this.CoinDict.containsKey(id))
		{
			this.CoinDict.put(id, coin);
			return true;
		}
		
		return false;
	}
	
	public Coin GetCoin(String id)
	{
		return this.CoinDict.get(id);
	}
	
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

}
