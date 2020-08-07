package SortingWithLambdas2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang
{
	Map<String, Set<String>> languages = new LinkedHashMap();
	Map<String, Set<String>> programmers = new LinkedHashMap();

	ProgLang(String nazwaPliku) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(nazwaPliku));
		for (String line = reader.readLine(); line != null; line = reader.readLine())
		{
			String[] tokens = line.split("\t");
			for (int i = 1; i < tokens.length; ++i)
			{
				addProgLang(tokens[i], tokens[0]);
			}
		}
	}

	Map<String, Collection<String>> getLangsMap()
	{
		return new LinkedHashMap<>(languages);
	}

	Map<String, Collection<String>> getProgsMap()
	{
		return new LinkedHashMap<>(programmers);
	}

	Map<String, Set<String>> getLangsMapSortedByNumOfProgs()
	{
		return sorted(languages, (o1, o2) ->
			o1.getValue().size() == o2.getValue().size() ?
				o1.getKey().compareTo(o2.getKey()) :
				o2.getValue().size() - o1.getValue().size());
	}

	Map<String, Set<String>> getProgsMapSortedByNumOfLangs()
	{
		return sorted(programmers, (o1, o2) ->
			o1.getValue().size() == o2.getValue().size() ?
				o1.getKey().compareTo(o2.getKey()) :
				o2.getValue().size() - o1.getValue().size());
	}

	Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n)
	{
		return filtered(programmers, e -> e.getValue().size() > n);
	}

	<K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator)
	{
		return map.entrySet().stream().sorted(comparator).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> null, LinkedHashMap::new));
	}

	<K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate)
	{
		return map.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> null, LinkedHashMap::new));
	}

	void addProgLang(String prog, String lang)
	{
		Set<String> programmer = programmers.get(prog);
		if (programmer == null) programmers.put(prog, programmer = new LinkedHashSet<>());
		programmer.add(lang);
		Set<String> language = languages.get(lang);
		if (language == null) languages.put(lang, language = new LinkedHashSet<>());
		language.add(prog);
	}
}
