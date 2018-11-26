package ic.doc;

import java.util.function.BinaryOperator;

public class StandardOperators {

  // Stores all of the standard Binary Operators.

  public static final BinaryOperator<Integer> sum = (x, y) -> x + y;
  public static final BinaryOperator<Integer> diff = (x, y) -> x - y;
}
