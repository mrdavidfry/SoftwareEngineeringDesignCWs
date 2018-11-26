package ic.doc;

import java.util.function.BinaryOperator;
import javax.swing.JButton;

public class OpButton extends JButton {

  private BinaryOperator<Integer> op;

  public OpButton(BinaryOperator<Integer> op, String sign) {
    super(sign);
    this.op = op;
  }

  public BinaryOperator<Integer> getOp() {
    return op;
  }
}
