package service;

import beens.BookFr;
import entity.Genre;
import entity.User;

import java.util.List;

public interface IServiceDB {
    List<BookFr> findAllBooks(int pageNumber, int pageSize);

    List<BookFr> findBooksByGenreId(Long id, int pageNumber, int pageSize);

    List<BookFr> findBooksByLetter(char letter, int pageNumber, int pageSize);

    List<BookFr> findBooksBySubstringAuthor(String substr, int pageNumber, int pageSize);

    List<BookFr> findBooksBySubstringName(String substr, int pageNumber, int pageSize);

    List<Genre> findAllGenre();

    byte[] getBookImage(Long id);

    byte[] getBookContent(Long id);

    Long countBooksByLetter(char letter);

    Long countBooksByGenreId(Long gid);

    Long countBooksBySubstringName(String substr);

    Long countBooksBySubstringAuthor(String substr);

    User findUserByName(String name);

    void saveBook(BookFr book);
}
