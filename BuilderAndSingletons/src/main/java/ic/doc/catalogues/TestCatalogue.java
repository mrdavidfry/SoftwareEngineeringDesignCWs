package ic.doc.catalogues;

import ic.doc.Book;
import java.util.Arrays;
import java.util.Collection;

public class TestCatalogue extends LibraryCatalogue {

  // This class is used to test the BookSearchQuery implementation

  public static synchronized LibraryCatalogue getInstance() {
    if (instance == null) {
      instance = new TestCatalogue();
    }
    return instance;
  }

  @Override
  protected Collection<Book> allTheBooks() {
    return Arrays.asList(
        new Book("A Tale of Two Cities", "Charles Dickens", 1859),
        new Book("Pride and Prejudice", "Jane Austen", 1813),
        new Book("Pride and Prejudice", "Jane Austen", 1813),
        new Book("Oliver Twist", "Charles Dickens", 1838),
        new Book("Lord of the Flies", "William Golding", 1954),
        new Book("Hamlet", "William Shakespeare", 1603));
  }
}
