/**
 * Class for the Trading Strategy 04. Inherits from the TradingStrategy class.
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

public class TradingStrategy04 extends TradingStrategy {

	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("terra-luna", "avalanche-2", "aave", "maker"));
	public static String StrategyName = "Strategy-04";
	
    public boolean Enact(Trader trader, RecordTable recordTable) {
        
    	Coin terra = coinDB.GetCoin("terra-luna");
    	Coin avalanche = coinDB.GetCoin("avalanche-2");
    	Coin aave = coinDB.GetCoin("aave");
    	Coin maker = coinDB.GetCoin("maker");
    	
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
    	
    	trader.IncrementStrategyUseCount(4);
    	
    	ArrayList<TradeRecord> records = new ArrayList<TradeRecord>();
    	
    	// sell Terra if above 175
    	if (terra.GetPrice() > 175d) {
    	    trader.SellCoin("terra-luna", 100);
    	    
    	    TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "terra-luna", "Sell", "100", terra.GetPrice() + "");
    	    records.add(record);
    	}
    	
    	if (terra.GetPrice() < 130d) {
    	    trader.BuyCoin("terra-luna", 150);
    	    
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "terra-luna", "Buy", "150", terra.GetPrice() + "");
            records.add(record);
    	}
    	
    	if (aave.GetPrice() > 400d) {
    	    trader.SellCoin("aave", 75);
    	    
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "aave", "Sell", "75", aave.GetPrice() + "");
            records.add(record);
    	}
    	
        if (aave.GetPrice() < 250d) {
            trader.BuyCoin("aave", 100);
            
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "aave", "Buy", "100", aave.GetPrice() + "");
            records.add(record);
        }

        if (avalanche.GetPrice() > 160) {
    	    trader.SellCoin("avalanche-2", 200);
    	    
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "avalanche-2", "Sell", "200", avalanche.GetPrice() + "");
            records.add(record);
    	}
    	
    	if (avalanche.GetPrice() < 120) {
    	    trader.BuyCoin("avalanche-2", 500);
    	    
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "avalanche-2", "Buy", "500", avalanche.GetPrice() + "");
            records.add(record);
    	}
    	
    	if (maker.GetPrice() > 3500) {
    	    trader.SellCoin("maker", 10);
    	        	    
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "maker", "Sell", "10", maker.GetPrice() + "");
            records.add(record);
    	}
    	
    	if (maker.GetPrice() < 2200) {
    	    trader.BuyCoin("maker", 20);
    	    
            TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "maker", "Buy", "20", maker.GetPrice() + "");
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