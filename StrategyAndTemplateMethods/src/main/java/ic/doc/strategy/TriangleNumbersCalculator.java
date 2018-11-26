package ic.doc.strategy;

public class TriangleNumbersCalculator implements TermCalculator {

  public Integer term(int i) {
    if (i < 0) {
      throw new IllegalArgumentException("Not defined for indices < 0");
    }
    if (i == 0) {
      return 1;
    }
    return term(i - 1) + i + 1;
  }
}
