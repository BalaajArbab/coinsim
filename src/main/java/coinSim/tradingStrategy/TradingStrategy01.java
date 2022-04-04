package coinSim.tradingStrategy;

import coinSim.session.Trader;
import coinSim.coinData.*;
import coinSim.records.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * The {@code TradingStrategy01} class inherits from the {@code TradingStrategy} class.
 * It represents the trading strategy called "Strategy-01" that deals with the buying
 * and selling of BTC and ETH crypto coins.
 * 
 * @author  Arjav R.
 * @author  Balaaj A.
 * 
 */
public class TradingStrategy01 extends TradingStrategy {
    
	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("bitcoin", "ethereum"));
	public static String StrategyName = "Strategy-01";
	
    /**
     * 
     * 
     * @param  trader
     *         Trader object of the trader performing a trade using this strategy
     *         
     * @param  recordTable
     *         keeps record of all trades performed across traders
     */
    public boolean Enact(Trader trader, RecordTable recordTable) {
        
    	Coin bitcoin = coinDB.GetCoin("bitcoin");
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
    	
    	trader.IncrementStrategyUseCount(1);
    	
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