package coinSim.viewers;

import javax.swing.table.DefaultTableModel;
import coinSim.session.*;
import coinSim.records.*;

/**
 * The viewer object for the view of records with RecordTable and DefaultTableModel as its model.
 * 
 * @author Balaaj A.
 *
 */
public class RecordViewer extends Viewer
{
	private DefaultTableModel dtm;
	private RecordTable recordTable;
	
	/**
	 * 
	 * @param dtm
	 * @param recordTable
	 */
	public RecordViewer(DefaultTableModel dtm, RecordTable recordTable)
	{
		this.dtm = dtm;
		this.recordTable = recordTable;
	}
	
	/**
	 * Updates the view of traders based on the current state of recordTable and dtm.
	 * 
	 */
	public void Notify()
	{
		dtm.setRowCount(0);
		
		for (TradeRecord record : recordTable.GetRecords())
		{
			dtm.addRow(record.AsArray());
		}
	}

}
