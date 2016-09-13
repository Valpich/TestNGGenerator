package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe gerant les relations la base de donnee.
 * 
 * @author Romain Hamon
 * @author Valentin Pichavant
 */
public class GestionnaireSQL implements DataAccessObject {

	private final static String DEFAULT_URL = "jdbc:mysql://mysql-eseo.alwaysdata.net/eseo_projet_java";
	private final static String DEFAULT_USER = "eseo";
	private final static String DEFAULT_PASSWORD = "eseo";
	private ExportSQL exporterSQLFail;
	private ExportSQL exporterSQLSuccess;
	private Connection connection;
	private Statement state;
	private ResultSet resultat;
	private ResultSetMetaData resultatMeta;
	private String destinationSuccess;
	private String destinationFail;

	/**
	 * Permet de creer le gestionnaire s'occupant de l'interaction avec la base
	 * de donnee.
	 */
	public GestionnaireSQL() {
		Date aujourdhui = new Date();
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		if (System.getProperty("os.name").equals("Mac OS X")) {
			this.setDestinationSuccess(new String("Logs/LogSQLSuccess" + fullDateFormat.format(aujourdhui) + ".txt"));
			this.setDestinationFail(new String("Logs/LogSQLFail" + fullDateFormat.format(aujourdhui) + ".txt"));
		} else {
			if (System.getProperty("os.name").equals("Linux")) {
				this.setDestinationSuccess(
						new String("Logs/LogSQLSuccess" + fullDateFormat.format(aujourdhui) + ".txt"));
				this.setDestinationFail(new String("Logs/LogSQLFail" + fullDateFormat.format(aujourdhui) + ".txt"));
			} else {
				this.setDestinationSuccess(
						new String("Logs\\LogSQLSuccess" + fullDateFormat.format(aujourdhui) + ".txt"));
				this.setDestinationFail(new String("Logs\\LogSQLFail" + fullDateFormat.format(aujourdhui) + ".txt"));
			}
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de trouver le Driver MySQL JDBC !");
			e.printStackTrace();
			System.exit(2);
		}
		try {
			this.setConnection(DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Echec de connexion... Arret du programme.");
			System.exit(3);
		}
		this.setExporterSQLSuccess(new ExportSQL(getDestinationSuccess()));
		this.setExporterSQLFail(new ExportSQL(getDestinationFail()));
	}

	/**
	 * Permet de creer le gestionnaire s'occupant de l'interaction avec la base
	 * de donnee dont les informations sont donnees en parametre.
	 * 
	 * @param url
	 *            L'url de la nouvelle base de donnee.
	 * @param user
	 *            L'user de la nouvelle base de donnee.
	 * @param password
	 *            Le mot de passe de la nouvelle base de donnee.
	 * @param driverName
	 *            Le nom de la classe du driver java a utiliser.
	 * @throws SQLException
	 *             L'exception SQL levee par la requete.
	 * @throws ClassNotFoundException
	 *             Si le driver requis n'a pas ete trouve.
	 */
	public GestionnaireSQL(String url, String user, String password, String driverName)
			throws SQLException, ClassNotFoundException {
		Class.forName(driverName);
		this.setConnection(DriverManager.getConnection(url, user, password));
	}

	/**
	 * Accesseur en lecture sur la connection.
	 * 
	 * @return La connection.
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Accesseur en ecriture sur la connection.
	 * 
	 * @param connection
	 *            La nouvelle connection.
	 */
	public void setConnection(final Connection connection) {
		this.connection = connection;
	}

	/**
	 * Accesseur en ecriture de la destination des succes.
	 * 
	 * @param destinationSuccess
	 *            La destination du fichier creer contenant les succes des
	 *            requetes SQL.
	 */

	public void setDestinationSuccess(final String destinationSuccess) {
		this.destinationSuccess = destinationSuccess;
	}

	/**
	 * Accesseur en lecture de la destination des succes.
	 * 
	 * @return La destination du fichier creer contenant les succes des requetes
	 *         SQL.
	 */
	public String getDestinationSuccess() {
		return destinationSuccess;
	}

	/**
	 * Accesseur en ecriture de la destination des echecs.
	 * 
	 * @param destinationFail
	 *            La destination du fichier creer contenant les echecs des
	 *            requetes SQL.
	 */

	public void setDestinationFail(final String destinationFail) {
		this.destinationFail = destinationFail;
	}

	/**
	 * Accesseur en lecture de la destination des echecs.
	 * 
	 * @return destination du fichier creer contenant les echecs des requetes
	 *         SQL.
	 */
	public String getDestinationFail() {
		return destinationFail;
	}

	/**
	 * Accesseur en lecture sur l'etat de la requete.
	 * 
	 * @return L'etat de la requete.
	 */
	public Statement getState() {
		return state;
	}

	/**
	 * Accesseur en ecriture sur l'etat de la requete.
	 * 
	 * @param state
	 *            Le nouvel etat de la requete.
	 */
	public void setState(Statement state) {
		this.state = state;
	}

	/**
	 * Accesseur en lecture du resultat de la requete.
	 * 
	 * @return Le resultat de la requete.
	 */
	public ResultSet getResultat() {
		return resultat;
	}

	/**
	 * Accesseur en ecriture du resultat de la requete.
	 * 
	 * @param resultat
	 *            Le nouveau resultat de la requete.
	 */
	public void setResultat(ResultSet resultat) {
		this.resultat = resultat;
	}

	/**
	 * Accesseur en lecture du resultat des meta donnee de la requete.
	 * 
	 * @return Les meta donnee de la requete
	 */
	public ResultSetMetaData getResultatMeta() {
		return resultatMeta;
	}

	/**
	 * Accesseur en ecriture du resultat des meta donnee de la requete.
	 * 
	 * @param resultatMeta
	 *            Les nouveaux meta donnee de la requete.
	 */
	public void setResultatMeta(ResultSetMetaData resultatMeta) {
		this.resultatMeta = resultatMeta;
	}

	/**
	 * Accesseur en lecture de l'exporter des echecs des requetes SQL.
	 * 
	 * @return Le nouveau niveau d'acreditation de l'utilisateur.
	 */
	public ExportSQL getExporterSQLFail() {
		return exporterSQLFail;
	}

	/**
	 * Accesseur en ecriture de l'exporter des echecs des requetes SQL.
	 * 
	 * @param exporterSQLFail
	 *            Le nouveau niveau d'acreditation de l'utilisateur.
	 */
	public void setExporterSQLFail(ExportSQL exporterSQLFail) {
		this.exporterSQLFail = exporterSQLFail;
	}

	/**
	 * Accesseur en lecture de l'exporter des succes des requetes SQL.
	 * 
	 * @return Le nouveau niveau d'acreditation de l'utilisateur.
	 */
	public ExportSQL getExporterSQLSuccess() {
		return exporterSQLSuccess;
	}

	/**
	 * Accesseur en ecriture de l'exporter des succes des requetes SQL.
	 * 
	 * @param exporterSQLSuccess
	 *            Le nouveau niveau d'acreditation de l'utilisateur.
	 */
	public void setExporterSQLSuccess(ExportSQL exporterSQLSuccess) {
		this.exporterSQLSuccess = exporterSQLSuccess;
	}

	/**
	 * Permet d'ajouter une note a la base de donnee.
	 * 
	 * @param note
	 *            La note a modifier.
	 */
	public void altererNote(final Note note) {
		String requete = new String("UPDATE  `eseo_projet_java`.`note` SET  `nom` =  '" + note.getNom()
				+ "',`valeur` =  '" + note.getValeur() + "',`coefficient` =  '" + note.getCoefficient()
				+ "',`id_matiere` =  '" + note.getIdMatiere() + "',`id_personne` =  '" + note.getIdEtudiant()
				+ "' WHERE  `note`.`id` =" + note.getId() + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de modifier une matiere dans la base de donnee.
	 * 
	 * @param matiere
	 *            La matiere a modifier.
	 */
	public void altererMatiere(final Matiere matiere) {
		String requete = new String("UPDATE  `eseo_projet_java`.`matiere` SET  `nom` =  '" + matiere.getNom()
				+ "',`coefficient` =  '" + matiere.getCoefficient() + "',`moyenne` =  '" + matiere.getMoyenne()
				+ "',`id_responsable` =  '" + matiere.getIdResponsable() + "',`id_semestre`= '" + matiere.getIdSemestre()
				+ "' WHERE  `matiere`.`id` =" + matiere.getId() + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de modifier un semestre dans la base de donnee.
	 * 
	 * @param semestre
	 *            Le semestre a modifier.
	 */
	public void altererSemestre(Semestre semestre) {
		String requete = new String("UPDATE  `eseo_projet_java`.`semestre` SET  `nom` =  '" + semestre.getNom()
				+ "',`date_debut` =  '" + semestre.getDateDebut() + "',`date_fin` =  '" + semestre.getDateFin()
				+ "',`validation` =  '" + semestre.getValide() + "' WHERE  `semestre`.`id` =" + semestre.getId() + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de modifier une personne dans la base de donnee.
	 * 
	 * @param personne
	 *            La personne a modifier.
	 */
	@SuppressWarnings("deprecation")
	public void altererPersonne(final Personne personne) {
		String requete = new String("UPDATE  `eseo_projet_java`.`personne` SET  `nom` =  '" + personne.getNom()
				+ "',`prenom` =  '" + personne.getPrenom() + "',`date_de_naissance` =  '"
				+ personne.getDateDeNaissance() + "',`email_professionel` =  '"
				+ personne.getEmailProfessionnel().toLowerCase() + "',`email_personnel` =  '"
				+ personne.getEmailPersonnel().toLowerCase() + "',`telephone_fixe` =  '" + personne.getTelephoneFixe()
				+ "',`telephone_mobile` =  '" + personne.getTelephoneMobile() + "',`identifiant` =  '"
				+ personne.getIdentifiant() + "',`annee_arrivee` =  '" + (personne.getAnneeArrivee().getYear() + 1900)
				+ "',`promotion` =  '" + personne.getPromotion() + "',`redoublant` =  '"
				+ ((personne.isRedoublant()) ? 1 : 0) + "',`adresse` =  '" + personne.getIdAdresse()
				+ "' WHERE  `personne`.`id` =" + personne.getId() + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de modifier une adresse dans la base de donnee.
	 * 
	 * @param adresse
	 *            L'adresse a modifier.
	 */
	public void altererAdresse(final Adresse adresse) {
		String requete = new String("UPDATE  `eseo_projet_java`.`adresse` SET  `numero` =  '" + adresse.getNumero()
				+ "',`rue` =  '" + adresse.getRue() + "',`code_postal` =  '" + adresse.getCodePostal()
				+ "',`ville` =  '" + adresse.getVille() + "',`pays` =  '" + adresse.getPays() + "',`nom_adresse` =  '"
				+ adresse.getNomAdresse() + "' WHERE  `adresse`.`id` =" + adresse.getId() + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet d'ajouter une note a la base de donnee.
	 * 
	 * @param note
	 *            La note a ajouter.
	 */
	@Override
	public void ajouterNote(final Note note) {
		String requete = new String(
				"INSERT INTO `eseo_projet_java`.`note` (`id`, `nom`, `valeur`, `coefficient`, `id_matiere`, `id_personne`) VALUES (NULL, '"
						+ note.getNom() + "', '" + note.getValeur() + "', '" + note.getCoefficient() + "', '"
						+ note.getIdMatiere() + "', '" + note.getIdEtudiant() + "');");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet d'ajouter une matiere a la base de donnee.
	 * 
	 * @param matiere
	 *            La matiere a ajouter.
	 */
	@Override
	public void ajouterMatiere(final Matiere matiere) {
		String requete = new String(
				"INSERT INTO `eseo_projet_java`.`matiere` (`id`, `nom`, `coefficient`, `moyenne`, `id_responsable`, `id_semestre`) VALUES (NULL, '"
						+ matiere.getNom() + "', '" + matiere.getCoefficient() + "', " + matiere.getMoyenne() + ", '"
						+ matiere.getIdResponsable() + "', '" + matiere.getIdSemestre() + "');");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet d'ajouter un semestre a la base de donnee.
	 * 
	 * @param semestre
	 *            Le semestre a ajouter.
	 */
	@Override
	public void ajouterSemestre(Semestre semestre) {
		String requete = new String(
				"INSERT INTO `eseo_projet_java`.`semestre` (`id`, `nom`, `date_debut`, `date_fin`, `validation`) VALUES (NULL, '"
						+ semestre.getNom() + "', '" + semestre.getDateDebut() + "', '" + semestre.getDateFin() + "', '"
						+ semestre.getValide() + "');");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet d'ajouter une personne a la base de donnee.
	 * 
	 * @param personne
	 *            La personne a ajouter.
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void ajouterPersonne(final Personne personne) {
		System.out.println(personne.getAnneeArrivee().getYear());
		String requete = new String(
				"INSERT INTO `eseo_projet_java`.`personne` (`id`, `nom`, `prenom`, `date_de_naissance`, `email_professionel`, `email_personnel`, `telephone_fixe`, `telephone_mobile`, `identifiant`, `annee_arrivee`, `promotion`, `redoublant`, `adresse`) VALUES (NULL, '"
						+ personne.getNom() + "', '" + personne.getPrenom() + "', '" + personne.getDateDeNaissance()
						+ "', '" + personne.getEmailProfessionnel().toLowerCase() + "', '"
						+ personne.getEmailPersonnel().toLowerCase() + "', '" + personne.getTelephoneFixe() + "', '"
						+ personne.getTelephoneMobile() + "', '" + personne.getIdentifiant() + "', '"
						+ (personne.getAnneeArrivee().getYear() + 1900) + "', '" + personne.getPromotion() + "', '"
						+ ((personne.isRedoublant()) ? 1 : 0) + "', '" + personne.getIdAdresse() + "');");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet d'ajouter une adresse a la base de donnee.
	 * 
	 * @param adresse
	 *            L'adresse a ajouter.
	 */
	@Override
	public void ajouterAdresse(final Adresse adresse) {
		String requete = new String(
				"INSERT INTO `eseo_projet_java`.`adresse` (`id`, `numero`, `rue`, `code_postal`, `ville`, `pays`, `nom_adresse`) VALUES (NULL, '"
						+ adresse.getNumero() + "', '" + adresse.getRue() + "', '" + adresse.getCodePostal() + "', '"
						+ adresse.getVille() + "', '" + adresse.getPays() + "', '" + adresse.getNomAdresse() + "');");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet d'ajouter un ue a la base de donnee.
	 * 
	 * @param ue
	 *            L'ue a ajouter.
	 */
	@Override
	public void ajouterUe(final UniteEnseignement ue) {
		for (Integer idMatiere : ue.getIdMatieres()) {
			String requete = new String(
					"INSERT INTO `eseo_projet_java`.`ue` (`id`, `nom`, `coefficient`, `id_matiere`) VALUES (NULL, '"
							+ ue.getNom() + "', '" + ue.getCoefficient() + "', '" + idMatiere + "');");
			try {
				this.setState(this.getConnection().createStatement());
				this.getState().executeUpdate(requete);
				this.getExporterSQLSuccess().exporter(requete);
			} catch (SQLException e) {
				this.getExporterSQLFail().exporter(requete, e);
			} finally {
				this.finirRequete();
			}
		}
	}

	/**
	 * Permet de supprimer une adresse de la base de donnee.
	 * 
	 * @param idAdresse
	 *            L'id de l'adresse a supprimer.
	 */
	@Override
	public void supprimerAdresse(final Integer idAdresse) {
		String requete = new String(
				"DELETE FROM `eseo_projet_java`.`adresse` WHERE `adresse`.`id` = " + idAdresse + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de supprimer une note de la base de donnee.
	 * 
	 * @param idNote
	 *            L'id de la note a supprimer.
	 */
	@Override
	public void supprimerNote(final Integer idNote) {
		String requete = new String("DELETE FROM `eseo_projet_java`.`note` WHERE `note`.`id` = " + idNote + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de supprimer une matiere de la base de donnee.
	 * 
	 * @param idMatiere
	 *            L'id de la matiere a supprimer.
	 */
	@Override
	public void supprimerMatiere(final Integer idMatiere) {
		String requete = new String(
				"DELETE FROM `eseo_projet_java`.`matiere` WHERE `matiere`.`id` = " + idMatiere + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de supprimer un semestre de la base de donnee.
	 * 
	 * @param idSemestre
	 *            L'id du semestre a supprimer.
	 */
	@Override
	public void supprimerSemestre(Integer idSemestre) {
		String requete = new String(
				"DELETE FROM `eseo_projet_java`.`semestre` WHERE `semestre`.`id` = " + idSemestre + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de supprimer une personne de la base de donnee.
	 * 
	 * @param idPersonne
	 *            L'id de la personne a supprimer.
	 */
	@Override
	public void supprimerPersonne(Integer idPersonne) {
		String requete = new String(
				"DELETE FROM `eseo_projet_java`.`personne` WHERE `personne`.`id` = " + idPersonne + ";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de supprimer un ue de la base de donnee.
	 * 
	 * @param ue
	 *            L'ue a supprimer.
	 */
	@Override
	public void supprimerUe(UniteEnseignement ue) {
		String requete = new String("DELETE FROM `eseo_projet_java`.`ue` WHERE `ue`.`nom` = \"" + ue.getNom() + "\";");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de clore les requetes a la base de donne.
	 */
	public void finirRequete() {
		try {
			if (getResultat() != null)
				getResultat().close();
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter("Erreur lors de la destruction du resultat.", e);
		}
		try {
			if (getState() != null)
				getState().close();
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter("Erreur lors de la destruction de l'etat.", e);
		}
	}

	/**
	 * Permet de vider la base de donnee de facon securisee.
	 */
	public void viderBaseDeDonnee() {
		String requete = new String("DROP TABLE `note`;");
		String requeteDeux = new String("DROP TABLE `ue`;");
		String requeteTrois = new String("DROP TABLE `matiere`;");
		String requeteQuatre = new String("DROP TABLE `semestre`;");
		String requeteCinq = new String("DROP TABLE `personne`;");
		String requeteSix = new String("DROP TABLE `adresse`;");
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requete);
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requeteDeux);
			this.getExporterSQLSuccess().exporter(requeteDeux);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requeteDeux, e);
		} finally {
			this.finirRequete();
		}
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requeteTrois);
			this.getExporterSQLSuccess().exporter(requeteTrois);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requeteTrois, e);
		} finally {
			this.finirRequete();
		}
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requeteQuatre);
			this.getExporterSQLSuccess().exporter(requeteQuatre);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requeteQuatre, e);
		} finally {
			this.finirRequete();
		}
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requeteCinq);
			this.getExporterSQLSuccess().exporter(requeteCinq);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requeteCinq, e);
		} finally {
			this.finirRequete();
		}
		try {
			this.setState(this.getConnection().createStatement());
			this.getState().executeUpdate(requeteSix);
			this.getExporterSQLSuccess().exporter(requeteSix);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requeteSix, e);
		} finally {
			this.finirRequete();
		}
	}

	/**
	 * Permet de recuperer la liste des notes en base de donnee.
	 * 
	 * @return La liste des notes.
	 * 
	 */
	@Override
	public static List<Note> recupererNotes() {
		ArrayList<Note> notes = new ArrayList<Note>();
		String requete = new String("SELECT * FROM `note`;");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				notes.add(new Note(getResultat().getInt(1), getResultat().getString(2), getResultat().getFloat(3),
						getResultat().getFloat(4), getResultat().getInt(6), getResultat().getInt(5)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return notes;
	}

	/**
	 * Permet de recuperer la liste des professeurs en base de donnee.
	 * 
	 * @return La liste des professeurs.
	 * 
	 */
	@Override
	public List<Professeur> recupererProfesseurs() {
		ArrayList<Professeur> professeurs = new ArrayList<Professeur>();
		String requete = new String("SELECT * FROM `personne` WHERE `personne`.`promotion` = \"Professeur\"");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				List<String> email = new ArrayList<String>();
				email.add(getResultat().getString(5));
				email.add(getResultat().getString(6));
				List<Integer> telephone = new ArrayList<Integer>();
				telephone.add(getResultat().getInt(7));
				telephone.add(getResultat().getInt(8));
				professeurs.add(new Professeur(getResultat().getInt(1), getResultat().getString(2),
						getResultat().getString(3), getResultat().getDate(4), getResultat().getInt(13), email,
						telephone, getResultat().getDate(10), getResultat().getString(9)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return professeurs;
	}

	/**
	 * Permet de recuperer la liste des administrateurs en base de donnee.
	 * 
	 * @return La liste des administrateurs.
	 * 
	 */
	@Override
	public List<Administrateur> recupererAdministrateurs() {
		ArrayList<Administrateur> administrateurs = new ArrayList<Administrateur>();
		String requete = new String("SELECT * FROM `personne` WHERE `personne`.`promotion` = \"Administrateur\"");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				List<String> email = new ArrayList<String>();
				email.add(getResultat().getString(5));
				email.add(getResultat().getString(6));
				List<Integer> telephone = new ArrayList<Integer>();
				telephone.add(getResultat().getInt(7));
				telephone.add(getResultat().getInt(8));
				administrateurs.add(new Administrateur(getResultat().getInt(1), getResultat().getString(2),
						getResultat().getString(3), getResultat().getDate(4), getResultat().getInt(13), email,
						telephone, getResultat().getDate(10), getResultat().getString(9)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return administrateurs;
	}

	/**
	 * Permet de recuperer la liste des etudiants en base de donnee.
	 * 
	 * @param promotion
	 *            La promotion a recuperer, recupere tous les etudiants si null.
	 * 
	 * @return La liste des eleves.
	 * 
	 */
	@Override
	public List<Etudiant> recupererEtudiants(final String promotion) {
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		String requete = null;
		requete = new String("SELECT * FROM `personne` WHERE `personne`.`promotion` = \"" + promotion + "\"");
		if (promotion == null)
			requete = new String(
					"SELECT * FROM `personne` WHERE `personne`.`promotion` != \"Administrateur\" AND `personne`.`promotion` != \"Professeur\"");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				List<String> email = new ArrayList<String>();
				email.add(getResultat().getString(5));
				email.add(getResultat().getString(6));
				List<Integer> telephone = new ArrayList<Integer>();
				telephone.add(getResultat().getInt(7));
				telephone.add(getResultat().getInt(8));
				etudiants.add(new Etudiant(getResultat().getInt(1), getResultat().getString(2),
						getResultat().getString(3), getResultat().getDate(4), getResultat().getInt(13), email,
						telephone, getResultat().getBoolean(12), getResultat().getDate(10), getResultat().getString(9),
						getResultat().getString(11)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return etudiants;
	}

	/**
	 * Permet de recuperer la liste des matieres en base de donnee.
	 * 
	 * @return La liste des matieres.
	 * 
	 */
	@Override
	public List<Matiere> recupererMatieres() {
		ArrayList<Matiere> Matiere = new ArrayList<Matiere>();
		String requete = new String("SELECT * FROM `matiere`;");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				Matiere.add(new Matiere(getResultat().getInt(1), getResultat().getInt(5), getResultat().getString(2),
						getResultat().getDouble(3), getResultat().getInt(6), getResultat().getDouble(4)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return Matiere;
	}

	/**
	 * Permet de recuperer la liste des matieres en base de donnee.
	 * 
	 * @return La liste des matieres.
	 * 
	 */
	@Override
	public List<Adresse> recupererAdresses() {
		ArrayList<Adresse> adresse = new ArrayList<Adresse>();
		String requete = new String("SELECT * FROM `adresse`;");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				adresse.add(new Adresse(getResultat().getInt(1), getResultat().getInt(2), getResultat().getString(3),
						getResultat().getInt(4), getResultat().getString(5), getResultat().getString(6),
						getResultat().getString(7)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return adresse;
	}

	/**
	 * Permet de recuperer la liste des semestres en base de donnee.
	 * 
	 * @return La liste des semestres.
	 * 
	 */
	@Override
	public List<Semestre> recupererSemestres() {
		ArrayList<Semestre> semestre = new ArrayList<Semestre>();
		String requete = new String("SELECT * FROM `semestre`;");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				semestre.add(new Semestre(getResultat().getInt(1), getResultat().getDate(3), getResultat().getDate(4),
						getResultat().getString(2), getResultat().getInt(5)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		return semestre;
	}

	/**
	 * Permet de recuperer la liste des ue en base de donnee.
	 * 
	 * @return La liste des ue.
	 * 
	 */
	@Override
	public List<UniteEnseignement> recupererUe() {
		/**
		 * Classe interne permettant la conversion des ues en base de donnee
		 * vers des ues du modele.
		 */
		class UeDB {
			private Integer id;
			private String nom;
			private Double coefficient;
			private Integer idMatiere;

			@SuppressWarnings("unused")
			public Integer getId() {
				return id;
			}

			public void setId(final Integer id) {
				this.id = id;
			}

			public String getNom() {
				return nom;
			}

			public void setNom(final String nom) {
				this.nom = nom;
			}

			public Double getCoefficient() {
				return coefficient;
			}

			public void setCoefficient(Double coefficient) {
				this.coefficient = coefficient;
			}

			public Integer getIdMatiere() {
				return idMatiere;
			}

			public void setIdMatiere(final Integer idMatiere) {
				this.idMatiere = idMatiere;
			}

			public UeDB(Integer id, String nom, final Double coefficient, final Integer idMatiere) {
				this.setId(idMatiere);
				this.setNom(nom);
				this.setCoefficient(coefficient);
				this.setIdMatiere(idMatiere);
			}
		}
		ArrayList<UniteEnseignement> ue = new ArrayList<UniteEnseignement>();
		ArrayList<UeDB> uesDB = new ArrayList<UeDB>();
		String requete = new String("SELECT * FROM `ue`;");
		try {
			this.requeteLecture(requete);
			while (getResultat().next()) {
				uesDB.add(new UeDB(getResultat().getInt(1), getResultat().getString(2), getResultat().getDouble(3),
						getResultat().getInt(4)));
			}
			this.getExporterSQLSuccess().exporter(requete);
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(requete, e);
		} finally {
			this.finirRequete();
		}
		for (UeDB ueDB : uesDB) {
			boolean ajoute = false;
			boolean parcours = true;
			while (parcours == true) {
				for (UniteEnseignement uniteEnseignement : ue) {
					if (ueDB.getNom().equals(uniteEnseignement.getNom())) {
						uniteEnseignement.getIdMatieres().add(ueDB.getIdMatiere());
						ajoute = true;
					}
					if (ajoute == true)
						break;
				}
				parcours = false;
			}
			if (ajoute == false) {
				ArrayList<Integer> nouvelleListe = new ArrayList<Integer>();
				nouvelleListe.add(ueDB.getIdMatiere());
				ue.add(new UniteEnseignement(ueDB.getNom(), ueDB.getCoefficient(), nouvelleListe));
			}
		}
		return ue;
	}

	/**
	 * Permet de creer la base de donnee.
	 */
	public void creerBaseDeDonnee() {
		String s = new String();
		StringBuffer sb = new StringBuffer();
		String[] inst = null;
		int i = 0;
		try {
			FileReader fr = new FileReader(new File("eseo_projet_java.sql"));
			BufferedReader br = new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			inst = sb.toString().split(";");
			Connection c = this.getConnection();
			Statement st = c.createStatement();
			for (i = 0; i < inst.length; i++) {
				if (!inst[i].trim().equals("")) {
					st.executeUpdate(inst[i]);
					this.getExporterSQLSuccess().exporter(inst[i]);
				}
			}
		} catch (SQLException e) {
			this.getExporterSQLFail().exporter(inst[i], e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de lancer la connexion vers une base de donnee.
	 * 
	 * @param url
	 *            L'adresse url de la base de donnee.
	 * @param user
	 *            Le nom de l'utilisateur de la base de donnee.
	 * @param password
	 *            Le mot de passe de l'utilisateur de la base de donnee.
	 * @throws SQLException
	 *             L'exception SQL levee par la requete.
	 */
	public static void connexionBaseDeDonnee(final String url, final String user, final String password) throws SQLException {
		this.setConnection(DriverManager.getConnection(url, user, password));
	}

	/**
	 * Permet d'envoyer une requete SQL la base de donnee connectee.
	 * 
	 * @param requete
	 *            La requete a envoyer.
	 * @throws SQLException
	 *             L'exception SQL levee par la requete.
	 */
	public static void requeteLecture(String requete) throws SQLException {
		this.setState(this.getConnection().createStatement());
		this.setResultat(this.getState().executeQuery(requete));
		this.setResultatMeta(this.getResultat().getMetaData());
	}

}
