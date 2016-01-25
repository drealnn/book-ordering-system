package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import javax.tools.OptionChecker;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
    Name: Daniel Sledd
    Course: CNT 4714 – Spring 2016
    Assignment title: Program 1 – Event-driven Programming
    Date: Sunday January 24, 2016
*/


public class Controller {
    public Button processBtn;
    public Button confirmBtn;
    public Button viewBtn;
    public Button finishBtn;
    public Button newBtn;
    public Button exitBtn;
    public Label IDLabel;
    public Label quantityLabel;
    public Label infoLabel;
    public Label subtotalLabel;
    public TextField orderBox;
    public TextField infoBox;
    public TextField IDBox;
    public TextField quantityBox;
    public TextField subtotalBox;

    int currentOrderNumber = 1;
    int numberOfOrders;
    int bookID;

    ArrayList<Order> myOrders;


    public void processItem(ActionEvent actionEvent) {
        if (!orderBox.getText().equals("") && !IDBox.getText().equals("") && !quantityBox.getText().equals(""))
        {
            numberOfOrders = Integer.parseInt(orderBox.getText());
            int quantity = Integer.parseInt(quantityBox.getText());
            String ID = IDBox.getText();

            BookEntry book = BookDatabase.search(ID);

            if (myOrders == null)
            {
                myOrders = new ArrayList<>();
            }

            Alert alert = new Alert(Alert.AlertType.ERROR, "Book ID " + ID + " not found", ButtonType.OK);

            if (book != null) {
                myOrders.add(new Order(book, quantity));
                infoBox.setText(myOrders.get(myOrders.size() - 1).toString());
                processBtn.setDisable(true);
                confirmBtn.setDisable(false);
            }
            else
                alert.show();


        }
    }

    public void confirmItem(ActionEvent actionEvent) {
        orderBox.setDisable(true);
        subtotalLabel.setText("Order subtotal for " + myOrders.size() + " items:");
        subtotalBox.setText(String.valueOf(Order.getRunningTotal()));

        if (myOrders.size() < numberOfOrders)
        {
            IDLabel.setText("Enter Book ID for Item #" + (myOrders.size() + 1) + ":");
            quantityLabel.setText("Enter quantity for Item #" + (myOrders.size() + 1) + ":");
            infoLabel.setText(String.format("Item #%d info", myOrders.size() + 1));

            processBtn.setText(String.format("Process Item #%d", myOrders.size() + 1));
            confirmBtn.setText(String.format("Confirm Item #%d", myOrders.size() + 1));
            processBtn.setDisable(false);
        }

        IDBox.setText("");
        quantityBox.setText("");

        confirmBtn.setDisable(true);
        viewBtn.setDisable(false);
        finishBtn.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item #" + (myOrders.size()) + " accepted", ButtonType.YES);
        alert.show();
    }

    public void view(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, orderArrayToString(), ButtonType.OK);
        alert.setHeaderText("View Order");
        alert.setResizable(true);
        alert.getDialogPane().setPrefWidth(alert.getDialogPane().getWidth()*2);
        //alert.setContentText(orderString.toString());
        alert.show();

    }

    public void finishOrder(ActionEvent actionEvent) {
       StringBuilder orderString = new StringBuilder();
        // create new alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK );
        // get date
        Date date = new Date();
        DateFormat format1 = new SimpleDateFormat("MM/dd/yy hh:mm:ss a z");
        DateFormat format2 = new SimpleDateFormat("yyMMddhhmmss");
        orderString.append(String.format("%s\n\nNumber of line items:%d\n\nItem#/ID/Title/Price/Qty/Disc%%/Subtotal:\n\n%s\n\nOrder Subtotal:$%.2f\n\n" +
                "Tax Rate:\t6%%\n\nTax Amount:$%.2f\n\nOrder Total:$%.2f\n\nThanks for shopping!",
                format1.format(date), myOrders.size(), orderArrayToString(), Order.getRunningTotal(), Order.getRunningTotal()*.06, Order.getRunningTotal() + Order.getRunningTotal()*.06 ) );

        // print order array
        // print subtotal
        // print 6% tax
        // add tax and generate new total
        // show alert
        // call new order

        alert.setHeaderText("Order Receipt");
        alert.setResizable(true);
        alert.getDialogPane().setPrefWidth(alert.getDialogPane().getWidth()*2);
        alert.setContentText(orderString.toString());
        alert.show();

        // output transactions.txt
        OpenOption[] options = {StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND};
        Path file = Paths.get("transaction.txt");

        try {
            BufferedWriter writer = Files.newBufferedWriter(file, Charset.defaultCharset(), options);


            int i = 0;
            for (Order order : myOrders)
            {
                //String[] strArray = order.toString().split(" ");
                //System.out.println(strArray[0] + strArray[1]);
                writer.write(String.format("%s, %s, %s, %s, %s, %s, %s, %s\n",
                        format2.format(date), order.getOrderedBook().getBookID(), order.getOrderedBook().getBookTitle(),
                        order.getOrderedBook().getPrice(), order.getQuantity(), order.getDiscount(), order.getFinalPrice(), format1.format(date)));
            }

            writer.close();
        } catch (IOException e)
        {
            System.out.println("OOPS");
            e.printStackTrace();
        }

        newOrder(new ActionEvent());




    }

    public void newOrder(ActionEvent actionEvent) {
        Order.resetOrder();
        numberOfOrders = 0;
        myOrders = new ArrayList<>();

        processBtn.setDisable(false);
        confirmBtn.setDisable(true);
        viewBtn.setDisable(true);
        finishBtn.setDisable(true);
        orderBox.setDisable(false);

        orderBox.setText("");
        IDBox.setText("");
        quantityBox.setText("");
        infoBox.setText("");
        subtotalBox.setText("");

        IDLabel.setText("Enter Book ID for Item #" + (myOrders.size() + 1) + ":");
        quantityLabel.setText("Enter quantity for Item #" + (myOrders.size() + 1) + ":");
        infoLabel.setText(String.format("Item #%d info", myOrders.size() + 1));

        processBtn.setText(String.format("Process Item #%d", myOrders.size() + 1));
        confirmBtn.setText(String.format("Confirm Item #%d", myOrders.size() + 1));
        subtotalLabel.setText("Order subtotal for " + myOrders.size() + " items:");
    }

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }


    public String orderArrayToString()
    {
        StringBuilder orderString = new StringBuilder();
        int i = 1;
        for (Order order : myOrders)
        {
            orderString.append(String.format("%d. %s\n", i++, order.toString()));
        }

        return orderString.toString();
    }
}
