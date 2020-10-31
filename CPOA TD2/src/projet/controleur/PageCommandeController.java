package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Client;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class PageCommandeController {
	
	public static Commande commande;
	public static LigneCommande lignecommande;
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private ChoiceBox<Commande> choiceBoxCommande;
	@FXML private TableView<LigneCommande> tableViewLigneCommande;
	@FXML private TableColumn<LigneCommande, String> tableColumnProduit;
	@FXML private TableColumn<LigneCommande, Integer> tableColumnQuantite;
	@FXML private TableColumn<LigneCommande, Integer> tableColumnTarif;
	
	public static Commande getCommande() {
		return commande;
	}
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Commande> commande= FXCollections.observableArrayList(dao.getCommandeDAO().findAll());
		choiceBoxCommande.setItems(commande);
		loadData();
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutlignecommande.fxml"));
		Scene addLigneCommandescene = new Scene(addLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Commande");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        ObservableList<LigneCommande> Allpeople, Ligneselect;
        Allpeople = tableViewLigneCommande.getItems();
        Ligneselect = tableViewLigneCommande.getSelectionModel().getSelectedItems();
        
        for(LigneCommande lignecom : Ligneselect)
        {
            Allpeople.remove(lignecom);
            System.out.println(lignecom);
            dao.getLigneCommandeDAO().delete(choiceBoxCommande.getValue().getIdcommande(), tableViewLigneCommande.getSelectionModel().getSelectedItem().getIdprod());
        }     
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		lignecommande = tableViewLigneCommande.getSelectionModel().getSelectedItem();
		Parent editCLient = FXMLLoader.load(getClass().getResource("/projet/FXML/editlignecommande.fxml"));
		Scene editClientscene = new Scene(editCLient);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editClientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnClient = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecommande.fxml"));
		Scene returnClientscene = new Scene(returnClient);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnClientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML
	public void loadData() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		this.tableColumnProduit.setCellValueFactory(new Callback<CellDataFeatures<LigneCommande, String>, ObservableValue<String>>() {
            public SimpleObjectProperty<String> call(CellDataFeatures<LigneCommande, String> p) {
                try {
                    return new SimpleObjectProperty<String>(dao.getProduitDAO().getById((p.getValue()).getIdprod()).getNom() );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
		this.tableColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		this.tableColumnTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		try {
			this.tableViewLigneCommande.getItems().addAll(dao.getLigneCommandeDAO().findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
