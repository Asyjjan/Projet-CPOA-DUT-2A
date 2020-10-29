package projet.controleur;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.LigneCommande;

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
	public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
		///////////////////////////////////////////////////////////////////////////////////////////// quantite
		///////////////////////////////////////////////////////////////////////////////////////////// =
		///////////////////////////////////////////////////////////////////////////////////////////// PageLigneCommandeController.getLigneCommande().getquantite();
		//////////////////////////////////////////////////////////////////////////////////////////// LigneCommande
		///////////////////////////////////////////////////////////////////////////////////////////// lignecom
		///////////////////////////////////////////////////////////////////////////////////////////// =
		///////////////////////////////////////////////////////////////////////////////////////////// dao.getLigneCommandeDAO().getById(quantite);
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
		///////////////////////////////////////////////////////////////////////////////////// int
		///////////////////////////////////////////////////////////////////////////////////// id
		///////////////////////////////////////////////////////////////////////////////////// =
		///////////////////////////////////////////////////////////////////////////////////// PageLigneCommandeController.getLigneCommande().getId();
		int quantite = textFieldLigneCommandeQuantite.getText().trim();
		float tarif = textFieldLigneCommandeTarif.getText().trim();

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
