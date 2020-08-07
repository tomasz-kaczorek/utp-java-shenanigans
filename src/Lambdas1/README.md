#Lambdas 1

List *dest* contains flight data in the following form: *origin* *destination* *price_in_EUR*.

Create an output list containing data about all flights from *WAW* with prices converted to PLN in the following format: to *destination* - price in PLN: *price_in_PLN*.

Create a class:

    class ListCreator<A>
    {
        static <T> ListCreator<T> collectFrom(List<T> list)
        ListCreator<A> when(Predicate<A> predicate)
        <R> List<R> mapEvery(Function<A, R> function)
    }  

so that the following statement:

    collectFrom(list).when(lambda1).mapEvery(lambda2)

would create a new list containing elements of *list* filtered by a *lambda1* and converted by a *lambda2*.

Following test program (that can be modified only within comments starting with ```<--```):

    import java.util.*;

    public class Main {

      static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                           .when(  /*<-- lambda expression */
                            )
                           .mapEvery( /*<-- lambda expression */
                            );
      }

      public static void main(String[] args) {
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
should print:

    to HAV - price in PLN:	5160
    to DPS - price in PLN:	8600
    to HKT - price in PLN:	4300
