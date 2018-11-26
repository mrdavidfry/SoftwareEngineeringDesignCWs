package ic.doc;

import static ic.doc.catalogues.BookSearchQueryBuilder.searchQuery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ic.doc.catalogues.LibraryCatalogue;
import ic.doc.catalogues.TestCatalogue;
import java.util.List;
import org.junit.Test;

public class BookSearchQueryTest {

  private LibraryCatalogue catalogue = TestCatalogue.getInstance();

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = searchQuery(catalogue).withLastName("dickens").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books = searchQuery(catalogue).withFirstName("Jane").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = searchQuery(catalogue).withTitle("Two Cities").build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = searchQuery(catalogue).publishedBefore(1700).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = searchQuery(catalogue).publishedAfter(1950).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = searchQuery(catalogue).withLastName("dickens")
                                             .publishedBefore(1840)
                                             .build()
                                             .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
