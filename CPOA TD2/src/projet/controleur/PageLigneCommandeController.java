package projet.controleur;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

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

public class PageLigneCommandeController {

	public static LigneCommande lignecom;

	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private TableView<LigneCommande> tableViewLigneCommande;
	@FXML private TableColumn<LigneCommande, Commande> tableColumnCommande;
	@FXML private TableColumn<LigneCommande, Produit> tableColumnProduit;
	@FXML private TableColumn<LigneCommande, Produit> tableColumnQuantite;
	@FXML private TableColumn<LigneCommande, Produit> tableColumnTarif;

	public static LigneCommande getLigneCommande() {
		return lignecom;
	}

	@FXML
	public void initialize() throws SQLException {
		loadData();
	}

	@FXML
	public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutlignecommande.fxml"));
		Scene addLigneCommandescene = new Scene(addLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("LigneCommande");
		window.show();
	}

	@FXML
	public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<LigneCommande> Allpeople, Ligneselect;
		Allpeople = tableViewLigneCommande.getItems();
		Ligneselect = tableViewLigneCommande.getSelectionModel().getSelectedItems();

		for (LigneCommande lignecommande : Ligneselect) {
			Allpeople.remove(lignecommande);
			System.out.println(lignecommande);
			int idcom = tableColumnCommande.getValue();
			int idprod = tableColumnProduit.getValue();
			dao.getLigneCommandeDAO().delete(idcom, idprod);
		}
	}

	@FXML
	public void clickOnEdit(ActionEvent e) throws IOException {
		lignecom = tableViewLigneCommande.getSelectionModel().getSelectedItem();
		Parent editLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/editlignecommande.fxml"));
		Scene editLigneCommandescene = new Scene(editLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(editLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("LigneCommande");
		window.show();
	}

	@FXML
	public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnLigneCommandescene = new Scene(returnLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(returnLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("LigneCommande");
		window.show();
	}

	@FXML
	public void loadData() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		this.tableColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
		this.tableColumnTarif.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
		this.tableColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		this.tableColumnTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		try {
			this.tableViewLigneCommande.getItems().addAll(dao.getLigneCommandeDAO().findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
