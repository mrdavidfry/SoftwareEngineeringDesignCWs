package ic.doc.templatemethod;

import static ic.doc.matchers.IterableBeginsWith.beginsWith;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;


public class CommonSequenceTests {

  public static void undefinedForNegativeIndices(Sequence sequence) {

    try {
      sequence.term(-1);
      fail("should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage(), containsString("Not defined for indices < 0"));
    }
  }

  public static void iterable(Sequence sequence) {
    int[] ts = new int[5];
    for (int i = 0; i < ts.length; i++) {
      ts[i] = sequence.term(i);
    }
    assertThat(sequence, beginsWith(ts[0], ts[1], ts[2], ts[3], ts[4]));
  }
}
