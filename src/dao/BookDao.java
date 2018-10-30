package dao;

import beens.BookFr;
import entity.Book;
import entity.Genre;

import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;

public class BookDao implements IBookDao {

    public BookDao() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lib");
    private EntityManager em = emf.createEntityManager();

    Logger logger = Logger.getLogger(BookDao.class.getSimpleName());

    @Override
    public List<BookFr> findAll(int pageNumber, int pageSize) {

        Query query = em.createQuery("select new beens.BookFr(b.id, b.name, b.pageCount, b.isbn, b.publishYear, b.descr, " +
                "b.image, b.genre, b.author, b.publisher) from Book b");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<BookFr>) query.getResultList();
    }

    @Override
    public List<BookFr> findByLetter(char letter, int pageNumber, int pageSize) {
        Query query = em.createQuery("select new beens.BookFr(b.id, b.name, b.pageCount, b.isbn, b.publishYear, b.descr, " +
                "b.image, b.genre, b.author, b.publisher) from Book b where substr(b.name,1,1)=:let order by b.name");
        query.setParameter("let", letter);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<BookFr>) query.getResultList();
    }


    @Override
    public List<BookFr> findByGenre(Genre genre, int pageNumber, int pageSize) {
        Query query = em.createQuery("select new beens.BookFr(b.id, b.name, b.pageCount, b.isbn, b.publishYear, b.descr, " +
                " b.image, b.genre, b.author, b.publisher) from Book b where b.genre=:gen order by b.name");
        query.setParameter("gen", genre);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<BookFr>) query.getResultList();
    }

    @Override
    public List<BookFr> findBySubstringName(String substr, int pageNumber, int pageSize) {
        String str = "%" + substr + "%";
        Query query = em.createQuery("select new beens.BookFr(b.id, b.name, b.pageCount, b.isbn, b.publishYear, b.descr, " +
                "b.image, b.genre, b.author, b.publisher) from Book b where lower(b.name) like :str order by b.name");
        query.setParameter("str", str);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<BookFr>) query.getResultList();
    }

    @Override
    public List<BookFr> findBySubstringAuthor(String substr, int pageNumber, int pageSize) {
        String str = "%" + substr + "%";
        Query query = em.createQuery("select new beens.BookFr(b.id, b.name, b.pageCount, b.isbn, b.publishYear, b.descr, " +
                "b.image, b.genre, b.author, b.publisher) from Book b where lower(b.author.fio) like :str order by b.name");
        query.setParameter("str", str);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<BookFr>) query.getResultList();
    }

    @Override
    public BookFr findById(Long id) {
        Query query = em.createQuery("select new beens.BookFr(b.id, b.name, b.pageCount, b.isbn, b.publishYear, b.descr, " +
                "b.image, b.genre, b.author, b.publisher) from Book b where b.id=:bid", BookFr.class);
        query.setParameter("bid", id);
        return (BookFr) query.getSingleResult();
    }

    @Override
    public byte[] getContentById(Long id) {
        Query query = em.createQuery("select b.content from Book b where b.id=:bid");
        query.setParameter("bid", id);
        return (byte[]) query.getSingleResult();
    }

    @Override
    public void save(BookFr book) {
        Book bookFromDB = em.getReference(Book.class, book.getId());
        em.getTransaction().begin();
        try {
            bookFromDB.setAuthor(book.getAuthor());
            bookFromDB.setDescr(book.getDescr());
            bookFromDB.setIsbn(book.getIsbn());
            bookFromDB.setName(book.getName());
            bookFromDB.setPageCount(book.getPageCount());
            bookFromDB.setPublisher(book.getPublisher());
            bookFromDB.setPublishYear(book.getPublishYear());
            em.merge(bookFromDB);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public Long countByLetter(char letter) {
        TypedQuery<Long> query = em.createQuery("select count (b.id) from Book b where substr(b.name,1,1)=:let", Long.class);
        query.setParameter("let", letter);
        return query.getSingleResult();
    }

    @Override
    public Long countByGenre(Genre genre) {
        TypedQuery<Long> query = em.createQuery("select count (b.id) from Book b where b.genre=:gen", Long.class);
        query.setParameter("gen", genre);
        return query.getSingleResult();
    }

    @Override
    public Long countBySubstringName(String sub) {
        String str = "%" + sub + "%";
        TypedQuery<Long> query = em.createQuery("select count (b.id) from Book b where lower(b.name) like :str", Long.class);
        query.setParameter("str", str);
        return query.getSingleResult();
    }

    @Override
    public Long countBySubstringAuthor(String sub) {
        String str = "%" + sub + "%";
        TypedQuery<Long> query = em.createQuery("select count (b.id) from Book b where lower(b.author.fio) like :str", Long.class);
        query.setParameter("str", str);
        return query.getSingleResult();
    }


}
