package wiktoria.appdemo.rates.favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("favoritesRepository")
public interface FavoritesRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findAllByUserid(int userid);
    void deleteByBookidAndUserid(int bookid, int userid);
    Favorite findByBookidAndUserid(int bookid, int userid);
}
					
