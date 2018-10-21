package dao;

import beens.BookFr;
import entity.Book;
import entity.Genre;

import java.util.List;

public interface IBookDao {
    List<BookFr> findAll(int pageNumber, int pageSize);

    List<BookFr> findByLetter(char letter, int pageNumber, int pageSize);
    Long countByLetter(char letter);
    Long countByGenre(Genre genre);
    Long countBySubstringName(String substr);
    Long countBySubstringAuthor(String substr);

    List<BookFr> findByGenre(Genre genre, int pageNumber, int pageSize);


    List<BookFr> findBySubstringName(String substr, int pageNumber, int pageSize);

    List<BookFr> findBySubstringAuthor(String substr, int pageNumber, int pageSize);

    BookFr findById(Long id);

    byte[] getContentById(Long id);

    void save(BookFr book);

}
