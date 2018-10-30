package controller;

import beens.BookFr;
import lombok.Data;
import service.ServiceDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Data
@ManagedBean(eager = true)
@SessionScoped
public class SearchController implements Serializable {

    private Logger logger = Logger.getLogger(SearchController.class.getSimpleName());

    private Character[] russianLetters = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О',
            'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    private SearchType searchType;
    private String currentSql;
    private long totalBooksCount;
    private int booksOnPage = 2;
    private int selectedPageNumber = 1;
    private List<BookFr> currentBookList;
    private Long selectedGenreId;
    private String searchString;
    private char selectedLetter;
    private ArrayList<Integer> pageNumbers = new ArrayList<>();
    private boolean editMode;
    private String currentSqlWithoutLimit;
    private int fillChoice = 0;

    private ServiceDB serviceDB = new ServiceDB();

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lib");
    private EntityManager em = emf.createEntityManager();

    public SearchController() {
        serviceDB.findAllBooks(selectedPageNumber, booksOnPage);
    }


    private void fillBooks() {
        switch (fillChoice) {
            case 1:
                fillBooksByGenre(selectedGenreId);
                //selectedPageNumber = 1;
                break;
            case 2:
                fillBooksByLetter();
                //selectedPageNumber = 1;
                break;
            case 3:
                fillBookBySearch();
                //selectedPageNumber = 1;
                break;
        }
    }

    private void fillPageNumbers(long totalBooksCount, int booksOnPage) {
        int pageCount = totalBooksCount / booksOnPage != 0 ? (int) (totalBooksCount / booksOnPage + 1) : (int) (totalBooksCount / booksOnPage);
        pageNumbers.clear();
        for (int i = 1; i <= pageCount; i++) {
            pageNumbers.add(i);
        }
    }

    public void fillBooksByGenre(Long id) {

        selectedGenreId = id;
        // selectedPageNumber = 1;
        currentBookList = serviceDB.findBooksByGenreId(id, selectedPageNumber, booksOnPage);
        totalBooksCount = serviceDB.countBooksByGenreId(selectedGenreId);
        fillPageNumbers(totalBooksCount, booksOnPage);
        selectedLetter = 'j';
        searchString = "";
        fillChoice = 1;
        //fillBooks();
        //return "index";
    }

    public void fillBooksByLetter() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedLetter = params.get("letter").charAt(0);
        totalBooksCount = serviceDB.countBooksByLetter(selectedLetter);
        currentBookList = serviceDB.findBooksByLetter(selectedLetter, selectedPageNumber, booksOnPage);
        selectedGenreId = -1L;
        //selectedPageNumber = 1;
        fillChoice = 2;
        //fillBooks();
    }

    public void fillBookBySearch() {
        if (searchString.trim().length() == 0) {
            fillBooksAll();
            return;
        }

        if (searchType == SearchType.AUTHOR) {
            currentBookList = serviceDB.findBooksBySubstringAuthor(searchString, selectedPageNumber, booksOnPage);
            totalBooksCount = serviceDB.countBooksBySubstringAuthor(searchString);
        } else if (searchType == SearchType.NAME) {
            currentBookList = serviceDB.findBooksBySubstringName(searchString, selectedPageNumber, booksOnPage);
            totalBooksCount = serviceDB.countBooksBySubstringName(searchString);
        }

        selectedLetter = ' ';
        selectedGenreId = -1L;
        //selectedPageNumber = 1;
    }

    private void fillBooksAll() {
        currentBookList = serviceDB.findAllBooks(selectedPageNumber, booksOnPage);
    }

    public void selectedPage() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedPageNumber = Integer.valueOf(params.get("page_number"));
        fillBooks();
        selectedPageNumber = 1;
        //fillBooksBySQL(currentSql);
        //return "index";
    }

    public byte[] getContent(Long id) {
        return serviceDB.getBookContent(id);
    }

    public byte[] getImage(Long id) {
        return serviceDB.getBookImage(id);
    }

    public String getUpdateBooks() {
        for (BookFr bookFr : currentBookList) {
            serviceDB.saveBook(bookFr);
        }
        return "main";
    }

    public void changeBooksOnPage(ValueChangeEvent event) {
        //pageSelected = false;
        booksOnPage = Integer.valueOf(event.getNewValue().toString());
        selectedPageNumber = 1;
        fillBooks();
        // fillBooksBySQL(currentSqlWithoutLimit);
    }

    public void switchEditMode() {
        editMode = !editMode;
    }

}
