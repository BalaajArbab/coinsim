/**
 * Class for the Trading Strategy 02. Inherits from the TradingStrategy class.
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

public class TradingStrategy02 extends TradingStrategy {

	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("dogecoin", "ethereum"));
	public static String StrategyName = "Strategy-02";
	
    public boolean Enact(Trader trader, RecordTable recordTable) {
        
    	Coin dogecoin = coinDB.GetCoin("dogecoin");
    	Coin ethereum = coinDB.GetCoin("ethereum");
    	
    	HashSet<String> traderCoinsOfInterest = trader.GetCoinsOfInterest();
    	
    	for (String s : coinsOfInterest)
    	{
    		if (!traderCoinsOfInterest.contains(s)) return false;
    	}
    	
    	ArrayList<TradeRecord> records = new ArrayList<TradeRecord>();
    	
    	if (dogecoin.GetPrice() < 1d)
    	{
    		trader.BuyCoin("bitcoin", 10000);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "dogecoin", "Buy", "10000", dogecoin.GetPrice() + "");
    		records.add(record);
    	}
    	
    	if (dogecoin.GetPrice() > 5d)
    	{
    		trader.SellCoin("bitcoin", 10000);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "dogecoin", "Sell", "10000", dogecoin.GetPrice() + "");
    		records.add(record);
    	}
    	
    	if (ethereum.GetPrice() > 2000d)
    	{
    		trader.SellCoin("ethereum", 20);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "ethereum", "Sell", "20", ethereum.GetPrice() + "");
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