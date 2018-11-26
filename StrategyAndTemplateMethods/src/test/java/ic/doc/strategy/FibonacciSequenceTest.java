package ic.doc.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class FibonacciSequenceTest {

  final FibonacciCalculator calculator = new FibonacciCalculator();
  final Sequence sequence = new Sequence(calculator);

  @Test
  public void definesFirstTwoTermsToBeOne() {

    assertThat(sequence.getTerm(0), is(1));
    assertThat(sequence.getTerm(1), is(1));
  }

  @Test
  public void definesSubsequentTermsToBeTheSumOfThePreviousTwo() {

    assertThat(sequence.getTerm(2), is(2));
    assertThat(sequence.getTerm(3), is(3));
    assertThat(sequence.getTerm(4), is(5));
  }

  @Test
  public void isUndefinedForNegativeIndices() {
    CommonSequenceTests.undefinedForNegativeIndices(sequence);
  }

  @Test
  public void canBeIteratedThrough() {
    CommonSequenceTests.iterable(sequence);
  }

}