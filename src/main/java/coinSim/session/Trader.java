package coinSim.session;

import java.util.Hashtable;
import java.util.HashSet;

public class Trader {
	
	String Name;
	HashSet<String> CoinsOfInterest;
	int TradeStrategy;
	Hashtable<String, Double> CoinCounts;
	
	public Trader(String name)
	{
		this.Name = name;
		this.TradeStrategy = -1;
		
		CoinsOfInterest = new HashSet<String>();
		CoinCounts = new Hashtable<String, Double>(); 
	}
	
	public void AddCoinOfInterest(String id)
	{
		if (!CoinsOfInterest.contains(id))
		{
			this.CoinsOfInterest.add(id);
			CoinCounts.put(id, 0d);
		}
	}
	
	public void RemoveCoinOfInterest(String id)
	{
		this.CoinsOfInterest.remove(id);
	}
	
	public void SetTradeStrategy(int number)
	{
		this.TradeStrategy = number;
	}
	
	public void updateCoinCount(String id, double newCount)
	{
		CoinCounts.put(id, newCount);
	}

}
