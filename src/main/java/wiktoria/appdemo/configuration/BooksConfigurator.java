package wiktoria.appdemo.configuration;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import org.springframework.stereotype.Service;
import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.rates.RateController;
import wiktoria.appdemo.rates.favorite.Favorite;
import wiktoria.appdemo.rates.favorite.FavoritesService;
import wiktoria.appdemo.rates.rate.Rate;
import wiktoria.appdemo.rates.rate.RatesService;
import wiktoria.appdemo.rates.toRead.ToRead;
import wiktoria.appdemo.rates.toRead.ToReadService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksConfigurator {

    Logger logger = LoggerFactory.getLogger(RateController.class);

    private RatesService ratesService;
    private FavoritesService favoritesService;
    private ToReadService toReadService;

    public BooksConfigurator(RatesService ratesService, FavoritesService favoritesService, ToReadService toReadService) {
        this.ratesService = ratesService;
        this.favoritesService = favoritesService;
        this.toReadService = toReadService;
    }
    public List<Book> setParameters(List<Book> books, int userId) {
        List<Favorite> favorites = new ArrayList<>();
        List<ToRead> toReadBooks = new ArrayList<>();
        List<Rate> userRates = new ArrayList<>();

        try {
            favorites = favoritesService.findAllByUserid(userId);
            toReadBooks = toReadService.findAllByUserid(userId);
            userRates = ratesService.findAllByUserid(userId);

        } catch (NullPointerException e) {
            logger.info(e.getMessage());
        }

        books.stream().forEach(book -> {
            book.setIsfav(false);
            book.setIstoread(false);
            book.setUserrate(0);
        });

        for (Book book : books) {

            if (favorites != null) {
                Favorite singleFavorite = findSingleFavorite(book.getId(), favorites);
                if (singleFavorite != null) {
                    book.setIsfav(true);
                }
            }

            if (toReadBooks != null) {
                ToRead singleToRead = findSingleToRead(book.getId(), toReadBooks);
                if (singleToRead != null) {
                    book.setIstoread(true);
                }
            }

            if (userRates != null) {
                Rate singleRate = findSingleRate(book.getId(), userRates);
                if (singleRate != null) {
                    book.setUserrate(singleRate.getValue());
                }
            }
        }
        return books;
    }

    private Favorite findSingleFavorite(int bookid, List<Favorite> favorites) {
        for (Favorite favorite : favorites) {
            if (favorite.getBookid() == bookid) {
                return favorite;
            }
        }
        return null;
    }

    private ToRead findSingleToRead(int bookid, List<ToRead> toReads) {
        for (ToRead toRead : toReads) {
            if (toRead.getBookid() == bookid) {
                return toRead;
            }
        }
        return null;
    }

    private Rate findSingleRate(int bookid, List<Rate> rates) {
        for (Rate rate : rates) {
            if (rate.getBookid() == bookid) {
                return rate;
            }
        }
        return null;
    }
}
