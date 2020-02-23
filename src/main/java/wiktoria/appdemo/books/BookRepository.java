package wiktoria.appdemo.books;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")												
public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findById(int bookid);
	List<Book> findAll( );
	List<Book> findAllByIdIn( List<Integer> bookIds);
}
											