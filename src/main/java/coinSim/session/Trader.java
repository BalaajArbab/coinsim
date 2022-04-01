/**
 * Datatype representing a Trader.
 * @author Balaaj Arbab
 */

package coinSim.session;

import java.util.Hashtable;
import java.util.HashSet;

public class Trader {
	
	String Name;
	HashSet<String> CoinsOfInterest;
	int TradeStrategy;
	Hashtable<String, Double> CoinCounts;
	
	int TransactionsPerformed;
	
	int[] StrategyUseCount;
	
	public Trader(String name)
	{
		this.Name = name;
		this.TradeStrategy = -1;
		this.TransactionsPerformed = 0;
		
		CoinsOfInterest = new HashSet<String>();
		CoinCounts = new Hashtable<String, Double>(); 
		
		StrategyUseCount = new int[4];
		
		// initializeCoinCounts();
	}
	
	public void IncrementStrategyUseCount(int strategyNumber)
	{
		this.StrategyUseCount[--strategyNumber]++;
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
	
	public void UpdateCoinCount(String id, double newCount)
	{
		CoinCounts.put(id, newCount);
	}
	
	private void initializeCoinCounts()
	{
		for (String s : this.CoinsOfInterest)
		{
			this.CoinCounts.put(s, 0d);
		}
	}
	
	public boolean BuyCoin(String id, double amount)
	{
		double oldCount = this.CoinCounts.get(id);
		
		this.CoinCounts.put(id, oldCount + amount);
		
		return true;
	}
	
	public boolean SellCoin(String id, double amount)
	{
		double oldCount = this.CoinCounts.get(id);
		
		if (oldCount >= amount) 
		{
			this.CoinCounts.put(id, oldCount - amount);
			return true;
		}
		
		return false;
	}
	
	public void IncrementTrades()
	{
		this.TransactionsPerformed++;
	}
	
	public void EditName(String newName)
	{
		this.Name = newName;
	}
	
	public String GetName()
	{
		return this.Name;
	}
	
	public HashSet<String> GetCoinsOfInterest()
	{
		return this.CoinsOfInterest;
	}
	
	public int GetTradeStrategy() 
	{
		return this.TradeStrategy;
	}
	
	public int[] GetStratUseCounts()
	{
		return this.StrategyUseCount;
	}

}
