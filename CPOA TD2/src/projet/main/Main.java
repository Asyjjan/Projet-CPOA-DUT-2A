package projet.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

import javafx.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException{
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Application de gestion commercial");
			primaryStage.show();
			} catch (Exception e) {
			e.printStackTrace();
			}
	}
}