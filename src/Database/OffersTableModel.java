package Database;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class OffersTableModel implements TableModel
{
	Locale locale;
	SimpleDateFormat dateFormat;
	ArrayList<Offer> offers;
	LinkedList<TableModelListener> listeners = new LinkedList<>();

	OffersTableModel(Locale locale, SimpleDateFormat dateFormat, ArrayList<Offer> offers)
	{
		this.locale = locale;
		this.dateFormat = dateFormat;
		this.offers = offers;
	}

	void setLocale(Locale locale)
	{
		this.locale = locale;
		for(TableModelListener listener : listeners) listener.tableChanged(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
	}

	void setDateFormat(SimpleDateFormat dateFormat)
	{
		this.dateFormat = dateFormat;
		for(TableModelListener listener : listeners) listener.tableChanged(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
	}

	@Override
	public int getRowCount()
	{
		return offers.size();
	}

	@Override
	public int getColumnCount()
	{
		return 6;
	}

	@Override
	public String getColumnName(int i)
	{
		final String[] headers = {"country", "beginning", "ending", "location", "price", "currency"};
		return ResourceBundle.getBundle("Database.HeadersBundle", locale).getString(headers[i]);
	}

	@Override
	public Class<?> getColumnClass(int i)
	{
		return String.class;
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		Offer offer = offers.get(row);
		switch(column)
		{
			case 0: return offer.getCountry(locale);
			case 1: return offer.getBeginning(dateFormat);
			case 2: return offer.getEnding(dateFormat);
			case 3: return offer.getLocation(locale);
			case 4: return offer.getPrice(locale);
			case 5: return offer.getCurrency();
		}
		return null;
	}

	@Override
	public void setValueAt(Object o, int i, int i1)
	{
	}

	@Override
	public void addTableModelListener(TableModelListener tableModelListener)
	{
		listeners.add(tableModelListener);
	}

	@Override
	public void removeTableModelListener(TableModelListener tableModelListener)
	{
		listeners.remove(tableModelListener);
	}
}
