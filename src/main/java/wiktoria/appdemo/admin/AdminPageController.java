package wiktoria.appdemo.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.books.BookService;
import wiktoria.appdemo.configuration.SortAndPaginate;
import wiktoria.appdemo.rates.rate.RatesService;
import wiktoria.appdemo.rates.recommendation.RecommendationController;
import wiktoria.appdemo.rates.recommendation.similarity.Similarity;
import wiktoria.appdemo.rates.recommendation.similarity.SimilarityService;
import wiktoria.appdemo.user.User;
import wiktoria.appdemo.user.UserService;
import wiktoria.appdemo.validators.AddBookValidator;
import javax.validation.Valid;

@Controller
public class AdminPageController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SimilarityService similarityService;

    @Autowired
    private RatesService ratesService;

    @Autowired
    RecommendationController recommendationController;

    @Autowired
    MessageSource messagesource;


    @GetMapping(value = "/admin")
    @Secured(value = {"ROLE_ADMIN"})
    public String openAdminMainPage() {
        return "admin/admin";
    }

    @GetMapping(value = "/admin/users/{page}")
    @Secured(value = {"ROLE_ADMIN"})
    public String openAdminAllUsersPage(Model model, @PathVariable("page") int page, @RequestParam("search") String search) {

        List<User> userList = getAllUsers(search);
        int size = userList.size();
        userList = SortAndPaginate.paginateUsers(userList, page);
        model.addAttribute("search", search);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil(size / 10.0));
        model.addAttribute("userList", userList);

        return "admin/users";
    }

    @GetMapping(value = "/admin/books/{page}/{sort}/{order}")
    @Secured(value = {"ROLE_ADMIN"})
    public String showBooksToAdmin(Model model, @PathVariable("page") int page, @PathVariable("sort") String sort,
                                   @PathVariable("order") String order, @RequestParam("search") String search) {
        List<Book> allBooks = bookService.findAll();
        List<Book> searchedBooksByAdmin = new ArrayList<>();

        for (Book book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(search.toLowerCase()) ||
                    book.getTitle().toLowerCase().contains(search.toLowerCase())) {
                searchedBooksByAdmin.add(book);
            }
        }

        searchedBooksByAdmin = SortAndPaginate.setBookSortOrder(searchedBooksByAdmin, order, sort);
        model.addAttribute("search", search);
        SortAndPaginate.setBookModelAttributes(searchedBooksByAdmin, page, model);

        return "admin/books";
    }

    @GetMapping(value = "/admin/addbook")
    @Secured(value = {"ROLE_ADMIN"})
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "admin/addbook";
    }

    @PostMapping(value = "/admin/addnewbook")
    @Secured(value = "ROLE_ADMIN")
    public String addBookAction(@Valid @ModelAttribute("book") Book newBook, BindingResult result, Model model, Locale locale) {

        new AddBookValidator().validate(newBook, result);

        if (result.hasErrors()) {
            return "admin/addbook";
        } else {
            List<Book> allBooks = bookService.findAll();
            newBook.setTitle("\"" + newBook.getTitle() + "\"");
            newBook = bookService.save(newBook);
            List<Similarity> newSimilarities = new ArrayList<>();

            for(Book book : allBooks){
                Similarity singleSimilarity = recommendationController.countSingleSimilarity(newBook, book);
                newSimilarities.add(singleSimilarity);

            }
            similarityService.saveAll(newSimilarities);
            model.addAttribute("message", messagesource.getMessage("book.msg.added", null, locale));
            return "admin/admin";
        }
    }

    private List<User> getAllUsers(String search) {
        List<User> usersList = userService.findAll();
        List<User> usersToShow = new ArrayList<>();

        for (User user : usersList) {
            if (user.getLastname().toLowerCase().contains(search.toLowerCase()) ||
                    user.getName().toLowerCase().contains(search.toLowerCase()) ||
                    user.getEmail().toLowerCase().contains(search.toLowerCase())) {
                int numerRoli = user.getRoles().iterator().next().getId();
                user.setNrRoli(numerRoli);
                usersToShow.add(user);
            }
        }

        return usersToShow;
    }

    @GetMapping(value = "/delete/{id}/{page}")
    @Secured(value = "ROLE_ADMIN")
    public String deleteUser(Model model, @PathVariable("id") int id, @PathVariable("page") int page,
                             @RequestParam("search") String search) {
        adminService.deleteUserById(id);
        ratesService.deleteAllByUserid(id);
        openAdminAllUsersPage(model, page, search);
        return "admin/users";
    }

    @GetMapping(value = "/deletebook/{bookid}/{page}/{sort}/{order}")
    @Secured(value = "ROLE_ADMIN")
    public String deleteBook(Model model, @PathVariable("bookid") int bookid, @PathVariable("page") int page,
                             @PathVariable("sort") String sort, @PathVariable("order") String order,
                             @RequestParam("search") String search, Locale locale){
        bookService.deleteByBookid(bookid);
        similarityService.deleteAllByBookid(bookid);
        model.addAttribute("message", messagesource.getMessage("book.msg.deleted", null, locale));
        return showBooksToAdmin(model, page, sort, order, search);
    }
}
