package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import java.util.ArrayList;

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
    }

    public void view(ActionEvent actionEvent) {
        StringBuilder orderString = new StringBuilder();
        int i = 1;
        for (Order order : myOrders)
        {
            orderString.append(String.format("%d. %s\n", i++, order.toString()));
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, orderString.toString(), ButtonType.OK);
        alert.setHeaderText("View Order");
        alert.setResizable(true);
        alert.getDialogPane().setPrefWidth(alert.getDialogPane().getWidth()*2);
        alert.setContentText(orderString.toString());
        alert.show();

    }

    public void finishOrder(ActionEvent actionEvent) {
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

    }


}
