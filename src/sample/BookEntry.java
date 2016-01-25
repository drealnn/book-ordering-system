package sample;

/*
    Name: Daniel Sledd
    Course: CNT 4714 – Spring 2016
    Assignment title: Program 1 – Event-driven Programming
    Date: Sunday January 24, 2016
*/

public class BookEntry {
    String bookID;
    String bookTitle;
    float price;

    public BookEntry(String bookID, String bookTitle, float price) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.price = price;
    }

    public BookEntry() {
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
