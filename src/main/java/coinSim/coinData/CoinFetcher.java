package coinSim.coinData;

import coinSim.utils.DataFetcher;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Fetches Coin data from CoinGecko API and constructs/updates Coin objects.
 * 
 * @author  Balaaj Arbab
 * @author  Arjav R.
 */
public class CoinFetcher {
	
	private static DataFetcher fetcher = new DataFetcher();
	private static CoinDB coinDB = CoinDB.GetInstance();
	
	private static Coin FetchNewCoin(String id)
	{
		LocalDate date = LocalDate.now();
		String dateString = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		String symbol = fetcher.GetSymbol(id, dateString);
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		Coin coin = new Coin(id, symbol, price, mktCap, vol);
		
		return coin;
	}
	
	private static Coin FetchNewCoin(String id, String dateString)
	{
		
		String symbol = fetcher.GetSymbol(id, dateString);
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		Coin coin = new Coin(id, symbol, price, mktCap, vol);
		
		return coin;
	}
	
	private static void FetchAndUpdateCoin(String id, Coin coin)
	{
		LocalDate date = LocalDate.now();
		String dateString = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		coin.UpdateCoin(price, mktCap, vol);
	}
	
	private static void FetchAndUpdateCoin(String id, String dateString, Coin coin)
	{
		
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		coin.UpdateCoin(price, mktCap, vol);
	}
	
	/**
	 * Fetches data on all cryptocoins specified in coinDB.coinList and updates the associated
	 * coin object.
	 */
	public static void Fetch()
	{
		for (String id : CoinDB.coinList)
		{
			Coin coin = coinDB.GetCoin(id);
			
			FetchAndUpdateCoin(id, coin);
			
			// delay to prevent exceeding api call limits
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			
		}
		
		coinDB.PrintCoins();
	}
	
	/**
	 * Fetches data on a crypto coin and adds the it as a Coin object to the database
	 * of coins coinDB
	 * 
	 * 
	 */
	public static void FetchFirstTime()
	{
		for (String id : CoinDB.coinList)
		{
			Coin coin = FetchNewCoin(id);
			coinDB.AddCoin(id, coin);
			
			// delay to prevent exceeding api call limits
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
		
		coinDB.PrintCoins();
		
	}
	
	
//	public static void main(String[] args)
//	{
//		Coin testCoin = CoinFetcher.FetchNewCoin("bitcoin", "08-09-2020");
//		
//		System.out.println(testCoin);
//		
//		
//		CoinFetcher.FetchAndUpdateCoin("bitcoin", testCoin);
//		
//		System.out.println(testCoin);
//	}
//	
	

}
