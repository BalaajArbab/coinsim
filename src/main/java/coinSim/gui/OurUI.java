package coinSim.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import coinSim.utils.DataVisualizationCreator;

import coinSim.session.*;
import coinSim.viewers.*;
import coinSim.coinData.*;
import coinSim.tradingStrategy.*;
import coinSim.records.*;

public class OurUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] coinList = new String[] {"bitcoin", "ethereum", "dogecoin"};
	
	private static OurUI instance;
	
	public static OurUI getInstance() {
		if (instance == null)
			instance = new OurUI();

		return instance;
	}
	
	private Ledger ledger;
	private CoinDB coinDB;
	private RecordTable recordTable;
	
	private DefaultTableModel dtm;
	private JTable table;
	
	private TraderViewer traderViewer;
	
	private OurUI()
	{
		// Set window title
		super("CoinSim");
		
		CoinFetcher.FetchFirstTime(new ArrayList<String>(Arrays.asList(coinList)));
		
		this.ledger = new Ledger();
		this.recordTable = new RecordTable();
		this.coinDB = CoinDB.GetInstance();
		
		coinDB.PrintCoins();
		
		
		dtm = new DefaultTableModel(new Object[] { "Trader", "Coin List", "Strategy Name" }, 0)
		{
		    public boolean isCellEditable(int row, int column) 
		    {
		    	if (column == 2) return true;
		        return false;
		    }
		};
		
		this.traderViewer = new TraderViewer(this.dtm, this.ledger);
		table = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-01");
		strategyNames.add("Strategy-02");
		strategyNames.add("Strategy-03");
		strategyNames.add("Strategy-04");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		JButton addTrader = new JButton("Add Trader");
		JButton removeTrader = new JButton("Remove Trader");
		addTrader.setActionCommand("addTrader");
		addTrader.addActionListener(this);
		removeTrader.setActionCommand("removeTrader");
		removeTrader.addActionListener(this);
		
		JButton addCoin = new JButton("Add Coin");
		JButton removeCoin = new JButton("Remove Coin");
		addCoin.setActionCommand("addCoin");
		addCoin.addActionListener(this);
		removeCoin.setActionCommand("removeCoin");
		removeCoin.addActionListener(this);
		
		JButton confirmStrategySelection = new JButton("Confirm Strategy Selection");
		confirmStrategySelection.setActionCommand("confirmStrat");
		confirmStrategySelection.addActionListener(this);
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		
		JPanel east = new JPanel();
//		east.setLayout();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addTrader);
		buttons.add(removeTrader);
		east.add(buttons);
		
		JPanel coinButtons = new JPanel();
		coinButtons.setLayout(new BoxLayout(coinButtons, BoxLayout.X_AXIS));
		coinButtons.add(addCoin);
		coinButtons.add(removeCoin);
		east.add(coinButtons);
		
		JPanel stratButtons = new JPanel();
		stratButtons.setLayout(new BoxLayout(stratButtons, BoxLayout.X_AXIS));
		stratButtons.add(confirmStrategySelection);
		east.add(stratButtons);
		
		
		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("performTrade");
		trade.addActionListener(this);


		JPanel south = new JPanel();
		
		south.add(trade);
		
		System.out.println("test");
		
		
		
		
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		
		
	}
	
	

	public static void main(String[] args) 
	{
		
		JFrame frame = OurUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("performTrade".equals(command)) 
		{
//			for (int count = 0; count < dtm.getRowCount(); count++)
//			{
//					Object traderObject = dtm.getValueAt(count, 0);
//					if (traderObject == null) {
//						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
//						return;
//					}
//					String traderName = traderObject.toString();
//					Object coinObject = dtm.getValueAt(count, 1);
//					if (coinObject == null) {
//						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
//						return;
//					}
//					String[] coinNames = coinObject.toString().split(",");
//					Object strategyObject = dtm.getValueAt(count, 2);
//					if (strategyObject == null) {
//						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
//						return;
//					}
//					String strategyName = strategyObject.toString();
//					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
//	        }
			
			CoinFetcher.Fetch(new ArrayList<String>(Arrays.asList(coinList)));
			
			//coinDB.PrintCoins();
			
			for (Trader trader : this.ledger.GetTraders())
			{
				TradingStrategy strat = StrategyCreator.CreateStrategy(trader.GetTradeStrategy());
				
				boolean atleastOneTradePerformed = false;
				
				if (strat != null)
				{					
					boolean tradePerformed = strat.Enact(trader, recordTable);
					
					if (!tradePerformed)
					{
						System.out.println("Trade not performed for " + trader.GetName() + " due to mismatch of coins of interest");
					}
					else atleastOneTradePerformed = true;
				}
			}
			
			this.traderViewer.Notify(); // To fix trade strategy selected in view, if confirm button was not pressed.
			recordTable.PrintRecords();
			
		} 
		else if ("addTrader".equals(command)) 
		{
			String name = JOptionPane.showInputDialog("Name of new Trader?");
			
			if (name.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Pls enter some text for name");
			}
			else if (ledger.AddTrader(name))
			{			
				this.traderViewer.Notify();
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "Trader with that name already exists");
			}
			
		} 
		else if ("removeTrader".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
			{
				if (this.ledger.RemoveTrader(selectedRow))
				{
					this.traderViewer.Notify();
				}
			}
			
		}
		else if ("addCoin".equals(command))
		{
			String coins = JOptionPane.showInputDialog("Id/Symbol of coin to associate with Trader? (or list of coins separated by space)");
			
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
			{
				Trader currentTrader = this.ledger.GetTraderAtIndex(selectedRow);
				
				String[] coinNames = coins.split(" ");
				
				for (String coinName : coinNames)
				{
					currentTrader.AddCoinOfInterest(coinName);
				}
				
				
				
				this.traderViewer.Notify();
			}
			
		}
		else if ("removeCoin".equals(command))
		{
			String coinName = JOptionPane.showInputDialog("Id/Symbol of coin to remove from Trader?");
			
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
			{
				Trader currentTrader = this.ledger.GetTraderAtIndex(selectedRow);
				currentTrader.RemoveCoinOfInterest(coinName);
				
				this.traderViewer.Notify();
			}
		}
		else if ("confirmStrat".equals(command))
		{
			ArrayList<Integer> strategies = getStrategyList();
			
			this.ledger.SetStrategies(strategies);
			
		}
	}
	
	private ArrayList<Integer> getStrategyList()
	{
		ArrayList<Integer> strategies = new ArrayList<Integer>();
		
		int rows = dtm.getRowCount();
		
		for (int i = 0; i < rows; i++)
		{
			String value = (String)dtm.getValueAt(i, 2);
			
			switch(value)
			{
				case "None": 
					strategies.add(-1);
					break;
				case "Strategy-01": 
					strategies.add(1);
					break;
				case "Strategy-02": 
					strategies.add(2);
					break;
				case "Strategy-03": 
					strategies.add(3);
					break;
				case "Strategy-04": 
					strategies.add(4);
					break;
			}
		}
		
		return strategies;
				
	}

}
