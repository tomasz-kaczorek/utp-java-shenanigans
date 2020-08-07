#Maybe

Create a class:

    class Maybe<T>
    {
        Maybe(T x)                              //creates a new Maybe wrapper around object passed as argument
        static <T> Maybe<T> of(T x)             //creates a new Maybe wrapper around object passed as argument
        void ifPresent(Consumer cons)           //passes this Maybe to Consumer if it's not empty, otherwise does nothing
        <R> Maybe<R> map(Function<T, R> func)   //passes this Maybe to Function and wraps return value in a new Maybe
        T get() throws NoSuchElementException   //returns a wrapped object, if this Maybe is empty throws NoSuchElementException
        boolean isPresent()                     //returns if this Maybe has a value assigned
        T orElse(T defVal)                      //returns wrapped object or a default value if this Maybe is empty
        Maybe<T> filter(Predicate<T> pred)      //returns this Maybe if predicate is true, empty Maybe otherwise
    }
    
that can store a value or be empty. All method should work properly in either case.
 
Following test program:

        public class Main {

          public static void test() {
            String s = "aaa";    
            Maybe<String> m1 = Maybe.of(s);
            System.out.println(m1);
            s = null;
            Maybe<String> m2 = Maybe.of(s);
            System.out.println(m2);

            Integer num = null;
            Maybe<Integer> m4 = Maybe.of(num);
            if (num != null) System.out.println(num);
            m4.ifPresent(n -> System.out.println(n));
            m4.ifPresent(System.out::println);

            Maybe<Integer> m5 = Maybe.of(10);
            m5.ifPresent(System.out::println);

            Maybe<Integer> m6 = m5.map( n -> n +10 ); 
            System.out.println(m6);

            System.out.println(m6.get());
            try {
              System.out.println(m4.get());
            } catch(Exception exc) {
              System.out.println(exc);
            }

            String snum = null;
            if (num != null) snum = "Value: " + num;
            if (snum != null) System.out.println(snum);
            else System.out.println("Value is inaccessible");

            String res = Maybe.of(num).map(n -> "Value: "+n)
                              .orElse("Value is inaccessible");
            System.out.println(res);

            String txt = "Pies";
            String msg = "";

            if (txt != null && txt.length() > 0) {
              msg = txt;
            } else {
              msg = "Txt is null or empty";
            }

            msg = Maybe.of(txt)
                       .filter(t -> t.length() > 0)
                       .orElse("Txt is null or empty"); 
            System.out.println(msg);
          }

          public static void main(String[] args) {
            test();
          }
        }
should print:

    Maybe has value aaa
    Maybe is empty
    10
    Maybe has value 20
    20
    java.util.NoSuchElementException:  maybe is empty
    Value is inaccessible
    Value is inaccessible
    Pies
