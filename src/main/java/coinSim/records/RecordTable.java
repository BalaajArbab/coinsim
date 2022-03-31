package coinSim.records;

import java.util.ArrayList;

public class RecordTable {
	
	ArrayList<TradeRecord> Records;
	
	public RecordTable()
	{
		this.Records = new ArrayList<TradeRecord>();
	}
	
	public void InsertRecord(TradeRecord record)
	{
		this.Records.add(record);
	}

}
