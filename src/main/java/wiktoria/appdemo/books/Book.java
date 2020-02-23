package wiktoria.appdemo.books;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookid")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "pages")
    private int pages;

    @Column(name = "avrate")
    private float avrate;

    @Column(name = "csex")
    private String csex;

    @Column(name = "victimnr")
    private String victimnr;

    @Column(name = "motive")
    private String motive;

    @Column(name = "crimetype")
    private String crimetype;

    @Column(name = "crimescene")
    private String crimescene;

    @Column(name = "invexecutor")
    private String invexecutor;

    @Column(name = "dsex")
    private String dsex;

    @Column(name = "storybcg")
    private String storybcg;

    @Column(name = "vibe")
    private String vibe;

    @Lob
    @Column(name = "blobimage", columnDefinition = "MEDIUMBLOB")
    private byte[] imageblob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isIsfav() {
        return isfav;
    }

    public void setIsfav(boolean isfav) {
        this.isfav = isfav;
    }

    public boolean isIstoread() {
        return istoread;
    }

    public void setIstoread(boolean istoread) {
        this.istoread = istoread;
    }

    public float getAvrate() {
        return avrate;
    }

    public void setAvrate(float avrate) {
        this.avrate = avrate;
    }

    public String getCsex() {
        return csex;
    }

    public void setCsex(String csex) {
        this.csex = csex;
    }

    public String getVictimnr() {
        return victimnr;
    }

    public void setVictimnr(String victimnr) {
        this.victimnr = victimnr;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getCrimetype() {
        return crimetype;
    }

    public void setCrimetype(String crimetype) {
        this.crimetype = crimetype;
    }

    public String getCrimescene() {
        return crimescene;
    }

    public void setCrimescene(String crimescene) {
        this.crimescene = crimescene;
    }

    public String getInvexecutor() {
        return invexecutor;
    }

    public void setInvexecutor(String invexecutor) {
        this.invexecutor = invexecutor;
    }

    public String getDsex() {
        return dsex;
    }

    public void setDsex(String dsex) {
        this.dsex = dsex;
    }

    public String getStorybcg() {
        return storybcg;
    }

    public void setStorybcg(String storybcg) {
        this.storybcg = storybcg;
    }

    public String getVibe() {
        return vibe;
    }

    public void setVibe(String vibe) {
        this.vibe = vibe;
    }

    public int getRankplace() {
        return rankplace;
    }

    public void setRankplace(int rankplace) {
        this.rankplace = rankplace;
    }

    public int getChancetolikeit() {
        return chancetolikeit;
    }

    public void setChancetolikeit(int chancetolikeit) {
        this.chancetolikeit = chancetolikeit;
    }

    public int getUserrate() {
        return userrate;
    }

    public void setUserrate(int userrate) {
        this.userrate = userrate;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    public byte[] getImageblob() {
        return imageblob;
    }

    public void setImageblob(byte[] imageblob) {
        this.imageblob = imageblob;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTemppages() {
        return temppages;
    }

    public void setTemppages(String temppages) {
        this.temppages = temppages;
    }

    @Transient
    private boolean isfav = false;

    @Transient
    private boolean istoread = false;

    @Transient
    private int rankplace;

    @Transient
    private int chancetolikeit;

    @Transient
    private int userrate;

    @Transient
    private String coverimage;

    @Transient
    private MultipartFile file;

    @Transient
    private String temppages;
}
