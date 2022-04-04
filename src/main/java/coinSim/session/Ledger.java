
package coinSim.session;

import java.util.ArrayList;

/**
 * A Ledger object which holds the Trader objects of the system.
 * @author Balaaj Arbab
 */

public class Ledger {
	
	ArrayList<Trader> Traders;
	int TraderCount;
	
	boolean populateCalled = false;
	
	public Ledger()
	{
		this.Traders = new ArrayList<Trader>();
		this.TraderCount = 0;
		
	}
	
	/**
	 * Populates the ledger object with some preset hard-coded traders.
     * 
     */
	public void CreateDefaultLedger()
	{
//		this.Traders.clear();
		if(populateCalled) return;
		
		Trader trader1 = new Trader("Trader 1");
		trader1.AddCoinOfInterest("bitcoin");
		trader1.AddCoinOfInterest("ethereum");
		trader1.SetTradeStrategy(1);
		
		Trader trader2 = new Trader("Tester 2");
		trader2.AddCoinOfInterest("ethereum");
		trader2.AddCoinOfInterest("dogecoin");
		trader2.SetTradeStrategy(2);
		
		Trader trader3 = new Trader("Tai-Lung");
		trader3.AddCoinOfInterest("dogecoin");
		trader3.SetTradeStrategy(1);
		
		Trader trader4 = new Trader("Ranchoddas Chanchad");
		trader4.AddCoinOfInterest("solana");
		trader4.AddCoinOfInterest("dash");
		trader4.SetTradeStrategy(3);
		
		Trader trader5 = new Trader("Shin Chan");
		trader5.AddCoinOfInterest("avalanche-2");
		trader5.AddCoinOfInterest("terra-luna");
		trader5.AddCoinOfInterest("aave");
		trader5.AddCoinOfInterest("maker");
		trader5.SetTradeStrategy(4);
		
		Trader trader6 = new Trader("Sachin Tendulkar");
		trader6.AddCoinOfInterest("bitcoin");
		trader6.AddCoinOfInterest("ethereum");
		trader6.AddCoinOfInterest("dogecoin");
		trader6.SetTradeStrategy(2);
		
		this.Traders.add(trader1);
		this.Traders.add(trader2);
		this.Traders.add(trader3);
		this.Traders.add(trader4);
		this.Traders.add(trader5);
		this.Traders.add(trader6);
		this.TraderCount += 6;
		
		this.populateCalled = true;
	}
	
	/**
     * @param  name
     * 		   The name of the trader to be added.
     *         
     * @return returns a boolean of whether the trader was successfully added or not.
     */
	public boolean AddTrader(String name)
	{
		if (!TraderExistsWithName(name))
		{
			Trader trader = new Trader(name);
			
			this.Traders.add(trader);
			this.TraderCount++;
			
			return true;
		}
		
		return false;
	}
	
	/**
     * @param  name
     * 		   The name of the trader to be removed.
     *         
     * @return returns a boolean of whether the trader was successfully removed or not.
     */
	public boolean RemoveTrader(int index)
	{
		// TODO
		if (index < this.Traders.size())
		{
			this.Traders.remove(index);
			this.TraderCount--;
			
			return true;
		}
		
		return false;
	}
	
	/**
     * @param  name
     * 		   The name to be checked.
     *         
     * @return returns a boolean of whether a trader with given name exists or not.
     */
	private boolean TraderExistsWithName(String name)
	{
		for (Trader trader : this.Traders)
		{
			if (trader.GetName().equals(name)) return true;
		}
		
		return false;
	}
	
	/**
     *         
     * @return returns a list of Traders currently in the ledger.
     */
	public ArrayList<Trader> GetTraders()
	{
		return this.Traders;
	}
	
	/**
     *         
     * @return returns the Trader at given index.
     */
	public Trader GetTraderAtIndex(int index)
	{
		return this.Traders.get(index);
	}
	
	/**
	 * Sets the strategies of trader objects by index.
     *         
     * @param A vector of the strategies to set for the corresponding trader.
     */
	public void SetStrategies(ArrayList<Integer> strategies)
	{
		for (int i = 0; i < this.TraderCount; i++)
		{
			GetTraderAtIndex(i).SetTradeStrategy(strategies.get(i));
		}
		
	}
	
//	public void PrintStrategies()
//	{
//		for (Trader trader : this.Traders)
//		{
//			System.out.println(trader.GetTradeStrategy());
//		}
//	}
//	
	

}
