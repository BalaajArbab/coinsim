/**
 * Class for the Trading Strategy 01. Inherits from the TradingStrategy class.
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

public class TradingStrategy01 extends TradingStrategy {
    
	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("bitcoin", "ethereum"));
	public static String StrategyName = "Strategy-01";
	
    public boolean Enact(Trader trader, RecordTable recordTable) {
        
    	Coin bitcoin = coinDB.GetCoin("bitcoin");
    	Coin ethereum = coinDB.GetCoin("ethereum");
    	
    	HashSet<String> traderCoinsOfInterest = trader.GetCoinsOfInterest();
    	
    	for (String s : coinsOfInterest)
    	{
    		if (!traderCoinsOfInterest.contains(s)) return false;
    	}
    	
    	ArrayList<TradeRecord> records = new ArrayList<TradeRecord>();
    	
    	if (bitcoin.GetPrice() > 30000d)
    	{
    		trader.BuyCoin("bitcoin", 1);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "bitcoin", "Buy", "1", bitcoin.GetPrice() + "");
    		records.add(record);
    	}
    	
    	if (ethereum.GetPrice() > 1500d)
    	{
    		trader.SellCoin("ethereum", 5);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "ethereum", "Sell", "5", ethereum.GetPrice() + "");
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