package wiktoria.appdemo.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity                                                    //podajemy, że jest to encja
@Table(name = "user")                                    //jakiej kolumny w bazie encja dotyczy
public class User {                                        //modyfikacja, zapisywanie, pobieranie danych, nie logowanie

    @Id                                                    //składowe muszą odpowiadać temu, co jest w tablicy w bazie
    @GeneratedValue(strategy = GenerationType.AUTO)        //generowanie aut., bo to klucz główny
    @Column(name = "userid")                            //jakiej kolumny dotyczy, dla każdego pola
    private int id;

    @Column(name = "email")
    @NotNull                                            //określenie, że nie może być puste pole
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "lastname")
    @NotNull
    private String lastname;

    @Column(name = "active")
    @NotNull
    private int active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //ustawiamy tablicę, która zbiera z dwóch, user i role, żeby odczytać rolę użytnkownika
    @JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Set<Role> roles;

    @Transient
    //przy update/insert pomijana przez Hibernate, gdy nie ma takiego pola w bazie
    private String operacja;

    @Transient
    private int nrRoli;

    @Transient
    private String newPassword;

    public String getOperacja() {
        return operacja;
    }

    public void setOperacja(String operacja) {
        this.operacja = operacja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getNrRoli() {
        return nrRoli;
    }

    public void setNrRoli(int nrRoli) {
        this.nrRoli = nrRoli;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
