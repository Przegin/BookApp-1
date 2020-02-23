package wiktoria.appdemo.rates.recommendation.similarity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("similarityRepository")
public interface SimilarityRepository extends JpaRepository<Similarity, Integer> {

    List<Similarity> findAll();
    List<Similarity> findAllByBookaidOrBookbid(int bookaid, int bookbid);
    void deleteAllByBookaidOrBookbid(int bookaid, int bookbid);
}