package wiktoria.appdemo.rates.favorite;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    public Favorite(int userid, int bookid) {
        this.userid = userid;
        this.bookid = bookid;
    }

    public Favorite(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "favid")
    private int favid;

    @Column(name = "userid")
    private int userid;

    @Column(name = "bookid")
    private int bookid;

    public int getFavid() {
        return favid;
    }

    public void setFavid(int favid) {
        this.favid = favid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
}
