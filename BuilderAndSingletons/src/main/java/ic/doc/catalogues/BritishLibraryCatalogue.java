package ic.doc.catalogues;

import ic.doc.Book;
import java.util.Arrays;
import java.util.Collection;

public class BritishLibraryCatalogue extends LibraryCatalogue {

  // imagine that each new instance of this object uses more than 500MB of RAM

  private BritishLibraryCatalogue() {
    super();
  }

  public static synchronized LibraryCatalogue getInstance() {
    if (instance == null) {
      instance = new BritishLibraryCatalogue();
    }
    return instance;
  }

  @Override
  protected Collection<Book> allTheBooks() {

    System.out.println("Memory Usage: 500MB...");

    return Arrays.asList(
        new Book("A Tale of Two Cities", "Charles Dickens", 1859),
        new Book("Pride and Prejudice", "Jane Austen", 1813),
        new Book("Pride and Prejudice", "Jane Austen", 1813),
        new Book("The Picture of Dorian Gray", "Oscar Wilde", 1890),
        new Book("Oliver Twist", "Charles Dickens", 1838),
        new Book("Frankenstein", "Mary Shelley", 1817),
        new Book("Brave New World", "Aldous Huxley", 1932),
        new Book("Lord of the Flies", "William Golding", 1954),
        new Book("Hamlet", "William Shakespeare", 1603),
        new Book("The Life and Opinions of Tristram Shandy, Gentleman", "Laurence Sterne", 1759));

    // and so on... Imagine that this list is very large and therefore uses a lot of memory.
  }

}
