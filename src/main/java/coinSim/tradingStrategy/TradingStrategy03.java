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

	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("solana", "dash"));
	public static String StrategyName = "Strategy-03";
	
    public boolean Enact(Trader trader, RecordTable recordTable) {
        
    	Coin solana = coinDB.GetCoin("solana");
    	Coin dash = coinDB.GetCoin("dash");
    	
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
    	
    	// buy Solana if its price goes under 125
    	// add transaction to table of records
    	if (solana.GetPrice() < 125d) {
    	    trader.BuyCoin("solana", 100);
    	    
    	    TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "solana", "Buy", "100", solana.GetPrice() + "");
    	    records.add(record);
    	}
    	
    	// if the price of Dash > price of Solana, sell Dash and buy Solana
    	// add both transactions to table of records
    	if (dash.GetPrice() > solana.GetPrice()) {
    	    trader.SellCoin("dash", 50);
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "dash", "Sell", "50", dash.GetPrice() + "");
            records.add(record);
            
            trader.BuyCoin("solana", 75);
            record = new TradeRecord(trader.GetName(), StrategyName, "solana", "Buy", "75", solana.GetPrice() + "");
            records.add(record);
    	}
    	
    	// if Solana price increases to 300 or falls below 100, sell
    	// record the trade
    	if (solana.GetPrice() > 300d || solana.GetPrice() < 100d) {
    	    trader.SellCoin("solana", 80);
    	    
    	    TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "solana", "Sell", "80", solana.GetPrice() + "");
    	    records.add(record);
    	}
    	
    	// if Dash price above 120, buy
    	// record the trade
    	if (dash.GetPrice() > 120d) {
            trader.BuyCoin("dash", 100);
            
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "dash", "Buy", "100", dash.GetPrice() + "");
            records.add(record);
    	}
    	
    	
    	for (int i = 0; i < records.size(); i++)
    	{
    		trader.IncrementTrades();
    	}
    		
    	recordTable.InsertRecords(records);
    	
    	
    	return true;
    		 	
    	
    }

}