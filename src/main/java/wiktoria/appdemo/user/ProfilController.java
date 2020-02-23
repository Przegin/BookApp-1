package wiktoria.appdemo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import wiktoria.appdemo.books.BookService;
import wiktoria.appdemo.configuration.BooksConfigurator;
import wiktoria.appdemo.configuration.SortAndPaginate;
import wiktoria.appdemo.rates.favorite.Favorite;
import wiktoria.appdemo.rates.favorite.FavoritesService;
import wiktoria.appdemo.rates.toRead.ToRead;
import wiktoria.appdemo.rates.toRead.ToReadService;
import wiktoria.appdemo.utilities.UserUtilities;
import wiktoria.appdemo.validators.ChangePasswordValidator;
import wiktoria.appdemo.validators.EditUserProfileValidator;
import wiktoria.appdemo.books.Book;

@Controller
public class ProfilController {

	private UserService userService;
	private BookService bookService;
	private FavoritesService favoritesService;
	private ToReadService toReadService;
	private BooksConfigurator booksConfigurator;
	private MessageSource messagesource;

	public ProfilController(UserService userService, BookService bookService, FavoritesService favoritesService,
							ToReadService toReadService, BooksConfigurator booksConfigurator, MessageSource messagesource) {
		this.userService = userService;
		this.bookService = bookService;
		this.favoritesService = favoritesService;
		this.toReadService = toReadService;
		this.booksConfigurator = booksConfigurator;
		this.messagesource = messagesource;
	}

	@GetMapping(value = "/profil")
	public String showUserProfilePage(Model model) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		int nrRoli = user.getRoles().iterator().next().getId();
		user.setNrRoli(nrRoli);
		model.addAttribute("user", user);
		return "profil";
	}

	@GetMapping(value = "/editpassword")
	public String editUserPassword(Model model) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		model.addAttribute("user", user);
		return "editpassword";
	}

	@PostMapping(value = "/updatepass")
	public String changeUSerPassword(User user, BindingResult result, Model model, Locale locale) {
		new ChangePasswordValidator().validate(user, result);
		new ChangePasswordValidator().checkPasswords(user.getNewPassword(), result);

		if (result.hasErrors()) {
		} else {
			userService.updateUserPassword(user.getNewPassword(), user.getEmail());
			model.addAttribute("message", messagesource.getMessage("passwordChange.success", null, locale));
		}

		return "editpassword";
	}

	@GetMapping(value = "/editprofile")
	public String changeUserData(Model model) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		model.addAttribute("user", user);

		return "editprofile";
	}

	@PostMapping(value = "/updateprofil")
	public String changeUserDataAction(User user, BindingResult result, Model model, Locale locale) {
		String returnPage;
		new EditUserProfileValidator().validate(user, result);

		if (result.hasErrors()) {
			returnPage = "editprofile";
		} else {
			userService.updateUserProfile(user.getName(), user.getLastname(), user.getEmail(), user.getId());
			model.addAttribute("message", messagesource.getMessage("profilEdit.success", null, locale));
			returnPage = "afteredit";
		}

		return returnPage;
	}

	@GetMapping(value = "/books/{page}/{sort}/{order}")
	public String showProfil(Model model, @PathVariable("page") int page, @PathVariable("sort") String sort,
                             @PathVariable("order") String order, @RequestParam("search") String search) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		List<Book> searchedBooks = new ArrayList<>();
		List<Book> allBooks = bookService.findAll();

        for(Book book : allBooks){
            if(book.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(search.toLowerCase())){
                searchedBooks.add(book);
            }
        }
        model.addAttribute("search", search);
		searchedBooks = booksConfigurator.setParameters(searchedBooks, user.getId());
		searchedBooks = SortAndPaginate.setBookSortOrder(searchedBooks, order, sort);
		SortAndPaginate.setBookModelAttributes(searchedBooks, page, model);

		return "books";
	}

	@GetMapping(value = "/favorites/{page}/{sort}/{order}")
	public String showFavorites(Model model, @PathVariable("page") int page, @PathVariable("sort") String sort,
								@PathVariable("order") String order, @RequestParam("search") String search) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		List<Favorite> allFavorites = favoritesService.findAllByUserid(user.getId());
		List<Integer> bookIds = new ArrayList<>();
		allFavorites.stream().forEach(favorite -> bookIds.add(favorite.getBookid()));
		List<Book> favBooks = bookService.findAllByIdIn(bookIds);

		for (Book book: favBooks) {
			book.setIsfav(true);
		}
		favBooks = SortAndPaginate.setBookSortOrder(favBooks, order, sort);
		SortAndPaginate.setBookModelAttributes(favBooks, page, model);
		model.addAttribute("search", search);

		return "favbooks";
	}

	@GetMapping(value = "/bookstoread/{page}/{sort}/{order}")
	public String showToRead(Model model, @PathVariable("page") int page, @PathVariable("sort") String sort,
							 @PathVariable("order") String order, @RequestParam("search") String search) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		List<ToRead> allToRead = toReadService.findAllByUserid(user.getId());
		List<Integer> bookIds = new ArrayList<>();
		allToRead.stream().forEach(toRead -> bookIds.add(toRead.getBookid()));
		List<Book> toReadBooks = bookService.findAllByIdIn(bookIds);

		for (Book book: toReadBooks) {
			book.setIstoread(true);
		}
		toReadBooks = SortAndPaginate.setBookSortOrder(toReadBooks, order, sort);
		SortAndPaginate.setBookModelAttributes(toReadBooks, page, model);
		model.addAttribute("search", search);

		return "toreadbooks";
	}

	@GetMapping(value = "/globalranking/{page}")
	public String globalRanking(Model model, @PathVariable("page") int page) {
		User user = userService.findUserByEmail(UserUtilities.getLoggedUser());
		List<Book> allBooks = bookService.findAllByAvg();
		List<Book> toRank = new ArrayList<>();
		int nr = 1;

		for(Book book :allBooks){
			if(book.getAvrate() == 0){
				break;
			}
			book.setRankplace(nr);
			toRank.add(book);
			nr++;
		}

		toRank = booksConfigurator.setParameters(toRank, user.getId());
		SortAndPaginate.setBookModelAttributes(toRank, page, model);
		return "globalrank";
	}
}