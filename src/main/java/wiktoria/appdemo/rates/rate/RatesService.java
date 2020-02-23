package wiktoria.appdemo.rates.rate;

import java.util.List;

public interface RatesService {

	List<Rate> findAllByUserid(int userid);
	List<Rate> findAllByBookid(int bookid);
	void save(Rate rate);
	void delete(Rate rate);
	Rate findByUseridAndBookid(int userid, int bookid);
	void updateRate(int value, int userid, int bookid);
	void deleteAllByUserid(int userid);
}

				