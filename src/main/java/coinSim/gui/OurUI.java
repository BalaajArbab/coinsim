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

public class OurUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static OurUI instance;
	
	public static OurUI getInstance() {
		if (instance == null)
			instance = new OurUI();

		return instance;
	}
	
	private Ledger ledger;
	
	private DefaultTableModel dtm;
	private JTable table;
	
	private OurUI()
	{
		// Set window title
		super("CoinSim");
		
		this.ledger = new Ledger();
		
		dtm = new DefaultTableModel(new Object[] { "Trader", "Coin List", "Strategy Name" }, 0)
		{
		    public boolean isCellEditable(int row, int column) 
		    {
		    	if (column == 2) return true;
		        return false;
		    }
		};
		
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
		
		System.out.println("test");
		
		
		
		
		getContentPane().add(east, BorderLayout.EAST);
		
		
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
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
	        }
		} else if ("addTrader".equals(command)) 
		{
			String name = JOptionPane.showInputDialog("Name of new Trader?");
			
			if (ledger.AddTrader(name))
			{
				String[] str = new String[3];
				
				str[0] = name;
				str[2] = "None";
				
				dtm.addRow(str);
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "Trader with that name already exists");
			}
			
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}

}
