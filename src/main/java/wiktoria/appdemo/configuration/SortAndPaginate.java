package wiktoria.appdemo.configuration;

import org.springframework.ui.Model;
import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.rates.rate.Rate;
import wiktoria.appdemo.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortAndPaginate {

    public static List<Rate> paginateRates(List<Rate> rates, int page) {
        List<Rate> listToReturn = new ArrayList<>();
        page = page * 10 - 10;

        try {
            for (int i = 0; i < 10; i++) {
                listToReturn.add(rates.get(page + i));
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return listToReturn;
    }

    public static List<Book> paginateBooks(List<Book> books, int page) {
        List<Book> listToReturn = new ArrayList<>();
        page = page * 10 - 10;

        try {
            for (int i = 0; i < 10; i++) {
                listToReturn.add(books.get(page + i));
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return listToReturn;
    }

    public static List<User> paginateUsers(List<User> users, int page) {
        List<User> listToReturn = new ArrayList<>();
        page = page * 10 - 10;

        try {
            for (int i = 0; i < 10; i++) {
                listToReturn.add(users.get(page + i));
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return listToReturn;
    }

    public static Comparator sortBookByParam(String param) {
        switch (param) {
            case "title":
                return Comparator.comparing(Book::getTitle);
            case "author":
                return Comparator.comparing(Book::getAuthor);
            case "pages":
                return Comparator.comparing(Book::getPages);
            case "rate":
                return Comparator.comparing(Book::getUserrate);
            case "favorite":
                return Comparator.comparing(Book::isIsfav);
            case "toread":
                return Comparator.comparing(Book::isIstoread);
            case "avr":
                return Comparator.comparing(Book::getAvrate);
            case "rank":
                return Comparator.comparing(Book::getRankplace);
            default:
                return Comparator.comparing(Book::getTitle);
        }
    }

    public static Comparator sortRateByParam(String param) {
        switch (param) {
            case "rate":
                return Comparator.comparing(Rate::getValue);
            default:
                return Comparator.comparing(Rate::getTitle);
        }
    }

    public static List<Book> setBookSortOrder(List<Book> books, String order, String sort) {

        if (order.equals("asc")) {
            books.sort(sortBookByParam(sort));
        } else if (order.equals("desc")) {
            books.sort(sortBookByParam(sort).reversed());
        }

        return books;
    }

    public static List<Rate> setRateSortOrder(List<Rate> rates, String order, String sort) {

        if (order.equals("asc")) {
            rates.sort(sortRateByParam(sort));
        } else if (order.equals("desc")) {
            rates.sort(sortRateByParam(sort).reversed());
        }

        return rates;
    }

    public static void setBookModelAttributes (List<Book> list, int page, Model model){
        model.addAttribute("currentPage", page);
        int pages = (int) Math.ceil(list.size()/10.0);
        if(pages == 0){
            pages = 1;
        }
        model.addAttribute("totalPages", pages);
        model.addAttribute("booksList", SortAndPaginate.paginateBooks(list, page));
    }
}
