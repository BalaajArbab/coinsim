package coinSim.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import org.apache.commons.lang3.time.StopWatch;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;


import coinSim.session.*;
import coinSim.viewers.*;
import coinSim.coinData.*;
import coinSim.tradingStrategy.*;
import coinSim.records.*;

/**
 * Implements the main UI of the trading software.
 * 
 * @author Balaaj A.
 *
 */
public class OurUI extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	private String[] coinList = CoinDB.coinList;
	
	private static OurUI instance;
	
	/*
	 * Implements the Singleton design pattern to ensure only one main UI view exists.
	 */
	public static OurUI getInstance() {
		if (instance == null)
			instance = new OurUI();

		return instance;
	}
	
	private StopWatch sw;
	boolean firstTrade = true;
	
	private Ledger ledger;
	private RecordTable recordTable;
	
	private DefaultTableModel dtm;
	private JTable table;
	
	private DefaultTableModel recordTableModel;
	private JTable westTable;
	
	private TraderViewer traderViewer;
	
	private RecordViewer recordViewer;
	private HistogramViewer histViewer;
	
	private ArrayList<Viewer> outputViewers;
	
	
	private DefaultCategoryDataset dataset;
	
	private OurUI()
	{
		// Set window title
		super("CoinSim");
		
//		CoinFetcher.FetchFirstTime();
		CoinFetcher.InstantiateCoinObjects();
		
		this.ledger = new Ledger();
		this.recordTable = new RecordTable();
//		this.coinDB = CoinDB.GetInstance();
		
		sw = new StopWatch();
		sw.start();
		
		
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
		
		JButton createDefault = new JButton("Populate Ledger With Traders");
		createDefault.setActionCommand("createDefault");
		createDefault.addActionListener(this);
		
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
		
		JPanel ledgerButton = new JPanel();
		ledgerButton.setLayout(new BoxLayout(ledgerButton, BoxLayout.X_AXIS));
		ledgerButton.add(createDefault);
		east.add(ledgerButton);
		
		
		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("performTrade");
		trade.addActionListener(this);


		
		recordTableModel = new DefaultTableModel(new Object[] {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"}, 0)
		{
		    public boolean isCellEditable(int row, int column) 
		    {
		        return false;
		    }
		};
		
		this.recordViewer = new RecordViewer(this.recordTableModel, this.recordTable);
		
		
		westTable = new JTable(recordTableModel);
		
		JScrollPane westScrollPane = new JScrollPane(westTable);
		westScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Records and Charts",
				TitledBorder.CENTER, TitledBorder.TOP));
		
		
		JPanel west = new JPanel();
		
		westScrollPane.setPreferredSize(new Dimension(800, 300));
		westTable.setFillsViewportHeight(true);

		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		west.add(westScrollPane);
		
		ChartPanel chartPanel = createChartPanel();
		
		this.histViewer = new HistogramViewer(this.dataset, this.ledger);
		
		west.add(chartPanel);
		
		
		JPanel south = new JPanel();
		
		south.add(trade);
			
		
		JPanel north = new JPanel();
		
		JTextArea tickers = new JTextArea("Available Tickers:\n" + String.join(", ", this.coinList));
		tickers.setBackground(Color.YELLOW);
		
		
		north.add(tickers);
		//north.setBackground(Color.yellow);
		
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(north, BorderLayout.NORTH);
		
		
		outputViewers = new ArrayList<Viewer>();
		outputViewers.add(histViewer);
		outputViewers.add(recordViewer);
	}
	
	
	/**
	 * Runs the UI element.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		JFrame frame = OurUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	/**
	 * Event listener for button clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("performTrade".equals(command)) 
		{
			long timePassed = this.sw.getTime() / 1000;
			
			int waitTime = 30;
			
			
			if (!this.firstTrade && !(timePassed >= waitTime))
			{
				JOptionPane.showMessageDialog(this, "Please wait " + (waitTime - timePassed) + " more seconds before trying to perform a trade operation.");
				return;
			}
			this.firstTrade = false;
			
			boolean atleastOneTradePerformed = TradingStratFacade.PerformTrade(ledger, recordTable);
			sw.reset();
			sw.start();
			
			this.traderViewer.Notify(); // To fix trade strategy selected in view, if confirm button was not pressed.
			
			// If at least one trade has been performed, notify output viewers.
			if (atleastOneTradePerformed)
			{
				// traderViewer not included in this as it is of a different nature (not output).
				for (Viewer viewer : this.outputViewers)
				{
					viewer.Notify();
				}
			}
			
		} 
		else if ("addTrader".equals(command)) 
		{
			addTrader();
			
		} 
		else if ("removeTrader".equals(command)) 
		{
			removeTrader();
			
		}
		else if ("addCoin".equals(command))
		{
			addCoin();
			
		}
		else if ("removeCoin".equals(command))
		{
			removeCoin();
			
		}
		else if ("confirmStrat".equals(command))
		{
			confirmStrat();	
			
		}
		else if ("createDefault".equals(command)) 
		{
			createDefaultLedger();
			
		}
	}
	
	private void addTrader()
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
	
	private void removeTrader()
	{
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1)
		{
			if (this.ledger.RemoveTrader(selectedRow))
			{
				this.traderViewer.Notify();
			}
		}
	}
	
	private void addCoin()
	{
		String coins = JOptionPane.showInputDialog("Id of coin to associate with Trader? (or list of coins separated by space)");
		
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
	
	private void removeCoin()
	{
		String coinName = JOptionPane.showInputDialog("Id of coin to remove from Trader?");
		
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1)
		{
			Trader currentTrader = this.ledger.GetTraderAtIndex(selectedRow);
			currentTrader.RemoveCoinOfInterest(coinName);
			
			this.traderViewer.Notify();
		}
	}
	
	private void confirmStrat()
	{
		ArrayList<Integer> strategies = getStrategyList();
		
		this.ledger.SetStrategies(strategies);
		
		JOptionPane.showMessageDialog(this, "Strategies locked in!");
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
	
	private ChartPanel createChartPanel() {
			
			this.dataset = new DefaultCategoryDataset();
	
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
	
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Strategy");
			plot.setDomainAxis(domainAxis);
			LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
			rangeAxis.setRange(new Range(0.1, 20.0));
			plot.setRangeAxis(rangeAxis);
			
	
	
			JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
					true);
	
			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(600, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			chartPanel.setBackground(Color.white);
			
			return chartPanel;

		}
	
	private void createDefaultLedger()
	{
		this.ledger.CreateDefaultLedger();
		
		
		traderViewer.Notify();
	}

}
