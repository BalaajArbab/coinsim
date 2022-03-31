package coinSim.records;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TradeRecord {
	
	String TraderName;
	String StrategyUsed;
	String CryptoCoin;
	String Action;
	String Quantity;
	String Price;
	String Date;
	
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
	
	public String toString()
	{
		return this.TraderName + " " + this.StrategyUsed + " " + this.CryptoCoin + " " + this.Action + " " + this.Quantity + " " + this.Price + " " + this.Date;
	}

}
