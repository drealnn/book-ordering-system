package sample;

/*
    Name: Daniel Sledd
    Course: CNT 4714 – Spring 2016
    Assignment title: Program 1 – Event-driven Programming
    Date: Sunday January 24, 2016
*/

public class Order {
    BookEntry orderedBook;
    float finalPrice;
    float discount;
    int quantity;
    int orderID;

    static int ID;
    static float runningTotal;


    public Order(BookEntry orderedBook, int quantity) {
        this.orderedBook = orderedBook;
        this.quantity = quantity;
        orderID = ++ID;

        calculateDiscount();
        calculateFinalPrice();
    }

    private void calculateDiscount()
    {
        if (quantity >= 1 && quantity <= 4)
            discount = 0;
        else if (quantity >=5 && quantity <= 9)
            discount = 0.1f;
        else if (quantity >=10 && quantity <= 14)
            discount = 0.15f;
        else if (quantity >=15)
            discount = 0.2f;

    }

    private void calculateFinalPrice()
    {
        finalPrice = orderedBook.getPrice() * quantity - orderedBook.getPrice()*quantity*discount;
        runningTotal += finalPrice;
    }

    public BookEntry getOrderedBook() {
        return orderedBook;
    }

    public void setOrderedBook(BookEntry orderedBook) {
        this.orderedBook = orderedBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getID() {
        return orderID;
    }

    public static float getRunningTotal() {return runningTotal; }

    public static void resetOrder()
    {
        ID = 0;
        runningTotal = 0;
    }

    public float getFinalPrice() {return finalPrice;}
    public float getDiscount() {return discount;}

    public String toString()
    {
        return (String.format("%s %s $%.2f %d %.2f%% $%.2f", orderedBook.getBookID(), orderedBook.getBookTitle(), orderedBook.getPrice(), quantity,  discount*100, finalPrice) );
    }

}
