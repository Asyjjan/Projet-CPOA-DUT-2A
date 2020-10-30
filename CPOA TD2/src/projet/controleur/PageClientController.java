package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;
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
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Client;

public class PageClientController {
	
	public static Client client;
	
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
	
	public static Client getClient() {
		return client;
	}
	
	@FXML public void initialize() throws SQLException {
		loadData();
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
}
