package projet.controleur;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.LigneCommande;

public class AjoutLigneCommandeController {

	@FXML
	private TextField textFieldLigneCommandeQuantite;
	@FXML
	private TextField textFieldLigneCommandeTarif;
	@FXML
	private Label labelAffichage;
	@FXML
	private Button buttonValider;
	@FXML
	private Button buttonReturn;

	@FXML
	public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
	}

	@FXML
	public void majLabelAffichage(ActionEvent e) throws SQLException, IOException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int quantite = textFieldLigneCommandeQuantite.getText().trim();
		float tarif = textFieldLigneCommandeTarif.getText().trim();

		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		labelAffichage.setText("La LigneCommande de quantite : " + quantite + " à été ajoutée");
		LigneCommande lignecom = new LigneCommande(quantite, tarif);
		dao.getCategorieDAO().create(lignecom);
		clickOnReturn(e);
	}

	@FXML
	public void keyReleasedProperty() {
		int quantite = textFieldLigneCommandeQuantite.getText();
		float tarif = textFieldLigneCommandeTarif.getText();

		boolean isDisabled = (quantite == 0 || tarif == 0);
		buttonValider.setDisable(isDisabled);
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
}
