
package coinSim.tradingStrategy;

import coinSim.coinData.CoinDB;
import coinSim.session.Trader;
import coinSim.records.*;

/**
 * The {@code TradingStrategy} parent class. Contains instantiation of database of coins.
 * 
 * @author  Arjav R.
 * @author  Balaaj A.
 * 
 */
public abstract class TradingStrategy {
	
    // instance of CoinDB - the database for coins supported by CoinSim
	protected static CoinDB coinDB = CoinDB.GetInstance();
	
    /**
     * Performs transactions of crypto coins based on established conditions.
     * 
     * @param  trader
     *         Trader object of the trader performing a trade using this strategy
     *         
     * @param  recordTable
     *         keeps record of all trades performed across traders
     */
    public abstract boolean Enact(Trader trader, RecordTable recordTable);
}