/**
 * A Ledger object which holds the Trader objects of the system.
 * @author Balaaj Arbab
 */

package coinSim.session;

import java.util.ArrayList;

public class Ledger {
	
	ArrayList<Trader> Traders;
	int TraderCount;
	
	public Ledger()
	{
		this.Traders = new ArrayList<Trader>();
		this.TraderCount = 0;
		
	}
	
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
	
	
	private boolean TraderExistsWithName(String name)
	{
		for (Trader trader : this.Traders)
		{
			if (trader.GetName().equals(name)) return true;
		}
		
		return false;
	}
	
	public ArrayList<Trader> GetTraders()
	{
		return this.Traders;
	}
	
	public Trader GetTraderAtIndex(int index)
	{
		return this.Traders.get(index);
	}
	
	public void SetStrategies(ArrayList<Integer> strategies)
	{
		for (int i = 0; i < this.TraderCount; i++)
		{
			GetTraderAtIndex(i).SetTradeStrategy(strategies.get(i));
		}
		
	}
	
	public void PrintStrategies()
	{
		for (Trader trader : this.Traders)
		{
			System.out.println(trader.GetTradeStrategy());
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
