package ic.doc.catalogues;

import static ic.doc.catalogues.QueryParser.firstNameFrom;
import static ic.doc.catalogues.QueryParser.lastNameFrom;
import static ic.doc.catalogues.QueryParser.publishedAfterFrom;
import static ic.doc.catalogues.QueryParser.publishedBeforeFrom;
import static ic.doc.catalogues.QueryParser.titleFrom;

import ic.doc.Book;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class LibraryCatalogue {

  private final Collection<Book> catalogue = allTheBooks();

  protected static LibraryCatalogue instance;

  public List<Book> searchFor(String query) {
    return catalogue.stream()
        .filter(book -> book.matchesAuthor(lastNameFrom(query)))
        .filter(book -> book.matchesAuthor(firstNameFrom(query)))
        .filter(book -> book.matchesTitle(titleFrom(query)))
        .filter(book -> book.publishedSince(publishedAfterFrom(query)))
        .filter(book -> book.publishedBefore(publishedBeforeFrom(query)))
        .collect(Collectors.toList());
  }

  protected abstract Collection<Book> allTheBooks();
}
