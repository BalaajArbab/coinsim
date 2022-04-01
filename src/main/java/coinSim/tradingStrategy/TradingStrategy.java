/**
 * Trading Strategy parent class.
 * @author Arjav R., Balaaj A.
 */

package coinSim.tradingStrategy;

import coinSim.coinData.CoinDB;
import coinSim.session.Trader;
import coinSim.records.*;

public abstract class TradingStrategy {
	
	protected static CoinDB coinDB = CoinDB.GetInstance();
	
    public abstract boolean Enact(Trader trader, RecordTable recordTable);
}