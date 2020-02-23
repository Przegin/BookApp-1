package wiktoria.appdemo.rates;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.books.BookService;
import wiktoria.appdemo.configuration.BooksConfigurator;
import wiktoria.appdemo.configuration.SortAndPaginate;
import wiktoria.appdemo.rates.favorite.Favorite;
import wiktoria.appdemo.rates.favorite.FavoritesService;
import wiktoria.appdemo.rates.rate.Rate;
import wiktoria.appdemo.rates.rate.RatesService;
import wiktoria.appdemo.rates.recommendation.RecommendationController;
import wiktoria.appdemo.rates.recommendation.similarity.SimilarityService;
import wiktoria.appdemo.rates.toRead.ToRead;
import wiktoria.appdemo.rates.toRead.ToReadService;
import wiktoria.appdemo.user.User;
import wiktoria.appdemo.user.UserService;
import wiktoria.appdemo.utilities.UserUtilities;

@Controller
public class RateController {

    private UserService userService;
    private BookService bookService;
    private RatesService ratesService;
    private FavoritesService favoritesService;
    private ToReadService toReadService;
    private SimilarityService similarityService;
    private BooksConfigurator booksConfigurator;
    private RecommendationController recommendationController;

    public RateController(UserService userService, BookService bookService, RatesService ratesService,
                          FavoritesService favoritesService, ToReadService toReadService,
                          SimilarityService similarityService, BooksConfigurator booksConfigurator,
                          RecommendationController recommendationController) {
        this.userService = userService;
        this.bookService = bookService;
        this.ratesService = ratesService;
        this.favoritesService = favoritesService;
        this.toReadService = toReadService;
        this.similarityService = similarityService;
        this.booksConfigurator = booksConfigurator;
        this.recommendationController = recommendationController;
    }

    @GetMapping(value = "/addrate/{rate}/{view}/{bookid}/{page}/{sort}/{order}")
    @Secured(value = "ROLE_USER")
    public String addRate(Model model, @PathVariable("rate") int rate, @PathVariable("view") String view,
                          @PathVariable("bookid") int bookid, @PathVariable("page") int page,
                          @PathVariable("sort") String sort, @PathVariable("order") String order,
                          @RequestParam("search") String search) {
        saveRate(model, bookid, rate, page, sort, order, view, search);
        model.addAttribute("search", search);

        if (view.equals("book")) {
            return "books";
        } else {
            allRates(model, page, sort, order);
            return "rates";
        }

    }

    @GetMapping(value = "/favorite/{bookid}/{view}/{page}/{sort}/{order}")
    @Secured(value = "ROLE_USER")
    public String saveFavorite(Model model, @PathVariable("bookid") int bookid, @PathVariable("view") String view,
                               @PathVariable("page") int page, @PathVariable("sort") String sort,
                               @PathVariable("order") String order, @RequestParam("search") String search) {
        checkAndSaveOrDeleteParam(model, bookid, true, view, page, sort, order, search);
        model.addAttribute("search", search);

        if (view.equals("books"))
            return "books";
        else if (view.equals("recommend"))
            return "recommendation";
        else if (view.equals("fav"))
            return "favbooks";
        else if (view.equals("global"))
            return "globalrank";
        else
            return "index";
    }

    @GetMapping(value = "/toread/{bookid}/{view}/{page}/{sort}/{order}")
    @Secured(value = "ROLE_USER")
    public String saveToRead(Model model, @PathVariable("bookid") int bookid, @PathVariable("view") String view,
                             @PathVariable("page") int page, @PathVariable("sort") String sort,
                             @PathVariable("order") String order, @RequestParam("search") String search) {
        checkAndSaveOrDeleteParam(model, bookid, false, view, page, sort, order, search);
        model.addAttribute("search", search);

        if (view.equals("books"))
            return "books";
        else if (view.equals("recommend"))
            return "recommendation";
        else if (view.equals("tread"))
            return "toreadbooks";
        else if (view.equals("global"))
            return "globalrank";
        else if (view.equals("recom"))
            return "recommendbooks";
        else
            return "index";
    }

    @GetMapping(value = "/rates/{page}/{sort}/{order}")
    @Secured(value = "ROLE_USER")
    public String allRates(Model model, @PathVariable("page") int page, @PathVariable("sort") String sort,
                           @PathVariable("order") String order) {
        User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
        List<Rate> allUserRates = ratesService.findAllByUserid(user.getId());
        allUserRates = SortAndPaginate.setRateSortOrder(allUserRates, order, sort);

        model.addAttribute("currentPage", page);
        int pages = (int) Math.ceil(allUserRates.size()/10.0);
        if(pages == 0){
            pages = 1;
        }
        model.addAttribute("totalPages", pages);
        model.addAttribute("allUserRates", SortAndPaginate.paginateRates(allUserRates, page));
        return "rates";
    }

    @GetMapping(value = {"/ranking"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ranking() {
        User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
        List<Rate> rateList = ratesService.findAllByUserid(user.getId());

        for (int j = 0; j < rateList.size() - 1; j++) {

            for (int i = 0; i < rateList.size() - 1; i++) {
                Rate temp;

                if (rateList.get(i).getValue() < rateList.get(i + 1).getValue()) {
                    temp = rateList.get(i);
                    rateList.set(i, rateList.get(i + 1));
                    rateList.set(i + 1, temp);
                }
            }
        }

        ByteArrayInputStream bis = Generator.ranking(rateList, user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ranking.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = {"/toreadpdf"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> toreadpdf() {
        User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
        List<Integer> bookIdsToRead = new ArrayList<>();
        List<ToRead> toReadList = toReadService.findAllByUserid(user.getId());
        toReadList.stream().forEach(toRead -> bookIdsToRead.add(toRead.getBookid()));
        List<Book> booksToRead = bookService.findAllByIdIn(bookIdsToRead);

        ByteArrayInputStream bis = Generator.booksToRead(booksToRead);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=toread.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private void checkAndSaveOrDeleteParam(Model model, int bookid, boolean aboutFav, String view, int page, String sort,
                                           String order, String search) {
        User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
        List<Book> allBooks = bookService.findAll();

        if (aboutFav) {
            Favorite favorite = favoritesService.findByBookidAndUserid(bookid, user.getId());
            if (favorite == null) {
                favoritesService.save(new Favorite(user.getId(), bookid));
            } else {
                favoritesService.deleteByBookidAndUserid(bookid, user.getId());
            }
        } else {
            ToRead toRead = toReadService.findByBookidAndUserid(bookid, user.getId());
            if (toRead == null) {
                toReadService.save(new ToRead(user.getId(), bookid));
            } else {
                toReadService.deleteByBookidAndUserid(bookid, user.getId());
            }
        }

        allBooks = doSearch(allBooks, search);

        allBooks = booksConfigurator.setParameters(allBooks, user.getId());
        model.addAttribute("view", view);
        setModelAttributes(allBooks, view, model, aboutFav, sort, order, page, user);
        model.addAttribute("currentPage", page);
    }

    private void setModelAttributes(List<Book> allBooks, String view, Model model, boolean aboutFav, String sort,
                                    String order, int page, User user) {
        if (view.equals("fav") || view.equals("tread")) {
            List<Book> booksToShow = new ArrayList<>(allBooks);

            for (Book book : allBooks) {

                if (aboutFav && !book.isIsfav()) {
                    booksToShow.remove(book);
                } else if (!aboutFav && !book.isIstoread()) {
                    booksToShow.remove(book);
                }
            }
            booksToShow = SortAndPaginate.setBookSortOrder(booksToShow, order, sort);
            SortAndPaginate.setBookModelAttributes(booksToShow, page, model);
        } else if (view.equals("global")) {
            allBooks = SortAndPaginate.setBookSortOrder(allBooks, "desc", "avr");
            List<Book> toRank = new ArrayList<>();
            int nr = 1;

            for (Book book : allBooks) {
                if (book.getAvrate() == 0) {
                    break;
                }
                book.setRankplace(nr);
                nr++;
                toRank.add(book);
            }
            toRank = SortAndPaginate.setBookSortOrder(toRank, "asc", "rank");
            toRank = booksConfigurator.setParameters(toRank, user.getId());

            SortAndPaginate.setBookModelAttributes(toRank, page, model);
        } else if (view.equals("recom")) {
            List<Rate> userRates = ratesService.findAllByUserid(user.getId());
            recommendationController.prepareRecommendation(user, model, userRates, page);
        } else {
            allBooks = SortAndPaginate.setBookSortOrder(allBooks, order, sort);
            SortAndPaginate.setBookModelAttributes(allBooks, page, model);
        }
    }


    private void saveRate(Model model, int bookid, int rate, int page, String sort, String order, String view, String search) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        List<Book> allBooks = bookService.findAll();
        Rate rateFromDb = ratesService.findByUseridAndBookid(user.getId(), bookid);

        if (rateFromDb == null) {
            Rate rate1 = new Rate();
            rate1.setBookid(bookid);
            rate1.setUserid(user.getId());
            rate1.setValue(rate);
            for (Book book : allBooks) {

                if (book.getId() == bookid) {
                    rate1.setTitle(book.getTitle());
                }
            }
            ratesService.save(rate1);
        } else {

            if (rateFromDb.getValue() == rate) {
                ratesService.delete(rateFromDb);
            } else {
                ratesService.updateRate(rate, user.getId(), bookid);
            }
        }
        List<Rate> bookRates = new ArrayList<>(ratesService.findAllByBookid(bookid));
        Book ratedBook = bookService.findById(bookid);
        int index = 0;

        for (Book book : allBooks) {
            if (book.getId() == bookid) {
                index = allBooks.indexOf(book);
            }
        }

        if (bookRates.isEmpty()) {
            ratedBook.setAvrate(0);
        } else {
            float avr = 0;

            for (Rate rate1 : bookRates) {
                avr += rate1.getValue();
            }
            avr = avr / bookRates.size();
            ratedBook.setAvrate(Math.round(avr*100.0f)/100.0f);
        }
        allBooks.get(index).setAvrate(ratedBook.getAvrate());
        bookService.save(ratedBook);

        allBooks = doSearch(allBooks, search);

        if(view.equals("book")) {
            allBooks = booksConfigurator.setParameters(allBooks, user.getId());
            allBooks = SortAndPaginate.setBookSortOrder(allBooks, order, sort);
            SortAndPaginate.setBookModelAttributes(allBooks, page, model);
        }
    }

    List<Book> doSearch(List<Book> allBooks, String search){
        List<Book> searchedBooks = new ArrayList<>();

        for(Book book : allBooks){
            if(book.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(search.toLowerCase())){
                searchedBooks.add(book);
            }
        }

        return searchedBooks;
    }
}