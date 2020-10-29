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
import projet.metier.Produit;

public class PageProduitController {
	
	public static Produit produit;
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private TableView<Produit> tableViewProduit;
	@FXML private TableColumn<Produit, String> tableColumnNom;
	@FXML private TableColumn<Produit, String> tableColumnDesc;
	@FXML private TableColumn<Produit, String> tableColumnTarif;
	@FXML private TableColumn<Produit, String> tableColumnVisuel;
	@FXML private TableColumn<Produit, String> tableColumnCategorie;
	
	public static Produit getProduit() {
		return produit;
	}
	
	@FXML public void initialize() throws SQLException {
		loadData();
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addProduit = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutproduit.fxml"));
		Scene addProduitscene = new Scene(addProduit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(addProduitscene);
		window.centerOnScreen();
		window.setTitle("Produit");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        ObservableList<Produit> Allpeople, Ligneselect;
        Allpeople = tableViewProduit.getItems();
        Ligneselect = tableViewProduit.getSelectionModel().getSelectedItems();
        
        for(Produit prod : Ligneselect)
        {
            Allpeople.remove(prod);
            System.out.println(prod);
            dao.getProduitDAO().delete(prod);
        }     
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		produit = tableViewProduit.getSelectionModel().getSelectedItem();
		Parent editProduit = FXMLLoader.load(getClass().getResource("/projet/FXML/editproduit.fxml"));
		Scene editProduitscene = new Scene(editProduit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editProduitscene);
		window.centerOnScreen();
		window.setTitle("Produit");
		window.show();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnProduit = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnProduitscene = new Scene(returnProduit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnProduitscene);
		window.centerOnScreen();
		window.setTitle("Produit");
		window.show();
	}
	
	@FXML public void loadData() {
        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        this.tableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.tableColumnTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        this.tableColumnVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        this.tableColumnCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        try {
                    this.tableViewProduit.getItems().addAll(dao.getProduitDAO().findAll());
                } catch (Exception e) {
                    e.printStackTrace();
                }
	}
}

