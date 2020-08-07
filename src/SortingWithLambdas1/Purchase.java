package SortingWithLambdas1;

public class Purchase
{
	String id;
	String name;
	String article;
	double price;
	double quantity;
	double total;

	Purchase(String text)
	{
		String[] fields = text.split(";");
		id = fields[0];
		name = fields[1];
		article = fields[2];
		price = Double.parseDouble(fields[3]);
		quantity = Double.parseDouble(fields[4]);
		total = price * quantity;
	}

	public String toString()
	{
		return id + ";" + name + ";" + article + ";" + price + ";" + quantity;
	}
}
