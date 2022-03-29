/** 
 * StrategyCreator Factory class. Takes in integer, returns corresponding 
 * TradingStrategy object.
 * @author Arjav R.
 */
package coinSim.tradingStrategy;

public class StrategyCreator {
    
    /**
     * 
     * @param strategyNumber which strategy is selected
     * @return returns TradingStrategy object based on input
     */
    public TradingStrategy createStrategy(int strategyNumber) {
        TradingStrategy strategy = null;
        
        if (strategyNumber == 1) {
            strategy = new TradingStrategy01();
        }
        else if (strategyNumber == 2) {
            strategy = new TradingStrategy02();
        }
        else if (strategyNumber == 3) {
            strategy = new TradingStrategy03();
        }
        else if (strategyNumber == 4) {
            strategy = new TradingStrategy04();
        }
        
        return strategy;
    }

}
