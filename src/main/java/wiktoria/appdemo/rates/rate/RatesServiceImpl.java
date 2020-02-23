package wiktoria.appdemo.rates.rate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ratesService")
@Transactional
public class RatesServiceImpl implements RatesService {

    @Autowired
    private RatesRepository ratesRepository;

    @Override
    public List<Rate> findAllByUserid(int userid) {
        return ratesRepository.findAllByUserid(userid);
    }

    @Override
    public List<Rate> findAllByBookid(int bookid) {
        return ratesRepository.findAllByBookid(bookid);
    }

    @Override
    public void save(Rate rate) {
        ratesRepository.save(rate);
    }

    @Override
    public void delete(Rate rate) {
        ratesRepository.delete(rate);
    }

    @Override
    public Rate findByUseridAndBookid(int userid, int bookid) {
        return ratesRepository.findByUseridAndBookid(userid, bookid);
    }

    @Override
    public void updateRate(int value, int userid, int bookid) {
        ratesRepository.updateRate(value, userid, bookid);
    }

    @Override
    public void deleteAllByUserid(int userid){
        ratesRepository.deleteAllByUserid(userid);
    }
}
