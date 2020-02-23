package wiktoria.appdemo.rates.recommendation.similarity;

import java.util.List;

public interface SimilarityService {

    List<Similarity> findAll();
    void save(Similarity similarity);
    void saveAll(List<Similarity> similarityList);
    List<Similarity> findAllByBookid(int bookid);
    void deleteAllByBookid(int book);
    void deleteAll();
}