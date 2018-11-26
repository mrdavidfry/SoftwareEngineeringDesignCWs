package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import org.junit.Test;

public class TransformableListTest {

  static final int DEFAULT_SLOW_DELAY = 2000; // sets the 'runtime' of slow functions/binary-ops

  final Function<Integer, Integer> square = i -> i * i;

  final Function<Integer, Integer> slowSquare = i -> {
    delayMillis(DEFAULT_SLOW_DELAY);
    return i * i;
  };

  final BinaryOperator<Integer> sum = (x, y) -> x + y;

  final BinaryOperator<Integer> slowAdd = (x, y) -> {
    delayMillis(DEFAULT_SLOW_DELAY);
    return x + y;
  };

  TransformableList<Integer> integers = new TransformableList<Integer>(1, 2, 3, 4);

  @Test
  public void isInitialisedEmpty() {
    assertTrue(new TransformableList<>().isEmpty());
  }

  @Test
  public void canMapAFunctionAcrossAllValues() {

    TransformableList<Integer> squares = integers.map(square);

    Iterator<Integer> iterator = squares.iterator();
    assertThat(iterator.next(), is(1));
    assertThat(iterator.next(), is(4));
    assertThat(iterator.next(), is(9));
    assertThat(iterator.next(), is(16));
  }

  @Test
  public void canApplyMapRepeatably() {

    TransformableList<Integer> hyperCubes = integers.map(square).map(square);

    Iterator<Integer> iterator = hyperCubes.iterator();
    assertThat(iterator.next(), is(1));
    assertThat(iterator.next(), is(16));
    assertThat(iterator.next(), is(81));
    assertThat(iterator.next(), is(256));
  }

  @Test
  public void slowSquareMapExample() {
    integers.map(slowSquare);
  }

  @Test
  public void canBenReduced() {
    Integer total = integers.reduce(sum, 0);
    assertThat(total, is(10));
  }

  @Test
  public void supportsMapReduce() {
    Integer total = integers.map(square).reduce(sum, 0);
    assertThat(total, is(30));
  }

  @Test
  public void supportsConcurrentMapReduce() {
    Integer total = integers.map(square).concurrentReduce(sum, 0);
    assertThat(total, is(30));
  }

  @Test
  public void slowAddReduceWithoutConcurrencyExample() {
    integers.reduce(slowAdd, 0);
  }

  @Test
  public void slowAddReduceWithConcurrencyExample() {
    integers.concurrentReduce(slowAdd, 0);
  }

  // Helper function for adding a delay of millis
  private void delayMillis(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}