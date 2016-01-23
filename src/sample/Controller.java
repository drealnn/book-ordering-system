package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    int currentOrderNumber = 1;
    int numberOfOrders;
    int bookID;



    public void processItem(ActionEvent actionEvent) {
    }

    public void confirmItem(ActionEvent actionEvent) {
    }

    public void view(ActionEvent actionEvent) {
    }

    public void finishOrder(ActionEvent actionEvent) {
    }

    public void newOrder(ActionEvent actionEvent) {
    }

    public void exitApp(ActionEvent actionEvent) {

    }

    public void updateOrderItems(Event event) {
        infoBox.setText(orderBox.getText());
    }
}
