package wiktoria.appdemo.rates.recommendation;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.books.BookService;
import wiktoria.appdemo.configuration.BooksConfigurator;
import wiktoria.appdemo.configuration.SortAndPaginate;
import wiktoria.appdemo.rates.rate.Rate;
import wiktoria.appdemo.rates.rate.RatesService;
import wiktoria.appdemo.rates.recommendation.similarity.Similarity;
import wiktoria.appdemo.rates.recommendation.similarity.SimilarityService;
import wiktoria.appdemo.user.User;
import wiktoria.appdemo.user.UserService;
import wiktoria.appdemo.utilities.UserUtilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class RecommendationController {

    private SimilarityService similarityService;
    private BookService bookService;
    private RatesService ratesService;
    private UserService userService;
    private BooksConfigurator booksConfigurator;

    public RecommendationController(SimilarityService similarityService, BookService bookService,
                                    BooksConfigurator booksConfigurator, RatesService ratesService,
                                    UserService userService) {
        this.similarityService = similarityService;
        this.bookService = bookService;
        this.ratesService = ratesService;
        this.userService = userService;
        this.booksConfigurator = booksConfigurator;
    }

        //    @GetMapping(value = "/similarity12345")
        //    @Secured(value = {"ROLE_ADMIN"})
    public String countSimilarity() {
        List<Book> allBooks = bookService.findAll();
        List<Similarity> similarityList = new ArrayList<>();
        similarityService.deleteAll();

        for (int i = 0; i < allBooks.size(); i++) {

            for (int j = i + 1; j < allBooks.size(); j++) {
                Similarity similarityObject = countSingleSimilarity(allBooks.get(i), allBooks.get(j));
                similarityList.add(similarityObject);
            }
        }
        similarityService.saveAll(similarityList);

        return "index";
    }

    public Similarity countSingleSimilarity(Book booka, Book bookb){
        float similarity = 0;

        if (booka.getCsex().equals(bookb.getCsex())) {
            similarity += 1;
        }
        if (booka.getVictimnr().equals(bookb.getVictimnr())) {
            similarity += 1;
        }
        if (booka.getMotive().equals(bookb.getMotive())) {
            similarity += 1;
        }
        if (booka.getCrimetype().equals(bookb.getCrimetype())) {
            similarity += 1;
        }
        if (booka.getCrimescene().equals(bookb.getCrimescene())) {
            similarity += 1;
        }
        if (booka.getInvexecutor().equals(bookb.getInvexecutor())) {
            similarity += 1;
        }
        if (booka.getDsex().equals(bookb.getDsex())) {
            similarity += 1;
        }
        if (booka.getStorybcg().equals(bookb.getStorybcg())) {
            similarity += 1;
        }
        if (booka.getVibe().equals(bookb.getVibe())) {
            similarity += 1;
        }
        similarity = similarity * 1.11f + 0.01f;
        return new Similarity(booka.getId(), bookb.getId(), similarity);
    }

    @GetMapping(value = "/recommendation/{page}")
    public String recommendate(Model model, @PathVariable("page") int page) {
        User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
        List<Rate> userRates = ratesService.findAllByUserid(user.getId());

        if (userRates == null || userRates.isEmpty()) {
            return "ratesomething";
        }

        List<Book> books = prepareRecommendation(user, model, userRates, page);

        if (books == null || books.isEmpty()) {
            return "ratesomething";
        }

        return "recommendbooks";
    }

    public List<Book> prepareRecommendation(User user, Model model, List<Rate> userRates, int page) {
        List<Integer> notRatedBookids = new ArrayList<>();
        List<Integer> ratedBookids = new ArrayList<>();
        List<Book> notRatedBooks = new ArrayList<>();
        List<Similarity> similarityList = similarityService.findAll();
        List<Book> allBooks = bookService.findAll();

        for (Book book : allBooks) {
            notRatedBookids.add(book.getId());
        }

        for (Rate rate : userRates) {
            ratedBookids.add(new Integer(rate.getBookid()));
            notRatedBookids.remove(new Integer(rate.getBookid()));
        }

        for (Book book : allBooks) {

            if (notRatedBookids.contains(book.getId())) {
                List<Similarity> similaritiesOfBook = findSimilaritiesofBook(book.getId(), similarityList);
                float recommendValue = 0;
                float numberOfBooks = 0.00001f;

                for (Rate rate : userRates) {
                    Similarity singleSimilarity = findSingleSimilarity(book.getId(), rate.getBookid(), similaritiesOfBook);

                    if (singleSimilarity != null) {
                        recommendValue = recommendValue + (getRateValue(rate) * singleSimilarity.getSimilar());
                        numberOfBooks++;
                    }
                }
                book.setChancetolikeit(Math.round(recommendValue / (numberOfBooks * 4.5f)));

                if (book.getChancetolikeit() > 30) {
                    notRatedBooks.add(book);
                }
            }
        }
        notRatedBooks = booksConfigurator.setParameters(notRatedBooks, user.getId());
        notRatedBooks.sort(Comparator.comparing(Book::getChancetolikeit).reversed());
        SortAndPaginate.setBookModelAttributes(notRatedBooks, page, model);
        return notRatedBooks;
    }

    private static int getRateValue(Rate rate) {

        if (rate.getValue() == 1) {
            return 1;
        } else if (rate.getValue() == 2) {
            return 2;
        } else if (rate.getValue() == 3) {
            return 4;
        } else if (rate.getValue() == 4) {
            return 8;
        } else if (rate.getValue() == 5) {
            return 15;
        } else if (rate.getValue() == 6) {
            return 25;
        } else if (rate.getValue() == 7) {
            return 31;
        } else if (rate.getValue() == 8) {
            return 37;
        } else if (rate.getValue() == 9) {
            return 43;
        } else {
            return 45;
        }
    }

    private static Similarity findSingleSimilarity(int bookaid, int bookbid, List<Similarity> similarities) {

        for (Similarity similarity : similarities) {

            if ((similarity.getBookaid() == bookaid && similarity.getBookbid() == bookbid) ||
                    (similarity.getBookaid() == bookbid && similarity.getBookbid() == bookaid)) {

                if (similarity.getSimilar() > 5) {
                    return similarity;
                }
            }
        }

        return null;
    }

    private static List<Similarity> findSimilaritiesofBook(int bookid, List<Similarity> similarities) {
        List<Similarity> similaritiesOfBook = new ArrayList<>();

        for (Similarity similarity : similarities) {
            if (similarity.getBookaid() == bookid || similarity.getBookbid() == bookid) {
                similaritiesOfBook.add(similarity);
            }
        }

        return similaritiesOfBook;
    }
}