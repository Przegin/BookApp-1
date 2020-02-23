package wiktoria.appdemo.rates.recommendation.similarity;

import javax.persistence.*;

@Entity
@Table(name = "similarity")
public class Similarity {

    public Similarity(){}

    public Similarity(int bookaid, int bookbid, float similar) {
        this.bookaid = bookaid;
        this.bookbid = bookbid;
        this.similar = similar;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "similarityid")
    private int similarityid;

    @Column(name = "bookaid")
    private int bookaid;

    @Column(name = "bookbid")
    private int bookbid;

    @Column(name = "similar")
    private float similar;

    public int getSimilarityid() {
        return similarityid;
    }

    public void setSimilarityid(int similarityid) {
        this.similarityid = similarityid;
    }

    public int getBookaid() {
        return bookaid;
    }

    public void setBookaid(int bookaid) {
        this.bookaid = bookaid;
    }

    public int getBookbid() {
        return bookbid;
    }

    public void setBookbid(int bookbid) {
        this.bookbid = bookbid;
    }

    public float getSimilar() {
        return similar;
    }

    public void setSimilar(float similar) {
        this.similar = similar;
    }
}