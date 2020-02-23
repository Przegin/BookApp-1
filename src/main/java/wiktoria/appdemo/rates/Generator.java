package wiktoria.appdemo.rates;

import wiktoria.appdemo.books.Book;
import wiktoria.appdemo.rates.rate.Rate;
import wiktoria.appdemo.user.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

public class Generator {

    public static ByteArrayInputStream ranking(List<Rate> rates, User user) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{2, 6, 2});
            table.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle("Ranking");
            Paragraph paragraph = new Paragraph();

            paragraph.add(new Paragraph("	Ranking uzytkownika " + user.getName() + " " + user.getLastname(), fontBold));
            paragraph.add(new Paragraph(" ", font));
            paragraph.add(new Paragraph(" ", font));
            paragraph.add(new Paragraph(" ", font));
            document.add(paragraph);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Miejsce", fontBold));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Tytul", fontBold));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Ocena", fontBold));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            int number = 1;
            for (Rate rate : rates) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(Integer.toString(number)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
        
                cell = new PdfPCell(new Phrase(rate.getTitle()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(rate.getValue())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                number++;
            }

            document.add(table);
            Paragraph paragraph1 = new Paragraph();
            paragraph1.add(new Paragraph(" ", font));
            paragraph1.add(new Paragraph(" ", font));
            paragraph1.add(new Paragraph(" ", font));
            paragraph1.add(new Paragraph("Ranking wygenerowano dnia " + LocalDate.now(), fontBold));

            document.add(paragraph1);
            document.close();

        } catch (DocumentException ex) {
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream booksToRead(List<Book> booksToRead) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{6, 6});
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle("toRead");

            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph("	Ksiazki do przeczytania: ", fontBold));
            paragraph.add(new Paragraph(" ", font));
            paragraph.add(new Paragraph(" ", font));
            document.add(paragraph);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Tytul", fontBold));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Autor", fontBold));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Book book : booksToRead) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(book.getTitle()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(book.getAuthor()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            document.add(table);
            document.close();
        } catch (DocumentException ex) {
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}