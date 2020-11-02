package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	public static LigneCommande lignecommande;
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private Button buttonCommande;
	@FXML private TextField textFieldFiltreCommande;
	@FXML private TableView<LigneCommande> tableViewLigneCommande;
	@FXML private TableColumn<LigneCommande, Integer> tableColumnCommande;
	@FXML private TableColumn<LigneCommande, String> tableColumnProduit;
	@FXML private TableColumn<LigneCommande, Integer> tableColumnQuantite;
	@FXML private TableColumn<LigneCommande, Integer> tableColumnTarif;
	
	public static LigneCommande getLigneCommande() {
		return lignecommande;
	}
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		loadData();
		buttonDelete.setDisable(true);
		buttonEdit.setDisable(true);
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutlignecommande.fxml"));
		Scene addLigneCommandescene = new Scene(addLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Ajout d'une ligne commande");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        ObservableList<LigneCommande> Allpeople, Ligneselect;
        Allpeople = tableViewLigneCommande.getItems();
        Ligneselect = tableViewLigneCommande.getSelectionModel().getSelectedItems();
        
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppresion d'un client");
		alert.setHeaderText("Êtes vous sur de vouloir supprimer ce client ?");

		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == null) {
		} else if (option.get() == ButtonType.OK) {
	        for(LigneCommande lignecom : Ligneselect)
	        {
	            Allpeople.remove(lignecom);
	            System.out.println(lignecom);
	            dao.getLigneCommandeDAO().delete(tableViewLigneCommande.getSelectionModel().getSelectedItem().getIdcom(), tableViewLigneCommande.getSelectionModel().getSelectedItem().getIdprod());
	        }    
		}
		else if (option.get() == ButtonType.CANCEL) {
		}   
	}
	
	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		lignecommande = tableViewLigneCommande.getSelectionModel().getSelectedItem();
		Parent editLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/editlignecommande.fxml"));
		Scene editLigneCommandescene = new Scene(editLigneCommande);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Modification d'une ligne commande");
		window.show();
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnClient = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnClientscene = new Scene(returnClient);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnClientscene);
		window.centerOnScreen();
		window.setTitle("Application de gestion commercial");
		window.show();
	}
	
	@FXML
	public void loadData() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		this.tableColumnCommande.setCellValueFactory(new PropertyValueFactory<>("idcom"));
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
	
	@FXML public void clickOnTable() {
		LigneCommande commande = tableViewLigneCommande.getSelectionModel().getSelectedItem();
		if(commande == null) {
			
		}
		else {
			buttonDelete.setDisable(false);
			buttonEdit.setDisable(false);
		}
	}
	
	@FXML public ArrayList<LigneCommande> filtreLigneCommande() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String lignecom = textFieldFiltreCommande.getText().trim().toLowerCase();
		ArrayList<LigneCommande> listecom = new ArrayList<LigneCommande>();
		try {
			if (lignecom.equals("")) {
				listecom.addAll(dao.getLigneCommandeDAO().findAll());
			}
			else {
				for (LigneCommande lc : dao.getLigneCommandeDAO().findAll()) {
					if (String.valueOf(lc.getIdcom()).toLowerCase().contains(lignecom)) {
						listecom.add(lc);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listecom;
	}
	
	@FXML public void filtrageLigneCommande() {
		ArrayList<LigneCommande> lignecommandeID = filtreLigneCommande();
		ObservableList<LigneCommande> listeLigneCommandeSelect = FXCollections.observableArrayList();
		ObservableList<LigneCommande> listeLigneCommandeSurplus = FXCollections.observableArrayList();
		ObservableList<LigneCommande> listeLigneCommandetMino = FXCollections.observableArrayList();

		for (LigneCommande lc: lignecommandeID) {
			if (lignecommandeID.contains(lc))
				listeLigneCommandeSelect.add(lc);
		}
		//On enleve de la tableView tout produit non present dans listeProdSelect mais present dans la tableView
		ObservableList<LigneCommande> trans1 = tableViewLigneCommande.getItems();
		for (LigneCommande lc : trans1) {
			if (!listeLigneCommandeSelect.contains(lc))
				listeLigneCommandeSurplus.add(lc);
		}

		//On rajoute dans la tableView tout produit present dans listeProdSelect mais non present dans la tableView
		ObservableList<LigneCommande> trans2 = tableViewLigneCommande.getItems();
		for (LigneCommande lc : listeLigneCommandeSelect ) {
			if (!trans2.contains(lc))
				listeLigneCommandetMino.add(lc);
		}

		tableViewLigneCommande.getItems().removeAll(listeLigneCommandeSurplus);
		tableViewLigneCommande.getItems().addAll(listeLigneCommandetMino);
	}
	
	@FXML public void clickForCommand(ActionEvent e) throws IOException {
		Parent Commande = FXMLLoader.load(getClass().getResource("/projet/FXML/commande.fxml"));
		Scene Commandescene = new Scene(Commande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(Commandescene);
		window.centerOnScreen();
		window.setTitle("Commande");
		window.show();
	}
}
