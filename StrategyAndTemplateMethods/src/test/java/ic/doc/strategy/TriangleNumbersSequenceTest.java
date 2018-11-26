package ic.doc.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class TriangleNumbersSequenceTest {

  final TriangleNumbersCalculator calculator = new TriangleNumbersCalculator();
  final Sequence sequence = new Sequence(calculator);

  @Test
  public void definesFirstTermToBeOne() {

    assertThat(sequence.getTerm(0), is(1));
  }

  @Test
  public void definesSubsequentTermsToBeTheSumOfAllNaturalsUpToTheSuccessorOfTheIndex() {

    assertThat(sequence.getTerm(1), is(3));
    assertThat(sequence.getTerm(2), is(6));
    assertThat(sequence.getTerm(3), is(10));
    assertThat(sequence.getTerm(4), is(15));
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