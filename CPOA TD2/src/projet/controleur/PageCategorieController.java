package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Client;
import projet.metier.LigneCommande;

public class PageCategorieController {
	
	public static Categorie categ;
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private TableView<Categorie> tableViewCategorie;
	@FXML private TableColumn<Categorie, String> tableColumnTitre;
	@FXML private TableColumn<Categorie, String> tableColumnVisuel;
	
	public static Categorie getCategorie() {
		return categ;
	}
	
	@FXML public void initialize() throws SQLException {
		loadData();
		buttonDelete.setDisable(true);
		buttonEdit.setDisable(true);
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addCategorie = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutcategorie.fxml"));
		Scene addCategoriescene = new Scene(addCategorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(addCategoriescene);
		window.centerOnScreen();
		window.setTitle("Ajout d'une catégorie");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Categorie> Allpeople, Ligneselect;
		Allpeople = tableViewCategorie.getItems();
		Ligneselect = tableViewCategorie.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppresion d'une catégorie");
		alert.setHeaderText("Êtes vous sur de vouloir supprimer cette catégorie ?");

		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == null) {
		} else if (option.get() == ButtonType.OK) {
			for(Categorie categorie : Ligneselect)
			{
				Allpeople.remove(categorie);
				System.out.println(categorie);
				dao.getCategorieDAO().delete(categorie);
			}
		}
		else if (option.get() == ButtonType.CANCEL) {
		}
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		categ = tableViewCategorie.getSelectionModel().getSelectedItem();
		Parent editCategorie = FXMLLoader.load(getClass().getResource("/projet/FXML/editcategorie.fxml"));
		Scene editCategoriescene = new Scene(editCategorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editCategoriescene);
		window.centerOnScreen();
		window.setTitle("Modification d'une catégorie");
		window.show();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnCategorie = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnCategoriescene = new Scene(returnCategorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnCategoriescene);
		window.centerOnScreen();
		window.setTitle("Application de gestion commercial");
		window.show();
	}
	
	@FXML public void loadData() {
        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        this.tableColumnTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        this.tableColumnVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        try {
                    this.tableViewCategorie.getItems().addAll(dao.getCategorieDAO().findAll());
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
	
	@FXML public void clickOnTable() {
		buttonDelete.setDisable(false);
		buttonEdit.setDisable(false);
	}
}
