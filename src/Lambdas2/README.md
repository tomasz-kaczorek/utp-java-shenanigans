#Lambdas 2

List *dest* contains flight data in the following form: *origin* *destination* *price_in_EUR*.

Create an output list containing data about all flights from *WAW* with prices converted to PLN in the following format: to *destination* - price in PLN: converted *price_in_PLN*.

Following test program (that can be modified only within comments starting with ```<--```):

    public class Main {
    
      public static void main(String[] args) {
        List<String> dest = Arrays.asList(
          "bleble bleble 2000",
          "WAW HAV 1200",
          "xxx yyy 789",
          "WAW DPS 2000",
          "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = /*<-- lambda expression */
    
        for (String r : result) System.out.println(r);
      }
    }
should print:

    to HAV - price in PLN:	5160  
    to DPS - price in PLN:	8600  
    to HKT - price in PLN:	4300
