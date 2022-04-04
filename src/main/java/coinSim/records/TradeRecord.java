package coinSim.records;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Holds the data for a trade performed by a broker.
 * 
 * @author Balaaj A.
 *
 */
public class TradeRecord {
	
	private String TraderName;
	private String StrategyUsed;
	private String CryptoCoin;
	private String Action;
	private String Quantity;
	private String Price;
	private String Date;
	
	/**
	 * 
	 * @param name
	 * @param strat
	 * @param coin
	 * @param action
	 * @param quant
	 * @param price
	 * @param date
	 */
	public TradeRecord(String name, String strat, String coin, String action, String quant, String price, String date)
	{
		this.TraderName = name;
		this.StrategyUsed = strat;
		this.CryptoCoin =  coin;
		this.Action = action;
		this.Quantity = quant;
		this.Price = price;
		this.Date = date;
	}
	
	/**
	 * 
	 * @param name
	 * @param strat
	 * @param coin
	 * @param action
	 * @param quant
	 * @param price
	 */
	public TradeRecord(String name, String strat, String coin, String action, String quant, String price)
	{
		this.TraderName = name;
		this.StrategyUsed = strat;
		this.CryptoCoin =  coin;
		this.Action = action;
		this.Quantity = quant;
		this.Price = price;
		
		LocalDate date = LocalDate.now();
		String dateString = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		this.Date = dateString;

	}
	
	/*
	 * Returns the fields of the TradeRecord in a String array.
	 */
	public String[] AsArray()
	{
		return new String[] { this.TraderName, this.StrategyUsed, this.CryptoCoin, this.Action, this.Quantity, this.Price, this.Date };
	}
	
	/**
	 * String representation of the TradeRecord.
	 */
	public String toString()
	{
		return this.TraderName + " " + this.StrategyUsed + " " + this.CryptoCoin + " " + this.Action + " " + this.Quantity + " " + this.Price + " " + this.Date;
	}

}
