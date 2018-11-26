package ic.doc;

import java.util.Stack;
import java.util.function.BinaryOperator;

public class CalculatorModel {

  private final CalculatorDisplay display;

  private Stack<Integer> stack = new Stack<>();

  public CalculatorModel(CalculatorDisplay display) {
    this.display = display;
  }

  public void pressedDigit(int i) {
    stack.push(i);
    display.displayInt(i);
  }

  public void pressedOperator(BinaryOperator<Integer> op) {
    if (stack.size() >= 2) {
      Integer y = stack.pop();
      Integer x = stack.pop();
      Integer result = op.apply(x, y);
      stack.push(result);
      display.displayInt(result);
    } // else do nothing
  }
}

