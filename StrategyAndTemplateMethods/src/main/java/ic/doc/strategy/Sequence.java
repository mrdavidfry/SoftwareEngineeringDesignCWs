package ic.doc.strategy;

import java.util.Iterator;

public class Sequence implements Iterable<Integer> {

  private TermCalculator calculator;

  public Sequence(TermCalculator calculator) {
    this.calculator = calculator;
  }

  public int getTerm(int i) {
    return calculator.term(i);
  }

  public Iterator<Integer> iterator() {
    return new SequenceIterator();
  }

  private class SequenceIterator implements Iterator<Integer> {

    private int index = 0;

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Integer next() {
      return calculator.term(index++);
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove is not implemented");
    }
  }
}


