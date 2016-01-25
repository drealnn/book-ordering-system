package sample;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Name: Daniel Sledd
    Course: CNT 4714 – Spring 2016
    Assignment title: Program 1 – Event-driven Programming
    Date: Sunday January 24, 2016
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
            /*
            String bookID = in.next();
            bookID = bookID.split(",")[0];
            String bookName = in.next();
            bookName = bookName.split(",")[0];
            */

            String entryString = in.nextLine();

            String[] myStrings = entryString.split(", ");
            String bookID = myStrings[0];
            String bookName = myStrings[1];
            float bookPrice = Float.parseFloat(myStrings[2]);

            //System.out.println(bookID + " " + bookName + " " + bookPrice);
            /*
            String bookID = in.next("^[0-9]+,");
            String bookName = in.next("\"[A-Za-z0-9 ]+\",");
            float bookPrice = Float.parseFloat( in.next("[0-9]*.?[0-9]+$") );
            */
            System.out.println("book added");
            bookArray.add(new BookEntry(bookID, bookName, bookPrice));


        }

    }

    public static ArrayList<BookEntry> getBooks()
    {
        return bookArray;
    }

    public static BookEntry search(String id)
    {
        for (BookEntry book : bookArray)
        {
            if (book.getBookID().equals(id))
                return book;
        }
        return null;
    }

    public static void printBooks()
    {
        for (int i = 0; i < bookArray.size(); i++)
        {
            System.out.println(bookArray.get(i).bookID + "-" + bookArray.get(i).bookTitle + "-" + bookArray.get(i).getPrice());

        }
    }
}
