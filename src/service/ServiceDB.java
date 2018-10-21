package service;

import beens.BookFr;
import dao.BookDao;
import dao.GenreDao;
import dao.UserDao;
import entity.Book;
import entity.Genre;
import entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ServiceDB implements IServiceDB {

    private BookDao bookDao = new BookDao();
    private GenreDao genreDao = new GenreDao();
    private UserDao userDao = new UserDao();

    @Override
    public List<BookFr> findAllBooks(int pageNumber, int pageSize) {
        return bookDao.findAll(pageNumber, pageSize);
    }

    @Override
    public List<Genre> findAllGenre() {
        return genreDao.findAll();
    }

    @Override
    public byte[] getBookImage(Long id) {
        BookFr book = bookDao.findById(id);
        return book.getImage();
    }

    @Override
    public byte[] getBookContent(Long id) {
        return bookDao.getContentById(id);
    }

    @Override
    public Long countBooksByLetter(char letter) {
        return bookDao.countByLetter(letter);
    }

    @Override
    public Long countBooksByGenreId(Long gid) {
        Genre genre = genreDao.findById(gid);
        return bookDao.countByGenre(genre);
    }

    @Override
    public Long countBooksBySubstringName(String substr) {
        return bookDao.countBySubstringName(substr);
    }

    @Override
    public Long countBooksBySubstringAuthor(String substr) {
        return bookDao.countBySubstringAuthor(substr);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void saveBook(BookFr book) {
        bookDao.save(book);
    }

    @Override
    public List<BookFr> findBooksByGenreId(Long id, int pageNumber, int pageSize) {
        Genre genre = genreDao.findById(id);
        return bookDao.findByGenre(genre, pageNumber, pageSize);
    }

    @Override
    public List<BookFr> findBooksByLetter(char letter, int pageNumber, int pageSize) {
        return bookDao.findByLetter(letter, pageNumber, pageSize);
    }

    @Override
    public List<BookFr> findBooksBySubstringAuthor(String substr, int pageNumber, int pageSize) {
        return bookDao.findBySubstringAuthor(substr, pageNumber, pageSize);
    }

    @Override
    public List<BookFr> findBooksBySubstringName(String substr, int pageNumber, int pageSize) {
        return bookDao.findBySubstringName(substr, pageNumber, pageSize);
    }


}
