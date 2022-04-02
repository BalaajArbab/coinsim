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

    // set of coins necessary for this trading strategy
    private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("dogecoin", "ethereum"));
    // name of the trading strategy
	public static String StrategyName = "Strategy-02";
	
    /**
     * @param  trader
     *         Trader object of the trader performing a trade using this strategy
     *         
     * @param  recordTable
     *         keeps record of trades performed        
     */
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
    	
    	trader.IncrementStrategyUseCount(2);
    	
    	ArrayList<TradeRecord> records = new ArrayList<TradeRecord>();
    	
    	if (dogecoin.GetPrice() < 1d)
    	{
    		trader.BuyCoin("dogecoin", 10000);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), StrategyName, "dogecoin", "Buy", "10000", dogecoin.GetPrice() + "");
    		records.add(record);
    	}
    	
    	if (dogecoin.GetPrice() > 5d)
    	{
    		trader.SellCoin("dogecoin", 10000);
    		
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