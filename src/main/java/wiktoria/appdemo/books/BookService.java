package wiktoria.appdemo.books;

import java.util.List;

public interface BookService {

	Book save(Book book);
	List<Book> findAll();
	Book findById(int bookid);
	List<Book> findAllByIdIn( List<Integer> bookIds);
	List<Book> findAllByAvg();
	void deleteByBookid(int bookid);
}

