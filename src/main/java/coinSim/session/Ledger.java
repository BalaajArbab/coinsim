/**
 * A Ledger object which holds the Trader objects of the system.
 * @author Balaaj Arbab
 */

package coinSim.session;

import java.util.ArrayList;

public class Ledger {
	
	ArrayList<Trader> Traders;
	int TraderCount;
	
	boolean populateCalled = false;
	
	public Ledger()
	{
		this.Traders = new ArrayList<Trader>();
		this.TraderCount = 0;
		
	}
	
	
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
