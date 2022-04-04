package coinSim.records;

import java.util.ArrayList;

/**
 * A class representing a table of TradeRecord objects.
 * 
 * @author Balaaj A.
 *
 */

public class RecordTable {
	
	
	ArrayList<TradeRecord> Records;
	
	/**
	 * 
	 */
	public RecordTable()
	{
		this.Records = new ArrayList<TradeRecord>();
	}
	
	/**
	 * Inserts a record into the table.
	 * @param record
	 */
	public void InsertRecord(TradeRecord record)
	{
		this.Records.add(record);
	}
	
	/**
	 * Inserts a list of records into the table.
	 * @param records
	 */
	public void InsertRecords(ArrayList<TradeRecord> records)
	{
		for (TradeRecord record : records)
		{
			this.Records.add(record);
		}
	}
	
	/**
	 * Prints the records held in the table.
	 */
	public void PrintRecords()
	{
		for (TradeRecord record : this.Records)
		{
			System.out.println(record);
		}
	}
	
	/**
	 * Returns the records held in the table.
	 * @return a list of TradeRecord objects.
	 */
	public ArrayList<TradeRecord> GetRecords()
	{
		return this.Records;
	}

}
