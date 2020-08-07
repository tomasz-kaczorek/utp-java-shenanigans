package Lambdas1;

import java.util.Arrays;
import java.util.List;

public class Main
{
	static List<String> getPricesInPLN(List<String> destinations, double xrate)
	{
		return ListCreator
			.collectFrom(destinations)
			.when((String s) -> s.startsWith("WAW"))
			.mapEvery((String s) ->
				{
					String[] tokens = s.split(" ");
					return String.format("to %s price in PLN:\t %.0f", tokens[1], Integer.parseInt(tokens[2]) * xrate);
				}
			);
	}

	public static void main(String[] args)
	{
		List<String> dest = Arrays.asList(
			"bleble bleble 2000",
			"WAW HAV 1200",
			"xxx yyy 789",
			"WAW DPS 2000",
			"WAW HKT 1000"
		);
		double ratePLNvsEUR = 4.30;
		List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
		for (String r : result) System.out.println(r);
	}
}
