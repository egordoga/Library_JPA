package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int pageCount;
    private String isbn;
    private int publishYear;
    private String descr;
    @Lob
    private byte[] content;
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne  //Для упрощения считаем что у книги один автор. Правельнее было бы @ManyToMany
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                pageCount == book.pageCount &&
                genre == book.genre &&
                author == book.author &&
                publishYear == book.publishYear &&
                publisher == book.publisher &&
                Objects.equals(name, book.name) &&
                Arrays.equals(content, book.content) &&
                Objects.equals(isbn, book.isbn) &&
                Arrays.equals(image, book.image) &&
                Objects.equals(descr, book.descr);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, pageCount, isbn, genre, author, publishYear, publisher, descr);
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

   /* @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pageCount=" + pageCount +
                ", isbn='" + isbn + '\'' +
                ", publishYear=" + publishYear +
                ", descr='" + descr + '\'' +
                ", content=" + Arrays.toString(content) +
                ", image=" + Arrays.toString(image) +
                ", genre=" + genre +
                ", author=" + author +
                ", publisher=" + publisher +
                '}';
    }*/
}
