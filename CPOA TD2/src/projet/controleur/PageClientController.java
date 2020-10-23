package projet.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projet.metier.Client;

public class PageClientController {
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private TableView<Client> tableViewClient;
	@FXML private TableColumn<Client, String> tableColumnNom;
	@FXML private TableColumn<Client, String> tableColumnPrenom;
	@FXML private TableColumn<Client, String> tableColumnIdentifiant;
	@FXML private TableColumn<Client, String> tableColumnMdp;
	@FXML private TableColumn<Client, String> tableColumnAdrNum;
	@FXML private TableColumn<Client, String> tableColumnAdrVoie;
	@FXML private TableColumn<Client, String> tableColumnCodePostal;
	@FXML private TableColumn<Client, String> tableColumnAdrVille;
	@FXML private TableColumn<Client, String> tableColumnPays;
	
	ObservableList<Client> tabClient = FXCollections.observableArrayList();
	
	@FXML public void initialize() {
		loadData();
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutclient.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException {
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/editclient.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void loadData() {
		this.nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.tarifProduit.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		this.visuelProduit.setCellValueFactory(new PropertyValueFactory<>("visuel"));
		try {
		            this.tabClient.getItems().addAll(produitDAO.findAll());
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	}
}
