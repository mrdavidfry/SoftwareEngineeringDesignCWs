package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {

  private final CalculatorDisplay display = new CalculatorDisplay(new Controller());
  private final CalculatorModel model = new CalculatorModel(display);

  class Controller implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      Object source = actionEvent.getSource();
      if (source instanceof DigitButton) {
        model.pressedDigit(((DigitButton) source).getValue());
      } else if (source instanceof OpButton) {
        model.pressedOperator(((OpButton) source).getOp());
      }
    }
  }

  public static void main(String[] args) {
    new CalculatorApp();
  }
}
