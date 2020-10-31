package projet.controleur;

import org.apache.maven.model.Parent;

import com.gluonhq.charm.glisten.control.TextField;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class EditLigneCommandeController {

	@FXML
	private Button buttonReturn;
	@FXML
	private Button buttonValider;
	@FXML
	private Label labelAffichage;
	@FXML
	private TextField textFieldLigneCommandeQuantite;
	@FXML
	private TextField textFieldLigneCommandeTarif;
	@FXML
	private ChoiceBox<Commande> choiceBoxCommande;
	@FXML
	private ChoiceBox<Produit> choiceBoxProduit;

	@FXML
	public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
		ObservableList<Commande> commande = FXCollections.observableArrayList(dao.getCommandeDAO().findAll());
		ObservableList<Produit> produit = FXCollections.observableArrayList(dao.getProduitDAO().findAll());
		LigneCommande lignecom = dao.getLigneCommandeDAO().getById(idcom, idprod);
		choiceBoxCommande.setItems(commande);
		choiceBoxProduit.setItems(produit);
		textFieldLigneCommandeQuantite.setText(lignecom.getQuantite());
		textFieldLigneCommandeTarif.setText(lignecom.getTarif());
	}

	@FXML
	public void clickOnReturn(ActionEvent e) throws IOException {
		Parent LigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/pagelignecommande.fxml"));
		Scene LigneCommandescene = new Scene(LigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(LigneCommandescene);
		window.centerOnScreen();
		window.setTitle("LigneCommande");
		window.show();
	}

	@FXML
	public void majLabelAffichage(ActionEvent e) throws SQLException, IOException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String strquantite = textFieldQuantiteLigneCommande.getText().trim();
		String strtarif = textFieldTarifLigneCommande.getText().trim();

		int quantite = Integer.parseInt(strquantite);
		float tarif = Float.parseFloat(strtarif);
		int idcom = choiceBoxCommande.getValue().getIdcommande();
		int idprod = choiceBoxProduit.getValue().getIdprod();

		labelAffichage.setText(quantite + "," + tarif);
		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");

		LigneCommande lignecom = new LigneCommande(quantite, tarif);
		dao.getCategorieDAO().update(lignecom);
		clickOnReturn(e);
	}

	@FXML
	public void keyReleasedProperty() {
		int quantite = textFieldLigneCommandeQuantite.getText();
		float tarif = textFieldLigneCommandeTarif.getText();

		boolean isDisabled = (quantite == 0 || tarif == 0);
		buttonValider.setDisable(isDisabled);
	}
}
