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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class PageProduitController {

	public static Produit produit;

	@FXML private Button buttonAdd;
	@FXML private Button buttonDelete;
	@FXML private Button buttonEdit;
	@FXML private Button buttonReturn;
	@FXML private TextField textFieldFiltreNom;
	@FXML private TextField textFieldFiltreTarif;
	@FXML private TextField textFieldFiltreCategorie;
	@FXML private TableView<Produit> tableViewProduit;
	@FXML private TableColumn<Produit, String> tableColumnNom;
	@FXML private TableColumn<Produit, String> tableColumnDesc;
	@FXML private TableColumn<Produit, String> tableColumnTarif;
	@FXML private TableColumn<Produit, String> tableColumnVisuel;
	@FXML private TableColumn<Produit, String> tableColumnCategorie;
	@FXML private TableColumn<Produit, Integer> tableColumnQuantite;

	public static Produit getProduit() {
		return produit;
	}

	@FXML public void initialize() throws SQLException {
		loadData();
		buttonDelete.setDisable(true);
		buttonEdit.setDisable(true);
	}

	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addProduit = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutproduit.fxml"));
		Scene addProduitscene = new Scene(addProduit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(addProduitscene);
		window.centerOnScreen();
		window.setTitle("Ajout d'un produit");
		window.show();
	}

	@FXML public void clickOnDelete(ActionEvent e) throws IOException, SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Produit> Allpeople, Ligneselect;
		Allpeople = tableViewProduit.getItems();
		Ligneselect = tableViewProduit.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppresion d'un client");
		alert.setHeaderText("Êtes vous sur de vouloir supprimer ce client ?");

		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == null) {
		} else if (option.get() == ButtonType.OK) {
			for(Produit prod : Ligneselect)
			{
				Allpeople.remove(prod);
				System.out.println(prod);
				dao.getProduitDAO().delete(prod);
			}  
		}
		else if (option.get() == ButtonType.CANCEL) {
		}       
	}

	@FXML public void clickOnEdit(ActionEvent e) throws IOException {
		produit = tableViewProduit.getSelectionModel().getSelectedItem();
		Parent editProduit = FXMLLoader.load(getClass().getResource("/projet/FXML/editproduit.fxml"));
		Scene editProduitscene = new Scene(editProduit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(editProduitscene);
		window.centerOnScreen();
		window.setTitle("Modification d'un produit");
		window.show();
	}

	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnProduit = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnProduitscene = new Scene(returnProduit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(returnProduitscene);
		window.centerOnScreen();
		window.setTitle("Application de gestion commercial");
		window.show();
	}

	@FXML public void loadData() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		this.tableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		this.tableColumnTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		this.tableColumnVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
		this.tableColumnCategorie.setCellValueFactory(new Callback<CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            public SimpleObjectProperty<String> call(CellDataFeatures<Produit, String> p) {
                try {
                    return new SimpleObjectProperty<String>(dao.getCategorieDAO().getById((p.getValue()).getIdcateg()).getTitre() );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
		this.tableColumnQuantite.setCellValueFactory(new Callback<CellDataFeatures<Produit, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Produit, Integer> p) {
                int quantite = 0;
                int idprod = p.getValue().getIdprod();
                try {
                    for (LigneCommande lc : dao.getLigneCommandeDAO().findAll()) {
                        if (lc.getIdprod() == idprod) 
                            quantite += lc.getQuantite();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new SimpleObjectProperty<Integer>(quantite);
            }
        });
		try {
			this.tableViewProduit.getItems().addAll(dao.getProduitDAO().findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML public void clickOnTable() {
		buttonDelete.setDisable(false);
		buttonEdit.setDisable(false);
	}

	//Renvoie une liste des produits qui possedent un nom correspondant a la demande
	@FXML public ArrayList<Produit> filtreNom() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String nom = textFieldFiltreNom.getText().trim().toLowerCase();
		ArrayList<Produit> listeProd = new ArrayList<Produit>();
		try {
			if (nom.equals("")) {
				listeProd.addAll(dao.getProduitDAO().findAll());
			}
			else {
				for (Produit produit : dao.getProduitDAO().findAll()) {
					if (produit.getNom().toLowerCase().contains(nom)) {
						listeProd.add(produit);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeProd;
	}

	//Renvoie une liste des produits qui possedent un tarif correspondant a la demande
	@FXML public ArrayList<Produit> filtreTarif() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ArrayList<Produit> listeProd = new ArrayList<Produit>();
		float tarif = 0;

		//Si le tarif rentre est un caractere ou une chaine vide on va dans le catch
		//Sinon si le tarif est superieur a 0 on recupere les produit dont tarif < valeur rentree
		try {
			tarif = Float.parseFloat(textFieldFiltreTarif.getText().trim());
			if (tarif > 0) {
				for (Produit produit : dao.getProduitDAO().findAll()) {
					if (produit.getTarif() <= tarif) {
						listeProd.add(produit);
					}
				}
			}

		} catch (Exception e) {
			if (textFieldFiltreTarif.getText().trim().isEmpty()) {
				try {
					listeProd.addAll(dao.getProduitDAO().findAll());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		return listeProd;
	}

	//Renvoie une liste des produits qui possedent un titre de categorie correspondant a la demande
	@FXML public ArrayList<Produit> filtreCateg() {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String categ = textFieldFiltreCategorie.getText().trim().toLowerCase();
		ArrayList<Produit> listeProd = new ArrayList<Produit>();
		try {
			if (categ.equals("")) {
				listeProd.addAll(dao.getProduitDAO().findAll());
			}
			else {
				for (Produit produit : dao.getProduitDAO().findAll()) {
					if (dao.getCategorieDAO().getById(produit.getIdcateg()).getTitre().toLowerCase().contains(categ)) {
						listeProd.add(produit);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeProd;
	}

	//Rassemble toute les listes des donnees filtrees et fait un ET exclusif des donnees
	@FXML public void filtrageNom() {
		ArrayList<Produit> prodNom = filtreNom();
		ObservableList<Produit> listeProdSelect = FXCollections.observableArrayList();
		ObservableList<Produit> listeProduitSurplus = FXCollections.observableArrayList();
		ObservableList<Produit> listeProduitMino = FXCollections.observableArrayList();

		for (Produit produit : prodNom) {
			if (prodNom.contains(produit))
				listeProdSelect.add(produit);
		}
		//On enleve de la tableView tout produit non present dans listeProdSelect mais present dans la tableView
		ObservableList<Produit> trans1 = tableViewProduit.getItems();
		for (Produit produit : trans1) {
			if (!listeProdSelect.contains(produit))
				listeProduitSurplus.add(produit);
		}

		//On rajoute dans la tableView tout produit present dans listeProdSelect mais non present dans la tableView
		ObservableList<Produit> trans2 = tableViewProduit.getItems();
		for (Produit produit : listeProdSelect ) {
			if (!trans2.contains(produit))
				listeProduitMino.add(produit);
		}

		tableViewProduit.getItems().removeAll(listeProduitSurplus);
		tableViewProduit.getItems().addAll(listeProduitMino);
	}
	
	@FXML public void filtrageTarif() {
		ArrayList<Produit> prodNom = filtreTarif();
		ObservableList<Produit> listeProdSelect = FXCollections.observableArrayList();
		ObservableList<Produit> listeProduitSurplus = FXCollections.observableArrayList();
		ObservableList<Produit> listeProduitMino = FXCollections.observableArrayList();

		for (Produit produit : prodNom) {
			if (prodNom.contains(produit))
				listeProdSelect.add(produit);
		}
		//On enleve de la tableView tout produit non present dans listeProdSelect mais present dans la tableView
		ObservableList<Produit> trans1 = tableViewProduit.getItems();
		for (Produit produit : trans1) {
			if (!listeProdSelect.contains(produit))
				listeProduitSurplus.add(produit);
		}

		//On rajoute dans la tableView tout produit present dans listeProdSelect mais non present dans la tableView
		ObservableList<Produit> trans2 = tableViewProduit.getItems();
		for (Produit produit : listeProdSelect ) {
			if (!trans2.contains(produit))
				listeProduitMino.add(produit);
		}

		tableViewProduit.getItems().removeAll(listeProduitSurplus);
		tableViewProduit.getItems().addAll(listeProduitMino);
	}
	
	@FXML public void filtrageCategorie() {
		ArrayList<Produit> prodNom = filtreCateg();
		ObservableList<Produit> listeProdSelect = FXCollections.observableArrayList();
		ObservableList<Produit> listeProduitSurplus = FXCollections.observableArrayList();
		ObservableList<Produit> listeProduitMino = FXCollections.observableArrayList();

		for (Produit produit : prodNom) {
			if (prodNom.contains(produit))
				listeProdSelect.add(produit);
		}
		//On enleve de la tableView tout produit non present dans listeProdSelect mais present dans la tableView
		ObservableList<Produit> trans1 = tableViewProduit.getItems();
		for (Produit produit : trans1) {
			if (!listeProdSelect.contains(produit))
				listeProduitSurplus.add(produit);
		}

		//On rajoute dans la tableView tout produit present dans listeProdSelect mais non present dans la tableView
		ObservableList<Produit> trans2 = tableViewProduit.getItems();
		for (Produit produit : listeProdSelect ) {
			if (!trans2.contains(produit))
				listeProduitMino.add(produit);
		}

		tableViewProduit.getItems().removeAll(listeProduitSurplus);
		tableViewProduit.getItems().addAll(listeProduitMino);
	}
}

