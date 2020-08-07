package Anagrams1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams
{
	HashMap<String, LinkedList<String>> words = new HashMap<>();

	Anagrams(String file) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(file));
		while (scanner.hasNext())
		{
			String value = scanner.next();
			String key = key(value);
			LinkedList<String> anagrams = words.get(key);
			if (anagrams == null) words.put(key, anagrams = new LinkedList<>());
			anagrams.add(value);
		}
		scanner.close();
	}

	List<List<String>> getSortedByAnQty()
	{
		List<List<String>> returned = new ArrayList<>(words.values());
		returned.sort((o1, o2) -> (o1.size() == o2.size() ? o1.get(0).compareTo(o2.get(0)) : o2.size() - o1.size()));
		return returned;
	}

	String getAnagramsFor(String s)
	{
		LinkedList<String> anagrams = words.get(key(s));
		if (anagrams != null)
		{
			for (Iterator<String> i = anagrams.iterator(); i.hasNext(); )
			{
				if (i.next().equals(s))
				{
					words.put(key(s), new LinkedList<>(anagrams));
					i.remove();
					return s + ": " + anagrams;
				}
			}
		}
		return s + ": null";
	}

	String key(String value)
	{
		char[] key = value.toCharArray();
		Arrays.sort(key);
		return new String(key);
	}
}
