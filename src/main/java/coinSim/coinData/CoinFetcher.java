/**
 * Fetches Coin data from CoinGecko API and constructs/updates Coin objects.
 * @author Balaaj Arbab
 */

package coinSim.coinData;

import coinSim.utils.DataFetcher;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CoinFetcher {
	
	private static DataFetcher fetcher = new DataFetcher();
	
	public static Coin FetchNewCoin(String id)
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
	
	public static Coin FetchNewCoin(String id, String dateString)
	{
		
		String symbol = fetcher.GetSymbol(id, dateString);
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		Coin coin = new Coin(id, symbol, price, mktCap, vol);
		
		return coin;
	}
	
	public static void FetchAndUpdateCoin(String id, Coin coin)
	{
		LocalDate date = LocalDate.now();
		String dateString = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		coin.UpdateCoin(price, mktCap, vol);
	}
	
	public static void FetchAndUpdateCoin(String id, String dateString, Coin coin)
	{
		
		double price = fetcher.getPriceForCoin(id, dateString);
		double mktCap = fetcher.getMarketCapForCoin(id, dateString);
		double vol = fetcher.getVolumeForCoin(id, dateString);
		
		coin.UpdateCoin(price, mktCap, vol);
	}
	
	public static void main(String[] args)
	{
		Coin testCoin = CoinFetcher.FetchNewCoin("bitcoin", "08-09-2020");
		
		System.out.println(testCoin);
		
		
		CoinFetcher.FetchAndUpdateCoin("bitcoin", testCoin);
		
		System.out.println(testCoin);
	}
	
	

}
