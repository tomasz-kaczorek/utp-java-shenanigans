#Generics

Create generic interfaces:

    interface Selector<T>
    {
	    boolean select(T value);
    }

    interface Mapper<T, R>
    {
    	R map(T value);
    }

and a generic class:

    class ListCreator<A>
    {
    	static <T> ListCreator<T> collectFrom(List<T> list)
    	ListCreator<A> when(Selector<A> selector)
    	<R> List<R> mapEvery(Mapper<A, R> mapper)
    }  

so that the following statement:

    collectFrom(list).when(selector).mapEvery(mapper)

would create a new list containing elements of *list* filtered by a *selector* and converted by a *mapper*.

Following test program (that can be modified only within comments starting with ```<--```):

    import java.util.*;
    
    public class Main {
        public Main() {
            List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
            System.out.println(test1(src1));

            List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
            System.out.println(test2(src2));
        }

        public List<Integer> test1(List<Integer> src) {
          Selector /*<-- Selector definition; no lambdas */
          Mapper   /*<-- Mapper definition; no lambdas */
          return   ListCreator.collectFrom(src).when(sel).mapEvery(map);
        }

        public List<Integer> test2(List<String> src) {
          Selector /*<-- Selector definition; no lambdas */
          Mapper   /*<-- Mapper definition; no lambdas */
          return   ListCreator.collectFrom(src).when(sel).mapEvery(map);
        }

        public static void main(String[] args) {
          new Main();
        }
    }

should print:

    [11, 17, 19]
    [14, 17]
    
assuming that:
- within method *test1* *Selector* accepts numbers lower than 10 and *Mapper* returns its argument increased by 10;
- within method *test2* *Selector* accepts strings longer than 3 characters and *Mapper* returns string length increased by 10. 
