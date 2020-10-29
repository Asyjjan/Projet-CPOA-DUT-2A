package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Client;
import projet.controleur.PageClientController;

public class EditClientController {
	
	@FXML private Button buttonReturn;
	@FXML private Button buttonValider;
	@FXML private Label labelAffichage;
	@FXML private TextField textFieldNomClient;
	@FXML private TextField textFieldPrenomClient;
	@FXML private TextField textFieldIdentifiantClient;
	@FXML private TextField textFieldMdpClient;
	@FXML private TextField textFieldAdrNumClient;
	@FXML private TextField textFieldAdrVoieClient;
	@FXML private TextField textFieldCodePostalClient;
	@FXML private TextField textFieldVilleClient;
	@FXML private TextField textFieldPaysClient;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
		int idcl = PageClientController.getClient().getIdclient();
		Client client = dao.getClientDAO().getById(idcl);
		textFieldNomClient.setText(client.getNom());
		textFieldPrenomClient.setText(client.getPrenom());
		textFieldIdentifiantClient.setText(client.getIdentifiant());
		textFieldMdpClient.setText(client.getMdp());
		textFieldAdrNumClient.setText(client.getAdrnumero());
		textFieldAdrVoieClient.setText(client.getAdrvoie());
		textFieldCodePostalClient.setText(client.getAdrcodepostal());
		textFieldVilleClient.setText(client.getAdrville());
		textFieldPaysClient.setText(client.getAdrpays());
	}
	
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/pageclient.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void majLabelAffichage(ActionEvent e) throws SQLException, IOException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int id = PageClientController.getClient().getIdclient();
		String nom = textFieldNomClient.getText().trim();
		String prenom = textFieldPrenomClient.getText().trim();
		String identifiant = textFieldIdentifiantClient.getText().trim();
		String mdp = textFieldMdpClient.getText().trim();
		String adrnum = textFieldAdrNumClient.getText().trim();
		String adrvoie = textFieldAdrVoieClient.getText().trim();
		String codepostal = textFieldCodePostalClient.getText().trim();
		String ville = textFieldVilleClient.getText().trim();
		String pays = textFieldPaysClient.getText().trim();

		labelAffichage.setText(nom + "," + prenom + "," + id + "," + mdp + "," + adrnum + "," + adrvoie + "," + codepostal + "," + ville + "," + pays);
		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		
		Client client = new Client(id, nom, prenom, identifiant, mdp, adrnum, adrvoie, codepostal, ville, pays);
		dao.getClientDAO().update(client);
		clickOnReturn(e);
		}
	
	@FXML public void keyReleasedProperty() {
		String nom = textFieldNomClient.getText();
		String prenom = textFieldPrenomClient.getText();
		String id = textFieldIdentifiantClient.getText();
		String mdp = textFieldMdpClient.getText();
		String adrnum = textFieldAdrNumClient.getText();
		String adrvoie = textFieldAdrVoieClient.getText();
		String codepostal = textFieldCodePostalClient.getText();
		String ville = textFieldVilleClient.getText();
		String pays = textFieldPaysClient.getText();

		boolean isDisabled = (nom.isEmpty() || nom.trim().isEmpty() || prenom.isEmpty() || prenom.trim().isEmpty() ||
							  id.isEmpty() || id.trim().isEmpty() || mdp.isEmpty() || mdp.trim().isEmpty() ||
							  adrnum.isEmpty() || adrnum .trim().isEmpty() || adrvoie.isEmpty() || adrvoie.trim().isEmpty() ||
							  codepostal.isEmpty() || codepostal.trim().isEmpty() || ville.isEmpty() || ville.trim().isEmpty() ||
							  pays.isEmpty() || pays.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}

}
