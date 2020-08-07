package Lambdas2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{

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
		List<String> result = dest.stream()
			.filter(s -> s.startsWith("WAW"))
			.map((String s) ->
			{
				String[] tokens = s.split(" ");
				return String.format("to %s price in PLN:\t %.0f", tokens[1], Integer.parseInt(tokens[2]) * ratePLNvsEUR);
			})
			.collect(Collectors.toList());
		for (String r : result) System.out.println(r);
	}
}
