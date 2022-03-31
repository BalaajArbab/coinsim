/**
 * Trading Strategy parent class.
 * @author Arjav R.
 */

package coinSim.tradingStrategy;

import coinSim.coinData.CoinDB;
import coinSim.session.Trader;

public abstract class TradingStrategy {
	
	protected static CoinDB coinDB = CoinDB.GetInstance();
	
    public abstract boolean Enact(Trader trader);
}