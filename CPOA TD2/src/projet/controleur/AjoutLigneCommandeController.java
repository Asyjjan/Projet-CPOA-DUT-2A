package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class AjoutLigneCommandeController {

	@FXML private TextField textFieldQuantiteLigneCommande;
	@FXML private ChoiceBox<Commande> choiceBoxCommande;
	@FXML private ChoiceBox<Produit> choiceBoxProduit;
	@FXML private Label labelAffichage;
	@FXML private Button buttonValider;
	@FXML private Button buttonReturn;

	@FXML
	public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Commande> commande= FXCollections.observableArrayList(dao.getCommandeDAO().findAll());
		ObservableList<Produit> produit= FXCollections.observableArrayList(dao.getProduitDAO().findAll());
		choiceBoxCommande.setItems(commande);
		choiceBoxProduit.setItems(produit);
		buttonValider.setDisable(true);
	}

	@FXML
	public void majLabelAffichage(ActionEvent e) throws SQLException, IOException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String strquantite = textFieldQuantiteLigneCommande.getText().trim();
		
		int quantite = Integer.parseInt(strquantite);
		int idcom = choiceBoxCommande.getValue().getIdcommande();
		int idprod = choiceBoxProduit.getValue().getIdprod();

		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		labelAffichage.setText("La LigneCommande de quantite : " + quantite + " à été ajoutée");
		LigneCommande lignecom = new LigneCommande(idprod, quantite, dao.getProduitDAO().getById(idprod).getTarif());
		dao.getLigneCommandeDAO().create(idcom, lignecom);
		clickOnReturn(e);
	}

	@FXML
	public void keyReleasedProperty() {
		String quantite = textFieldQuantiteLigneCommande.getText();

		boolean isDisabled = (quantite.isEmpty() || quantite.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}

	@FXML
	public void clickOnReturn(ActionEvent e) throws IOException {
		Parent LigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene LigneCommandescene = new Scene(LigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(LigneCommandescene);
		window.centerOnScreen();
		window.setTitle("LigneCommande");
		window.show();
	}
}
