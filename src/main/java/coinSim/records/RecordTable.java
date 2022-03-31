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
	
	public void InsertRecords(ArrayList<TradeRecord> records)
	{
		for (TradeRecord record : records)
		{
			this.Records.add(record);
		}
	}
	
	public void PrintRecords()
	{
		for (TradeRecord record : this.Records)
		{
			System.out.println(record);
		}
	}

}
