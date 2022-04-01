/**
 * Class for the Trading Strategy 03. Inherits from the TradingStrategy class.
 * 
 * @author Arjav R., Balaaj A.
 * 
 */

package coinSim.tradingStrategy;

import coinSim.session.Trader;
import coinSim.coinData.*;
import coinSim.records.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;

public class TradingStrategy03 extends TradingStrategy {

	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("dogecoin", "ethereum"));
	public static String StrategyName = "Strategy-03";
	
    public boolean Enact(Trader trader, RecordTable recordTable) {
        
    	Coin dogecoin = coinDB.GetCoin("dogecoin");
    	Coin ethereum = coinDB.GetCoin("ethereum");
    	
    	HashSet<String> traderCoinsOfInterest = trader.GetCoinsOfInterest();
    	
    	
    	for (String s : coinsOfInterest)
    	{
    		if (!traderCoinsOfInterest.contains(s))
    		{
    			TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "N/A", "FAIL", "N/A", "N/A");
    			recordTable.InsertRecord(record);
    			return false;
    		}
    	}	
    	
    	trader.IncrementStrategyUseCount(3);
    	
    	ArrayList<TradeRecord> records = new ArrayList<TradeRecord>();
    	
    	// ADD TRADE FUNCTIONALITY, also change coins
    	
    	
    	for (int i = 0; i < records.size(); i++)
    	{
    		trader.IncrementTrades();
    	}
    		
    	recordTable.InsertRecords(records);
    	
    	
    	return true;
    		 	
    	
    }

}