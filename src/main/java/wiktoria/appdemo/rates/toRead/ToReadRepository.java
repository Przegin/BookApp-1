package wiktoria.appdemo.rates.toRead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("toReadRepository")
public interface ToReadRepository extends JpaRepository<ToRead, Integer> {

    List<ToRead> findAllByUserid(int userid);
    void deleteByBookidAndUserid(int bookid, int userid);
    ToRead findByBookidAndUserid(int bookid, int userid);
}
					
