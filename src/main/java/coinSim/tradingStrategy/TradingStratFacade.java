package coinSim.tradingStrategy;

import coinSim.coinData.*;
import coinSim.session.*;
import coinSim.records.*;

/**
 * Implements the Facade design pattern to perform a tradde operation in the software. The facade
 * DP hides the implementation details and simplifies the call.
 * 
 * @author  Balaaj A.
 * 
 */

public class TradingStratFacade {
	
	/**
     * @param  ledger
     * 		   The ledger object of trades to perform trades with.
     *         
     * @param  recordTable
     *         The RecordTable object which holds the history of trades performed.
     *         
     * @return returns a boolean of whether at least one trade was performed or not.
     */
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
