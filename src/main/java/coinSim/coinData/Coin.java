package coinSim.coinData;

public class Coin {
	
	String Id;
	String Symbol;
	String Name;
	int Price;
	long MktCap;
	long Volume;
	
	public Coin(String id, String symbol, String name, int price, long mktCap, long volume)
	{
		this.Id = id;
		this.Symbol = symbol;
		this.Name = name;
		this.Price = price;
		this.MktCap = mktCap;
		this.Volume = volume;
	}
	
	public void UpdateCoin(int newPrice, long newMktCap, long newVolume)
	{
		this.Price = newPrice;
		this.MktCap = newMktCap;
		this.Volume = newVolume;
	}
	

}
