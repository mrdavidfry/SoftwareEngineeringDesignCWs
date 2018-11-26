package ic.doc;

import javax.swing.JButton;

public class DigitButton extends JButton {
  private int value;

  public DigitButton(int value) {
    super(Integer.toString(value));
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
