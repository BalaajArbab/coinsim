/**
 * A Ledger object which holds the Trader objects of the system.
 * @author Balaaj Arbab
 */

package coinSim.session;

import java.util.ArrayList;

public class Ledger {
	
	ArrayList<Trader> Traders;
	
	public Ledger()
	{
		Traders = new ArrayList<Trader>();
	}
	
	public boolean AddTrader(String name)
	{
		if (!TraderExistsWithName(name))
		{
			Trader trader = new Trader(name);
			
			Traders.add(trader);
			
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
			
			return true;
		}
		
		return false;
	}
	
	
	private boolean TraderExistsWithName(String name)
	{
		for (Trader trader : Traders)
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
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
