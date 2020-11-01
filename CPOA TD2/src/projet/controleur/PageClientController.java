package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Client;
import projet.metier.Produit;

public class PageClientController {
	
	public static Client client;
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private TextField textFieldFiltreNom;
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
	
	public static Client getClient() {
		return client;
	}
	
	@FXML public void initialize() throws SQLException {
		loadData();
		buttonDelete.setDisable(true);
		buttonEdit.setDisable(true);
	}    
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addClient = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutclient.fxml"));
		Scene addClientscene = new Scene(addClient);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(addClientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        ObservableList<Client> Allpeople, Ligneselect;
        Allpeople = tableViewClient.getItems();
        Ligneselect = tableViewClient.getSelectionModel().getSelectedItems();
        
        for(Client cl : Ligneselect)
        {
            Allpeople.remove(cl);
            System.out.println(cl);
            dao.getClientDAO().delete(cl);
        }     
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		client = tableViewClient.getSelectionModel().getSelectedItem();
		Parent editCLient = FXMLLoader.load(getClass().getResource("/projet/FXML/editclient.fxml"));
		Scene editClientscene = new Scene(editCLient);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editClientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnClient = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnClientscene = new Scene(returnClient);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnClientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void loadData() {
        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        this.tableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.tableColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        this.tableColumnIdentifiant.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        this.tableColumnMdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        this.tableColumnAdrNum.setCellValueFactory(new PropertyValueFactory<>("adrnumero"));
        this.tableColumnAdrVoie.setCellValueFactory(new PropertyValueFactory<>("adrvoie"));
        this.tableColumnCodePostal.setCellValueFactory(new PropertyValueFactory<>("adrcodepostal"));
        this.tableColumnAdrVille.setCellValueFactory(new PropertyValueFactory<>("adrville"));
        this.tableColumnPays.setCellValueFactory(new PropertyValueFactory<>("adrpays"));
        try {
                    this.tableViewClient.getItems().addAll(dao.getClientDAO().findAll());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
	}
	
	@FXML public void clickOnTable() {
		buttonDelete.setDisable(false);
		buttonEdit.setDisable(false);
	}
	
	//Renvoie une liste des produits qui possedent un nom correspondant a la demande
	@FXML public ArrayList<Client> filtreNom() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String nom = textFieldFiltreNom.getText().trim().toLowerCase();
		ArrayList<Client> listeClient = new ArrayList<Client>();
		try {
			if (nom.equals("")) {
				listeClient.addAll(dao.getClientDAO().findAll());
			}
			else {
				for (Client client : dao.getClientDAO().findAll()) {
					if (client.getNom().toLowerCase().contains(nom) || client.getPrenom().toLowerCase().contains(nom)) {
						listeClient.add(client);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeClient;
	}
	
	//Rassemble toute les listes des donnees filtrees et fait un ET exclusif des donnees
	@FXML public void filtrageNom() {
		ArrayList<Client> clientNom = filtreNom();
		ObservableList<Client> listeClientSelect = FXCollections.observableArrayList();
		ObservableList<Client> listeClientSurplus = FXCollections.observableArrayList();
		ObservableList<Client> listeClienttMino = FXCollections.observableArrayList();

		for (Client client : clientNom) {
			if (clientNom.contains(client))
				listeClientSelect.add(client);
		}
		//On enleve de la tableView tout produit non present dans listeProdSelect mais present dans la tableView
		ObservableList<Client> trans1 = tableViewClient.getItems();
		for (Client client : trans1) {
			if (!listeClientSelect.contains(client))
				listeClientSurplus.add(client);
		}

		//On rajoute dans la tableView tout produit present dans listeProdSelect mais non present dans la tableView
		ObservableList<Client> trans2 = tableViewClient.getItems();
		for (Client client : listeClientSelect ) {
			if (!trans2.contains(client))
				listeClienttMino.add(client);
		}

		tableViewClient.getItems().removeAll(listeClientSurplus);
		tableViewClient.getItems().addAll(listeClienttMino);
	}
}
