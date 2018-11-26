package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StandardOperatorsTests {

  @Test
  public void sumOfTwoNumbersTests() {
    assertThat(StandardOperators.sum.apply(2, 5), is(7));
    assertThat(StandardOperators.sum.apply(-2, 5), is(3));
    assertThat(StandardOperators.sum.apply(-2, -1), is(-3));
  }

  @Test
  public void diffOfTwoNumbersTests() {

    assertThat(StandardOperators.diff.apply(5, 2), is(3));
    assertThat(StandardOperators.diff.apply(2, -5), is(7));
    assertThat(StandardOperators.diff.apply(-2, -1), is(-1));
  }
}
