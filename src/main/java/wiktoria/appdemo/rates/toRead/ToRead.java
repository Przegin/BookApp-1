package wiktoria.appdemo.rates.toRead;

import javax.persistence.*;

@Entity
@Table(name = "toread")
public class ToRead {

    public ToRead(int userid, int bookid) {
        this.userid = userid;
        this.bookid = bookid;
    }

    public ToRead() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "toreadid")
    private int toreadid;

    @Column(name = "userid")
    private int userid;

    @Column(name = "bookid")
    private int bookid;

    public int getToreadid() {
        return toreadid;
    }

    public void setToreadid(int toreadid) {
        this.toreadid = toreadid;
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
