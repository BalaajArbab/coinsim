package coinSim.viewers;

import org.jfree.data.category.DefaultCategoryDataset;

import coinSim.session.*;

public class HistogramViewer extends Viewer{
	
	private Ledger ledger;
	private DefaultCategoryDataset dataset;
	
	public HistogramViewer(DefaultCategoryDataset dataset, Ledger ledger)
	{
		this.dataset = dataset;
		this.ledger = ledger;
	}
	
	public void Notify()
	{
		this.dataset.clear();
		
		for (Trader trader : ledger.GetTraders())
		{
			int[] useCounts = trader.GetStratUseCounts();
			
			for (int i = 0; i < useCounts.length; i++)
			{
				if (useCounts[i] > 0)
				{
					switch(i)
					{
						case 0:
							dataset.setValue(useCounts[i], trader.GetName(), "Strategy-01");
							break;
						case 1:
							dataset.setValue(useCounts[i], trader.GetName(), "Strategy-02");
							break;
						case 2:
							dataset.setValue(useCounts[i], trader.GetName(), "Strategy-03");
							break;
						case 3:
							dataset.setValue(useCounts[i], trader.GetName(), "Strategy-04");
							break;
					}
				}
			}
		}
	}

}
