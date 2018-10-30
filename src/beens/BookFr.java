package beens;

import entity.Author;
import entity.Genre;
import entity.Publisher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Getter
@Setter
@NoArgsConstructor
@ManagedBean
@SessionScoped
public class BookFr {

    private long id;
    private String name;
    private int pageCount;
    private String isbn;
    private int publishYear;
    private String descr;
    private byte[] content;
    private byte[] image;
    private Genre genre;
    private Author author;
    private Publisher publisher;

    public BookFr(long id, String name, int pageCount, String isbn, int publishYear, String descr, byte[] image, Genre genre, Author author, Publisher publisher) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.descr = descr;
        this.image = image;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
    }
}
