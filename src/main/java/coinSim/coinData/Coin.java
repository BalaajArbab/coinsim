/**
 * Datatype representing a Cryptocoin.
 * @author Balaaj Arbab
 */

package coinSim.coinData;

public class Coin {
	
	String Id;
	String Symbol;
	
	double Price;
	double MktCap;
	double Volume;
	
	public Coin(String id, String symbol, double price, double mktCap, double volume)
	{
		this.Id = id;
		this.Symbol = symbol;
		this.Price = price;
		this.MktCap = mktCap;
		this.Volume = volume;
	}
	
	public void UpdateCoin(double newPrice, double newMktCap, double newVolume)
	{
		this.Price = newPrice;
		this.MktCap = newMktCap;
		this.Volume = newVolume;
	}
	
	public String toString()
	{
		return "ID: " + this.Id + ", Symbol: " + this.Symbol + ", Price: " + this.Price + ", MktCap: " + this.MktCap + ", Volume: " + this.Volume;
	}
	
	public double GetPrice()
	{
		return this.Price;
	}
	
	public double GetMktCap()
	{
		return this.MktCap;
	}
	
	public double GetVolume()
	{
		return this.Volume;
	}
	

}
