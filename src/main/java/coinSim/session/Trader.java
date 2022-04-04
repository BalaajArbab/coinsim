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
	
	/**
	 * Creates a trader with given name.
	 * 
	 * @param name
	 */
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
	
	/**
	 * Increments the use count of the given strategy number for this trader object.
	 * 
	 * @param strategyNumber
	 */
	public void IncrementStrategyUseCount(int strategyNumber)
	{
		this.StrategyUseCount[--strategyNumber]++;
	}
	
	/** Adds the id as a coin of interest for this trader object.
	 * 
	 * @param id
	 */
	public void AddCoinOfInterest(String id)
	{
		if (!CoinsOfInterest.contains(id))
		{
			this.CoinsOfInterest.add(id);
			CoinCounts.put(id, 0d);
		}
	}
	
	/** Removes the id as a coin of interest for this trader object.
	 * 
	 * @param id
	 */
	public void RemoveCoinOfInterest(String id)
	{
		this.CoinsOfInterest.remove(id);
	}
	
	/** Sets the trade strategy for this trader object.
	 * 
	 * @param number
	 */
	public void SetTradeStrategy(int number)
	{
		this.TradeStrategy = number;
	}
	
	/**
	 * 
	 * @param id
	 * @param newCount
	 */
	public void UpdateCoinCount(String id, double newCount)
	{
		CoinCounts.put(id, newCount);
	}
	
	/**
	 * 
	 */
	private void initializeCoinCounts()
	{
		for (String s : this.CoinsOfInterest)
		{
			this.CoinCounts.put(s, 0d);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param amount
	 * @return
	 */
	public boolean BuyCoin(String id, double amount)
	{
		double oldCount = this.CoinCounts.get(id);
		
		this.CoinCounts.put(id, oldCount + amount);
		
		return true;
	}
	
	/**
	 * 
	 * @param id
	 * @param amount
	 * @return
	 */
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
	
	/**
	 * Increment the trades performed for this trader.
	 */
	public void IncrementTrades()
	{
		this.TransactionsPerformed++;
	}
	
	/**
	 * Edits the name of this trader object.
	 * @param newName
	 */
	public void EditName(String newName)
	{
		this.Name = newName;
	}
	
	/**
	 * 
	 * @return name of this trader object.
	 */
	public String GetName()
	{
		return this.Name;
	}
	
	/**
	 * 
	 * @return HashSet of the coins of interest of this trader object.
	 */
	public HashSet<String> GetCoinsOfInterest()
	{
		return this.CoinsOfInterest;
	}
	
	/**
	 * 
	 * @return the int representing the trade strategy of this trader object.
	 */
	public int GetTradeStrategy() 
	{
		return this.TradeStrategy;
	}
	
	/**
	 * 
	 * @return an integer array of the usage counts of strategies.
	 */
	public int[] GetStratUseCounts()
	{
		return this.StrategyUseCount;
	}

}
