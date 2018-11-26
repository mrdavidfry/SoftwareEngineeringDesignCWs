package ic.doc;

import java.util.ArrayList;

public class RecentList<T> {
  private ArrayList<T> list = new ArrayList<>();

  public int length() {
    return list.size();
  }

  public boolean contains(T s) {
    return list.contains(s);
  }

  public void add(T s) {
    if (contains(s)) {
      list.remove(s);
    }
    list.add(0, s);
  }

  public T retrieve(int index) {
    if (index >= length()) {
      return null;
    } else {
      return list.get(index);
    }
  }
}


