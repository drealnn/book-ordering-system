package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    Name: Daniel Sledd
    Course: CNT 4714 – Spring 2016
    Assignment title: Program 1 – Event-driven Programming
    Date: Sunday January 24, 2016
*/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Book Store");
        primaryStage.setScene(new Scene(root, 722, 206));
        primaryStage.show();
    }


    public static void main(String[] args) {
        BookDatabase.generate("inventory.txt");
        //BookDatabase.printBooks();
        launch(args);
    }
}
