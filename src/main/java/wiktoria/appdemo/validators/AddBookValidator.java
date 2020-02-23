package wiktoria.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.user.User;

import java.io.IOException;

public class AddBookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Book newBook = (Book) obj;

        try {
            String path = newBook.getFile().getOriginalFilename().split("\\.")[1].toLowerCase();
            if(!("jpg".equals(path) || "png".equals(path) || "jpeg".equals(path))){
                errors.rejectValue("file", "book.msg.file");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            errors.rejectValue("file", "book.msg.fileempty");
        }



        if(newBook.getTitle() == null || newBook.getTitle().isEmpty() || newBook.getTitle().length() > 100){
            errors.rejectValue("title", "book.msg.title");
        }

        if(newBook.getAuthor() == null || newBook.getAuthor().isEmpty() || newBook.getAuthor().length() > 100){
            errors.rejectValue("author", "book.msg.author");
        }

        try {
            newBook.setPages(Integer.parseInt(newBook.getTemppages()));
        } catch (NumberFormatException e){
            errors.rejectValue("temppages", "book.msg.pages");
        }

        try {
            newBook.setImageblob(newBook.getFile().getBytes());
        } catch (IOException e){}

    }
}
