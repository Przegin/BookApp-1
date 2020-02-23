package wiktoria.appdemo.rates.favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("favoritesService")
@Transactional
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Override
    public List<Favorite> findAllByUserid(int userid) {
        return favoritesRepository.findAllByUserid(userid);
    }

    @Override
    public void deleteByBookidAndUserid(int bookid, int userid) {
        favoritesRepository.deleteByBookidAndUserid(bookid, userid);
    }

    @Override
    public Favorite findByBookidAndUserid(int bookid, int userid) {
        return favoritesRepository.findByBookidAndUserid(bookid, userid);
    }

    @Override
    public void save(Favorite favorite) {
        favoritesRepository.save(favorite);
    }
}
