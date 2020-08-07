#Anagrams 1

The file {user.home}/allwords.txt contains a list of whitespace separated words.

Create a class:

    class Anagrams
    {
        List<List<String>> getSortedByAnQty()   //returns all anagram groups sorted by number of elements in descending order, if two groups have the same size alphabetical order of first elements is used
        String getAnagramsFor(String s)         //returns a list of anagrams for a word s in the format "s: [anagram1, anagram2...]" or null if a file does not contain such word
    }

Following test program:

    import java.io.*;
    import java.util.*;

    public class Main {

      public static void main(String[] args) throws FileNotFoundException {
        String home = System.getProperty("user.home");
        String allWords = home + "/allwords.txt";
        Anagrams an = new Anagrams(allWords);
        for(List<String> wlist : an.getSortedByAnQty()) {
          System.out.println(wlist);
        }
        System.out.println("************************");
        Scanner scan = new Scanner(new File(home, "wordsToFind.txt"));
        while(scan.hasNext()) {
          System.out.println(an.getAnagramsFor(scan.next()));
        }
        scan.close();
      }

    }
should print:

    [evil, levi, live, veil, vile]
    [andes, danes, deans, sedan]
    [gals, lags, slag]
    [streets, testers]
    [uprising]
    ************************
    evil: [levi, live, veil, vile]
    streets: [testers]
    uprising: []

for allwords.txt file containing:

    andes danes deans evil gals lags levi live sedan
    slag streets testers uprising veil vile

and wordsToFind file containing:

    evil streets uprising
