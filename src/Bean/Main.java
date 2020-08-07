package Bean;

import java.beans.PropertyVetoException;

public class Main {
  public static void main(String[] args) {

    Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
    System.out.println(purch);

    // --- tu należy dodać odpowiedni kod
    purch.addPropertyChangeListener(e -> {
      if(e.getPropertyName().equals("data") || e.getPropertyName().equals("price"))
        System.out.println("Change value of: " + e.getPropertyName() + " from: " + e.getOldValue() + " to: " + e.getNewValue());});
    purch.addVetoableChangeListener(e -> {
      if(e.getPropertyName().equals("price") && (Double) e.getNewValue() < 1000.)
        throw new PropertyVetoException("Price change to: " + e.getNewValue() + " not allowed", e);});
    // ----------------------------------

    try {
      purch.setData("w promocji");
      purch.setPrice(2000.00);
      System.out.println(purch);

      purch.setPrice(500.00);

    } catch (PropertyVetoException exc) {
      System.out.println(exc.getMessage());
    }
    System.out.println(purch);

  }
}
