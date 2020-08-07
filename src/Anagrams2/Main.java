package Anagrams2;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                Map<String, List<String>> map = reader.lines().collect(Collectors.groupingBy(e -> {char[] array = e.toCharArray(); Arrays.sort(array); return new String(array);}));
                int size = map.entrySet().stream().max(Comparator.comparingInt(e -> e.getValue().size())).get().getValue().size();
                map.entrySet().stream().filter(e -> e.getValue().size() == size).forEach(e -> {for(String word : e.getValue()) {System.out.println(word + " " + e.getValue().stream().filter(w -> !w.equals(word)).collect(Collectors.joining(" ")));}});
        }
}