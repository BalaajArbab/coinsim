package coinSim.viewers;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import coinSim.session.*;
import java.util.HashSet;

public class TraderViewer extends Viewer {
	
	private DefaultTableModel dtm;
	private Ledger ledger;
	
	public TraderViewer(DefaultTableModel dtm, Ledger ledger)
	{
		this.dtm = dtm;
		this.ledger = ledger;
	}
	
	public void Notify()
	{
		dtm.setRowCount(0);
		
		for (Trader trader : ledger.GetTraders())
		{
			String[] str = new String[3];
			
			str[0] = trader.GetName();
			
			str[1] = "";
			
			for (String coinID : trader.GetCoinsOfInterest())
			{
				str[1] += coinID + " ";
			}
			
			switch (trader.GetTradeStrategy())
			{
				case -1:
					str[2] = "None";
					break;
			}
			
			dtm.addRow(str);			
		}
	}

}
