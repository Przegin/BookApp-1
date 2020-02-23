package wiktoria.appdemo.rates.toRead;

import java.util.List;

public interface ToReadService {

    List<ToRead> findAllByUserid(int userid);
    void deleteByBookidAndUserid(int bookid, int userid);
    ToRead findByBookidAndUserid(int bookid, int userid);
    void save(ToRead toRead);
}
