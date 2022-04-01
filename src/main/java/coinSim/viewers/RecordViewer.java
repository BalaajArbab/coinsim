package coinSim.viewers;

import javax.swing.table.DefaultTableModel;
import coinSim.session.*;
import coinSim.records.*;

public class RecordViewer extends Viewer
{
	private DefaultTableModel dtm;
	private RecordTable recordTable;
	
	public RecordViewer(DefaultTableModel dtm, RecordTable recordTable)
	{
		this.dtm = dtm;
		this.recordTable = recordTable;
	}
	
	public void Notify()
	{
		dtm.setRowCount(0);
		
		for (TradeRecord record : recordTable.GetRecords())
		{
			dtm.addRow(record.AsArray());
		}
	}

}
