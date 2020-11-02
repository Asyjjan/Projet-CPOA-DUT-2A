package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Commande;
import projet.metier.LigneCommande;

public class DeleteCommandeController {

	@FXML private ChoiceBox<Commande> choiceBoxCommande;
	@FXML private Button buttonValider;
	@FXML private Button buttonReturn;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Commande> commande= FXCollections.observableArrayList(dao.getCommandeDAO().findAll());
		choiceBoxCommande.setItems(commande);
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent addLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecommande.fxml"));
		Scene addLigneCommandescene = new Scene(addLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Page commande");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		Commande commande = choiceBoxCommande.getValue();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Suppresion d'une commande");
	      alert.setHeaderText("Êtes vous sur de vouloir supprimer cette commande ?");
	      
	      Optional<ButtonType> option = alert.showAndWait();
	      if (option.get() == null) {
	       } else if (option.get() == ButtonType.OK) {
	    	   dao.getCommandeDAO().delete(commande);
	    	   for(LigneCommande lc : dao.getLigneCommandeDAO().findAll()) {
	    		   if(lc.getIdcom() == commande.getIdcommande()) {
	    			   dao.getLigneCommandeDAO().delete(commande.getIdcommande(), lc.getIdprod());
	    		   }
	    	   }
	    	   clickOnReturn(e);
	       } else if (option.get() == ButtonType.CANCEL) {
	          clickOnReturn(e);
	       }
	}
}
