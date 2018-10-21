package service;

import dao.BookDao;
import lombok.Data;

import java.util.ArrayList;

@Data
public class SearchService {

    private ArrayList<Integer> pageNumbers = new ArrayList<>();

    BookDao bookDao = new BookDao();

    public SearchService() {
       // bookDao.findAll();
    }

    private void fillPageNumbers(long totalBooksCount, int booksOnPage) {

        int pageCount = totalBooksCount / booksOnPage != 0 ? (int) (totalBooksCount / booksOnPage + 1) : (int) (totalBooksCount / booksOnPage);
        pageNumbers.clear();
        for (int i = 1; i <= pageCount ; i++) {
            pageNumbers.add(i);
        }
    }


}
