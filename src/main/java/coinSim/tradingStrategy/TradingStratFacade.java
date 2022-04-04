package coinSim.tradingStrategy;

import coinSim.coinData.*;
import coinSim.session.*;
import coinSim.records.*;

public class TradingStratFacade {
	
	public static boolean PerformTrade(Ledger ledger, RecordTable recordTable)
	{
		
		CoinFetcher.Fetch();
		
		// coinDB.PrintCoins();
		
		boolean atleastOneTradePerformed = false;
		
		for (Trader trader : ledger.GetTraders())
		{
			TradingStrategy strat = StrategyCreator.CreateStrategy(trader.GetTradeStrategy());
			
			
			if (strat != null)
			{					
				boolean tradePerformed = strat.Enact(trader, recordTable);
				
				if (!tradePerformed)
				{
					System.out.println("Trade not performed for " + trader.GetName() + " due to mismatch of coins of interest");
				}
				else atleastOneTradePerformed = true;
			}
		}
		
		return atleastOneTradePerformed;
	}

}
