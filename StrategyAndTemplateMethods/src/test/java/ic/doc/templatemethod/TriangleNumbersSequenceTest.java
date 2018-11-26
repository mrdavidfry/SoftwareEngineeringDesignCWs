package ic.doc.templatemethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class TriangleNumbersSequenceTest {
  final TriangleNumbersSequence sequence = new TriangleNumbersSequence();

  @Test
  public void definesFirstTermToBeOne() {

    assertThat(sequence.term(0), is(1));
  }

  @Test
  public void definesSubsequentTermsToBeTheSumOfAllNaturalsUpToTheSuccessorOfTheIndex() {

    assertThat(sequence.term(1), is(3));
    assertThat(sequence.term(2), is(6));
    assertThat(sequence.term(3), is(10));
    assertThat(sequence.term(4), is(15));
  }

  @Test
  public void isUndefinedForNegativeIntegers() {
    CommonSequenceTests.undefinedForNegativeIndices(sequence);
  }

  @Test
  public void canBeIteratedThrough() {
    CommonSequenceTests.iterable(sequence);
  }
}
