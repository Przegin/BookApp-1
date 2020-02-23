package wiktoria.appdemo.rates.favorite;

import java.util.List;

public interface FavoritesService {

    List<Favorite> findAllByUserid(int userid);
    Favorite findByBookidAndUserid(int bookid, int userid);
    void deleteByBookidAndUserid(int bookid, int userid);
    void save(Favorite favorite);
}
