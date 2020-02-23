package wiktoria.appdemo.rates.recommendation.similarity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("similarityService")
@Transactional
public class SimilarityServiceImpl implements SimilarityService {

    @Autowired
    private SimilarityRepository similarityRepository;

    @Override
    public List<Similarity> findAll() {
        return similarityRepository.findAll();
    }

    @Override
    public List<Similarity> findAllByBookid(int bookid){
        return similarityRepository.findAllByBookaidOrBookbid(bookid, bookid);
    }

    @Override
    public void deleteAllByBookid(int bookid){
        similarityRepository.deleteAllByBookaidOrBookbid(bookid, bookid);
    }

    @Override
    public void deleteAll(){
        similarityRepository.deleteAll();
    }

    public void save(Similarity similarity){
        similarityRepository.save(similarity);
    }

    public void saveAll(List<Similarity> similarityList){
        similarityRepository.saveAll(similarityList);
    }
}