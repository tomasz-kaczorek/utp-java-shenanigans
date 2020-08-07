package SortingWithLambdas1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind
{
	List<Purchase> purchases;

	CustomersPurchaseSortFind()
	{
		purchases = new ArrayList<>();
	}

	void readFile(String filename)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			for (String line = reader.readLine(); line != null; line = reader.readLine())
			{
				purchases.add(new Purchase(line));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	void showSortedBy(String field)
	{
		if (field.equals("Nazwiska"))
		{
			System.out.println("Nazwiska\n"
				+ purchases.stream()
				.sorted((o1, o2) -> o1.name.compareTo(o2.name) == 0 ? o1.id.compareTo(o2.id) : o1.name.compareTo(o2.name))
				.map(Object::toString)
				.collect(Collectors.joining("\n")
				)
				+ "\n"
			);
		}
		else if (field.equals("Koszty"))
		{
			System.out.println("Koszty\n"
				+ purchases
				.stream()
				.sorted((o1, o2) -> o1.total < o2.total ? 1 : o1.total > o2.total ? -1 : o1.id.compareTo(o2.id))
				.map(e -> e.toString() + " (koszt: " + e.total + ")")
				.collect(Collectors.joining("\n")
				)
				+ "\n"
			);
		}
	}

	void showPurchaseFor(String id)
	{
		System.out.println("Klient" + id + "\n"
			+ purchases.stream()
			.filter(e -> e.id.equals(id))
			.map(Object::toString)
			.collect(Collectors.joining("\n")
			)
			+ "\n"
		);
	}
}
