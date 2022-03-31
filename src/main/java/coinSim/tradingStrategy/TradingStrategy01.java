/**
 * Class for the Trading Strategy 01. Inherits from the TradingStrategy class.
 * 
 * @author Arjav R.
 * 
 */

package coinSim.tradingStrategy;

import coinSim.session.Trader;
import coinSim.coinData.*;
import coinSim.records.*;

import java.util.Arrays;
import java.util.HashSet;

public class TradingStrategy01 extends TradingStrategy {
    
	private static HashSet<String> coinsOfInterest = new HashSet<String>(Arrays.asList("bitcoin", "ethereum", "dogecoin"));
	public static String name = "Strategy-01";
	
    public boolean Enact(Trader trader) {
        
    	Coin bitcoin = coinDB.GetCoin("bitcoin");
    	Coin ethereum = coinDB.GetCoin("ethereum");
    	Coin dogecoin = coinDB.GetCoin("dogecoin");
    	
    	HashSet<String> traderCoinsOfInterest = trader.GetCoinsOfInterest();
    	
    	for (String s : coinsOfInterest)
    	{
    		if (!traderCoinsOfInterest.contains(s)) return false;
    	}
    	
    	if (bitcoin.GetPrice() > 30000d)
    	{
    		trader.BuyCoin("bitcoin", 1);
    		
    		TradeRecord record = new TradeRecord(trader.GetName(), name, "bitcoin", "Buy", "1", bitcoin.GetPrice() + "");
    		System.out.println(record);
    	}
    	
    	
    	trader.IncrementTrades();
    	return true;
    	
    	
    	
    	
    	
    }
    
    public static void main(String[] args)
    {
    	for (String s : coinsOfInterest)
    	{
    		System.out.println(s);
    	}
    }

}