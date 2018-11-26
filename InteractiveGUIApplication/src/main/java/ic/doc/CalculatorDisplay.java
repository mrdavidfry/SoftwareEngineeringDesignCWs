package ic.doc;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalculatorDisplay {

  private static final int frameWidth = 160;
  private static final int frameHeight = 200;

  private final DigitButton[] digits = new DigitButton[9]; // stores the digit buttons
  private final OpButton plus;
  private final OpButton minus;

  private final JTextField resultDisplay = new JTextField(10);

  public CalculatorDisplay(ActionListener controller) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(frameWidth, frameHeight);
    JPanel panel = new JPanel();
    panel.add(resultDisplay);
    resultDisplay.setText("0");

    // Set up digit buttons:
    for (int i = 0; i < 9; i++) {
      DigitButton button = new DigitButton(i + 1);
      digits[i] = button;
      button.addActionListener(controller);
      panel.add(button);
    }

    // Set up operator buttons:
    plus = new OpButton(StandardOperators.sum, "+");
    minus = new OpButton(StandardOperators.diff, "-");
    panel.add(plus);
    panel.add(minus);
    plus.addActionListener(controller);
    minus.addActionListener(controller);

    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  public void displayInt(int i) {
    resultDisplay.setText(Integer.toString(i));
  }
}
