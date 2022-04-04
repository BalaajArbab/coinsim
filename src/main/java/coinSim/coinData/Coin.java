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
	
	/**
     * Constructor of a cryptoocoin with relevant data.
     */
	public Coin(String id, String symbol, double price, double mktCap, double volume)
	{
		this.Id = id;
		this.Symbol = symbol;
		this.Price = price;
		this.MktCap = mktCap;
		this.Volume = volume;
	}
	
	/**
	 *  Updates a coin with given parameters.
	 *  
     * @param  newPrice
     * 		   self-explanatory
     *         
     * @param  newMktCap
     *         self-explanatory
     *         
     * @param newVolume
     * 		  self-explanatory
     * 
     */
	public void UpdateCoin(double newPrice, double newMktCap, double newVolume)
	{
		this.Price = newPrice;
		this.MktCap = newMktCap;
		this.Volume = newVolume;
	}
	
	/**
     * @return representation of a coin object.
     */
	public String toString()
	{
		return "ID: " + this.Id + ", Price: " + this.Price + ", MktCap: " + this.MktCap + ", Volume: " + this.Volume;
	}
	
	/**
     * @return returns the price of a coin.
     */
	public double GetPrice()
	{
		return this.Price;
	}
	
	/**
     * @return returns the mkt cap of a coin.
     */
	public double GetMktCap()
	{
		return this.MktCap;
	}
	
	/**
     * @return returns the volume of a coin.
     */
	public double GetVolume()
	{
		return this.Volume;
	}
	

}
