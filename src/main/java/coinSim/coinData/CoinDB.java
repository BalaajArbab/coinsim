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
	
	public void AddCoin(String symbol, Coin coin)
	{
		this.CoinDict.put(symbol, coin);
	}
	
	public Coin GetCoin(String symbol)
	{
		return this.CoinDict.get(symbol);
	}
	
	public ArrayList<Coin> GetCoins(ArrayList<String> symbols)
	{
		ArrayList<Coin> coinsToReturn = new ArrayList<Coin>();
		
		for (String symbol : symbols)
		{
			if (this.CoinDict.containsKey(symbol))
			{
				coinsToReturn.add(this.CoinDict.get(symbol));
			}
		}
		
		return coinsToReturn;
	}

}
