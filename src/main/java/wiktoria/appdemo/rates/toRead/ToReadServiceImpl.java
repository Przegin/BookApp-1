package wiktoria.appdemo.rates.toRead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("toReadService")
@Transactional
public class ToReadServiceImpl implements ToReadService {

    @Autowired
    private ToReadRepository toReadRepository;

    @Override
    public List<ToRead> findAllByUserid(int userid) {
        return toReadRepository.findAllByUserid(userid);
    }

    @Override
    public void deleteByBookidAndUserid(int bookid, int userid) {
        toReadRepository.deleteByBookidAndUserid(bookid, userid);
    }

    @Override
    public ToRead findByBookidAndUserid(int bookid, int userid) {
        return toReadRepository.findByBookidAndUserid(bookid, userid);
    }

    @Override
    public void save(ToRead toRead) {
        toReadRepository.save(toRead);
    }
}
