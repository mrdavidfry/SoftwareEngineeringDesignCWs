package ic.doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class TransformableList<T> implements Iterable<T> {

  private static final int NUM_THREADS = 2; // Number of threads available for concurrent processes
  private final List<T> store = new ArrayList<T>();
  private ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

  public TransformableList() {
  }

  @SuppressWarnings(value = "unchecked")
  public TransformableList(T... values) {
    this(Arrays.asList(values));
  }

  public TransformableList(Collection<T> values) {
    store.addAll(values);
  }

  @Override
  public Iterator<T> iterator() {
    return store.iterator();
  }

  public <R> TransformableList<R> map(final Function<T, R> function) {

    List<R> result = new ArrayList<>();
    List<Future<R>> futures = new ArrayList<>();
    for (final T t : store) {
      Callable c = () -> function.apply(t);
      futures.add(executorService.submit(c));
    }

    R mappedValue;
    for (final Future<R> f : futures) {
      try {
        mappedValue = f.get();
        result.add(mappedValue);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }

    return new TransformableList<R>(result);
  }

  public T reduce(BinaryOperator<T> function, T initialValue) {
    return reduce(function, initialValue, null);
  }

  private T reduce(BinaryOperator<T> function, T initialValue, List<T> store) {
    if (store == null) { // if store == null then reduce() was not called by concurrentReduce
      store = this.store;
    }

    if (store.isEmpty()) {
      return initialValue;
    }
    T accumulator = function.apply(initialValue, store.get(0));
    for (T t : store.subList(1, store.size())) {
      accumulator = function.apply(accumulator, t);
    }
    return accumulator;
  }

  // PRE: BinaryOperation function MUST be commutative
  public T concurrentReduce(BinaryOperator<T> function, T initialValue) {

    ArrayList<ArrayList<T>> subStore = new ArrayList<>(); // stores the sub ArrayLists
    int noOfStores = NUM_THREADS;
    // create noOfStores sub ArrayLists, such that store is the concatenation of substores:
    for (int i = 0; i < noOfStores; i++) {
      subStore.add(new ArrayList<>());
    }
    int storeSize = store.size();
    // divide store size by the number of sub-ArrayLists:
    int fracStoreSize = store.size() / noOfStores;

    // split the store list into noOfStores lists:
    for (int i = 0; i < fracStoreSize; i++) {
      for (int j = 0; j < noOfStores; j++) {
        subStore.get(j).add(store.get(i + j * fracStoreSize));
      }
    }

    int lastStoreEndIndex = noOfStores * fracStoreSize; // index of the first 'forgotten' element
    // add the 'forgotten' elements to last store:
    for (int i = lastStoreEndIndex; i < storeSize; i++) {
      subStore.get(noOfStores - 1).add(store.get(i));
    }

    ArrayList<Future<T>> reducedFutures = new ArrayList<>();
    for (final ArrayList<T> sub : subStore) {
      T initialVal = sub.remove(0); // the head of each sub list should be the initial reduce value
      Callable c = () -> reduce(function, initialVal, sub);
      reducedFutures.add(executorService.submit(c));
    }

    ArrayList<T> reduceSubResults = new ArrayList<>(); // stores result of sub reduces
    for (int i = 0; i < noOfStores; i++) {
      try {
        reduceSubResults.add(reducedFutures.get(i).get()); // add each sub result to reduceSubResult
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    return reduce(function, initialValue, reduceSubResults); // combine the sub-reductions
  }

  public boolean isEmpty() {
    return store.isEmpty();
  }
}
