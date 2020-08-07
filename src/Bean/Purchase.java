package Bean;

import java.beans.*;

public class Purchase
{
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

	String prod;
	String data;
	Double price;

	Purchase(String prod, String data, Double price)
	{
		this.prod = prod;
		this.data = data;
		this.price = price;
	}

	synchronized void setData(String newData)
	{
		String oldData = data;
		data = newData;
		propertyChangeSupport.firePropertyChange("data", oldData, newData);
	}

	synchronized void setPrice(Double newPrice) throws PropertyVetoException
	{
		Double oldPrice = price;
		vetoableChangeSupport.fireVetoableChange("price", oldPrice, newPrice);
		price = newPrice;
		propertyChangeSupport.firePropertyChange("price", oldPrice, newPrice);
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
	{
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
	{
		propertyChangeSupport.removePropertyChangeListener(listener);

	}

	public synchronized void addVetoableChangeListener(VetoableChangeListener listener)
	{
		vetoableChangeSupport.addVetoableChangeListener(listener);
	}

	public synchronized void removeVetoableChangeListener(VetoableChangeListener listener)
	{
		vetoableChangeSupport.removeVetoableChangeListener(listener);
	}

	public String toString()
	{
		return "Purchase [prod=" + prod + ", data=" + data + ", price=" + price + "]";
	}
}
