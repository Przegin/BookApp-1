package wiktoria.appdemo.rates.rate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("ratesRepository")
public interface RatesRepository extends JpaRepository<Rate, Integer> {

    List<Rate> findAllByUserid(int userid);
    List<Rate> findAllByBookid(int bookid);
    Rate findByUseridAndBookid(int userid, int bookid);
    void deleteAllByUserid(int userid);

    @Modifying
    @Query("UPDATE Rate r SET r.value = :value WHERE r.userid= :userid AND r.bookid = :bookid")
    //w query jest update, który przyjmuje parametry (named query bo mają nazwe)
    void updateRate(@Param("value") int value, @Param("userid") int userid, @Param("bookid") int bookid);
}
					
