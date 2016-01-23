package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sledd on 1/23/2016.
 */
public class BookDatabase {

    static ArrayList<BookEntry> bookArray;

    public static void generate(String fileName)
    {
        bookArray = new ArrayList<>();
        Scanner in;
        try {
            in = new Scanner(new File(fileName));
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            return;
        }

        while (in.hasNext())
        {

            String bookID = in.next();
            bookID = bookID.split(",")[0];
            String bookName = in.next();
            bookName = bookName.split(",")[0];
            /*
            String bookID = in.next("^[0-9]+");
            String bookName = in.next("\"[A-Za-z0-9 ]+\"");
            float bookPrice = Float.parseFloat( in.next("[0-9]*.?[0-9]+$") );
            */
            bookArray.add(new BookEntry(bookID, bookName, bookPrice));


        }

    }

    public static ArrayList<BookEntry> getBooks()
    {
        return bookArray;
    }

    public static void printBooks()
    {
        for (int i = 0; i < bookArray.size(); i++)
        {
            System.out.println(bookArray.get(i).bookID + "-" + bookArray.get(i).bookTitle + "-" + bookArray.get(i).getPrice());

        }
    }
}
