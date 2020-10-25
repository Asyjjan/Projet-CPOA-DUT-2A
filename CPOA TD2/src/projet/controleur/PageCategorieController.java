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
import projet.metier.Categorie;
import projet.metier.Client;

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
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addCategorie = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutcategorie.fxml"));
		Scene addCategoriescene = new Scene(addCategorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(addCategoriescene);
		window.centerOnScreen();
		window.setTitle("Categorie");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        ObservableList<Categorie> Allpeople, Ligneselect;
        Allpeople = tableViewCategorie.getItems();
        Ligneselect = tableViewCategorie.getSelectionModel().getSelectedItems();
        
        for(Categorie categorie : Ligneselect)
        {
            Allpeople.remove(categorie);
            System.out.println(categorie);
            dao.getCategorieDAO().delete(categorie);
        }     
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		Parent editCategorie = FXMLLoader.load(getClass().getResource("/projet/FXML/editcategorie.fxml"));
		Scene editCategoriescene = new Scene(editCategorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editCategoriescene);
		window.centerOnScreen();
		window.setTitle("Categorie");
		window.show();
		categ = tableViewCategorie.getSelectionModel().getSelectedItem();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnCategorie = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnCategoriescene = new Scene(returnCategorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnCategoriescene);
		window.centerOnScreen();
		window.setTitle("Categorie");
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
}
