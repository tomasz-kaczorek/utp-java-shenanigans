#Variadic Function

Create a class:

    class InputConverter<T>
    {
        InputConverter(T first)
        <R> R convertBy(Function... f)
    }
  
so that all functions passed to *ConvertBy* are applied to the argument of *InputConverter* constructor.

Given the following functions:
		
    Function<String, List<String>> flines(String)       //returns content of a specified file as a List of lines
    Function<List<String>, String> join(List<String>)   //returns a single String containing all elemens of a given List
    Function<String, List<Integer>> collectInts(String) //returns a List of all Integers found in a given String
    Function<List<Integer>, Integer> sum(List<Integer>) //returns a sum of all elements in the List

the sum of all integers in a file fname can be obtained by:

    Integer s = new InputConverter<String>(fname).convertBy(flines, join, collectInts, sum);

and the list of all integers from the String txt can be obtained by:

    List<Integer> n = new InputConverter<String>(txt).convertBy(collectInts);.


Following test program (that can be modified only within comments starting with ```<--```):

    public static void main(String[] args) {
      /*<-- definitions of flines, join, collectInts and sum as lambda expressions */
  
      String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
      InputConverter<String> fileConv = new InputConverter<>(fname);
      List<String> lines = fileConv.convertBy(flines);
      String text = fileConv.convertBy(flines, join);
      List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
      Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);
  
      System.out.println(lines);
      System.out.println(text);
      System.out.println(ints);
      System.out.println(sumints);
  
      List<String> arglist = Arrays.asList(args);
      InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
      sumints = slistConv.convertBy(join, collectInts, sum);
      System.out.println(sumints);
  
    }
should print:

    [Cars:, - Fiat: 15, Ford: 20, - Opel: 8, Mitsubishi: 10]  
    Cars:- Fiat: 15, Ford: 20- Opel: 8, Mitsubishi: 10  
    [15, 20, 8, 10]  
    53  
    600

when launched with parameters:

    Warszawa 100 Kielce 200 Szczecin 300

for LamComFile.txt file containing:

    Cars:  
    - Fiat: 15, Ford: 20  
    - Opel: 8, Mitsubishi: 10

    
