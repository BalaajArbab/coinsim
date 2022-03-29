/**
 * Trading Strategy parent class.
 * @author Arjav R.
 */

package coinSim.tradingStrategy;

import coinSim.session.Trader;

public abstract class TradingStrategy {
    public abstract void Enact(Trader trader);
}