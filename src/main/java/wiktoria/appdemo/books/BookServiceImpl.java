package wiktoria.appdemo.books;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return setBooksImages(bookRepository.findAll());
    }

    @Override
    public Book findById(int bookid) {
        return bookRepository.findById(bookid);
    }

    @Override
    public List<Book> findAllByAvg() {
        return setBooksImages(bookRepository.findAll(Sort.by(Sort.Direction.DESC, "avrate")));
    }

    @Override
    public void deleteByBookid(int bookid) {
        bookRepository.deleteById(bookid);
    }

    @Override
    public List<Book> findAllByIdIn(List<Integer> bookIds) {
        return setBooksImages(bookRepository.findAllByIdIn(bookIds));
    }

    private List<Book> setBooksImages(List<Book> allBooks) {

        for (Book book : allBooks) {
            byte[] bytes = book.getImageblob();
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            try {
                String base64Encoded = new String(encodeBase64, "UTF-8");
                book.setCoverimage(base64Encoded);
            } catch (UnsupportedEncodingException e) {
            }
        }

        return allBooks;
    }
}
