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
			
			int count = 0;
			for (String coinID : trader.GetCoinsOfInterest())
			{
				if (count != trader.GetCoinsOfInterest().size() - 1)
				str[1] += coinID + ", ";
				else str[1] += coinID + "";
				
				count++;
			}
			
			switch (trader.GetTradeStrategy())
			{
				case -1:
					str[2] = "None";
					break;
				case 1:
					str[2] = "Strategy-01";
					break;
				case 2:
					str[2] = "Strategy-02";
					break;
				case 3:
					str[2] = "Strategy-03";
					break;
				case 4:
					str[2] = "Strategy-04";
					break;
			}
			
			dtm.addRow(str);			
		}
	}

}
