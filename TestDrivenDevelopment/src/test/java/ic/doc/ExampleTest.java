package ic.doc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class ExampleTest {

  RecentList<String> stringList = new RecentList<>();

  @Test
  public void initializedEmptyList() {
    assertThat(new RecentList().length(), is(0));
  }

  @Test
  public void itemsCanBeInserted() {
    String s = "Hi!";
    stringList.add(s);
    assert (stringList.contains(s));
  }

  @Test
  public void lengthCanBeChecked() {
    stringList.add("Hello");
    assertThat(stringList.length(), is(1));
    stringList.add("Bye");
    assertThat(stringList.length(), is(2));
  }

  @Test
  public void itemsCanBeRetrieved() {
    stringList.add("Hello");
    stringList.add("Hello!");
    assert (stringList.retrieve(0) != null);
  }

  @Test
  public void mostRecentIsFirst() {
    stringList.add("Hello");
    stringList.add("Hi!");
    assert (stringList.retrieve(0).equals("Hi!"));
    stringList.add("ay");
    assert (stringList.retrieve(0).equals("ay"));
  }

  @Test
  public void elementsAreUnique() {
    stringList.add("Hi");
    stringList.add("Hello");
    stringList.add("Hi");
    assertThat(stringList.length(), is(2));
  }
}