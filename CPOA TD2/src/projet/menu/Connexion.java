package projet.menu;
import java.sql.*;
public class Connexion {

	public static Connection creeConnexion() 
	{
		String url ="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/gisonni2u_CPOA";
		url += "?serverTimezone=Europe/Paris";
		String login = "gisonni2u_appli";
		String pwd = "Huntercraft57,";
		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;
	}
}