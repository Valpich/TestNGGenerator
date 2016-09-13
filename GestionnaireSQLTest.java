//Classe de test générée automatiquement :)
//Pensez à changer partout la string VALEUR :)

import static org.testng.Assert.*
import org.testng.annotations.*;

public class GestionnaireSQLTest {

@BeforeClass
public void beforeClass() {
//TODO : AJOUTER DES CHOSES SI NECESSAIRE
}

@Test(priority=1)
public void testConstructeurVide(){GestionnaireSQL gestionnairesql = new GestionnaireSQL();
assertNotNull( gestionnairesql );
}

@Test(priority=2)
public void testConstructeurStringStringStringString() {
String url = VALEUR; //TODO : MODIFIER VALEUR
String user = VALEUR; //TODO : MODIFIER VALEUR
String password = VALEUR; //TODO : MODIFIER VALEUR
String driverName = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL( url, user, password, driverName);
assertNotNull( gestionnairesql );
assertEquals(gestionnairesql.getUrl(), url , "Erreur sur la valeur de l'attribut suivant : url !");
assertEquals(gestionnairesql.getUser(), user , "Erreur sur la valeur de l'attribut suivant : user !");
assertEquals(gestionnairesql.getPassword(), password , "Erreur sur la valeur de l'attribut suivant : password !");
assertEquals(gestionnairesql.getDriverName(), driverName , "Erreur sur la valeur de l'attribut suivant : driverName !");
}


@Test(priority=3)
public void testAccesseurGetConnection() {
Connection connection  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=4)
public void testAccesseurSetConnectionConnection() {
Connection connection  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setConnection(connection);
assertEquals(gestionnairesql.setConnection(), connection , "Erreur sur l'accesseur en écriture suivant : connection !");
//TODO : FAIRE UN TEST !
}


@Test(priority=5)
public void testAccesseurSetDestinationSuccessString() {
String destinationSuccess  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setDestinationSuccess(destinationSuccess);
assertEquals(gestionnairesql.setDestinationSuccess(), destinationSuccess , "Erreur sur l'accesseur en écriture suivant : destinationSuccess !");
//TODO : FAIRE UN TEST !
}


@Test(priority=6)
public void testAccesseurGetDestinationSuccess() {
String string  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=7)
public void testAccesseurSetDestinationFailString() {
String destinationFail  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setDestinationFail(destinationFail);
assertEquals(gestionnairesql.setDestinationFail(), destinationFail , "Erreur sur l'accesseur en écriture suivant : destinationFail !");
//TODO : FAIRE UN TEST !
}


@Test(priority=8)
public void testAccesseurGetDestinationFail() {
String string  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=9)
public void testAccesseurGetState() {
Statement statement  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=10)
public void testAccesseurSetStateStatement() {
Statement state  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setState(state);
assertEquals(gestionnairesql.setState(), state , "Erreur sur l'accesseur en écriture suivant : state !");
//TODO : FAIRE UN TEST !
}


@Test(priority=11)
public void testAccesseurGetResultat() {
ResultSet resultset  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=12)
public void testAccesseurSetResultatResultSet() {
ResultSet resultat  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setResultat(resultat);
assertEquals(gestionnairesql.setResultat(), resultat , "Erreur sur l'accesseur en écriture suivant : resultat !");
//TODO : FAIRE UN TEST !
}


@Test(priority=13)
public void testAccesseurGetResultatMeta() {
ResultSetMetaData resultsetmetadata  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=14)
public void testAccesseurSetResultatMetaResultSetMetaData() {
ResultSetMetaData resultatMeta  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setResultatMeta(resultatMeta);
assertEquals(gestionnairesql.setResultatMeta(), resultatMeta , "Erreur sur l'accesseur en écriture suivant : resultatMeta !");
//TODO : FAIRE UN TEST !
}


@Test(priority=15)
public void testAccesseurGetExporterSQLFail() {
ExportSQL exportsql  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=16)
public void testAccesseurSetExporterSQLFailExportSQL() {
ExportSQL exporterSQLFail  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setExporterSQLFail(exporterSQLFail);
assertEquals(gestionnairesql.setExporterSQLFail(), exporterSQLFail , "Erreur sur l'accesseur en écriture suivant : exporterSQLFail !");
//TODO : FAIRE UN TEST !
}


@Test(priority=17)
public void testAccesseurGetExporterSQLSuccess() {
ExportSQL exportsql  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=18)
public void testAccesseurSetExporterSQLSuccessExportSQL() {
ExportSQL exporterSQLSuccess  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
GestionnaireSQL.setExporterSQLSuccess(exporterSQLSuccess);
assertEquals(gestionnairesql.setExporterSQLSuccess(), exporterSQLSuccess , "Erreur sur l'accesseur en écriture suivant : exporterSQLSuccess !");
//TODO : FAIRE UN TEST !
}


@Test(priority=19)
public void testMethodeAltererNoteNote() {
Note note  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=20)
public void testMethodeAltererMatiereMatiere() {
Matiere matiere  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=21)
public void testMethodeAltererSemestreSemestre() {
Semestre semestre  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=22)
public void testMethodeAltererPersonnePersonne() {
Personne personne  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=23)
public void testMethodeAltererAdresseAdresse() {
Adresse adresse  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=24)
public void testMethodeAjouterNoteNote() {
Note note  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=25)
public void testMethodeAjouterMatiereMatiere() {
Matiere matiere  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=26)
public void testMethodeAjouterSemestreSemestre() {
Semestre semestre  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=27)
public void testMethodeAjouterPersonnePersonne() {
Personne personne  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=28)
public void testMethodeAjouterAdresseAdresse() {
Adresse adresse  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=29)
public void testMethodeAjouterUeUniteEnseignement() {
UniteEnseignement ue  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=30)
public void testMethodeSupprimerAdresseInteger() {
Integer idAdresse  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=31)
public void testMethodeSupprimerNoteInteger() {
Integer idNote  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=32)
public void testMethodeSupprimerMatiereInteger() {
Integer idMatiere  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=33)
public void testMethodeSupprimerSemestreInteger() {
Integer idSemestre  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=34)
public void testMethodeSupprimerPersonneInteger() {
Integer idPersonne  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=35)
public void testMethodeSupprimerUeUniteEnseignement() {
UniteEnseignement ue  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=36)
public void testMethodeFinirRequete() {
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=37)
public void testMethodeViderBaseDeDonnee() {
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=38)
public void testMethodeRecupererNotes() {
List<Note> list  = VALEUR; //TODO : MODIFIER VALEUR
assertEquals( gestionnairesql.recupererNotes(), list,  "La méthode recupererNotes n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=39)
public void testMethodeRecupererProfesseurs() {
List<Professeur> list  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererProfesseurs(), list,  "La méthode recupererProfesseurs n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=40)
public void testMethodeRecupererAdministrateurs() {
List<Administrateur> list  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererAdministrateurs(), list,  "La méthode recupererAdministrateurs n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=41)
public void testMethodeRecupererEtudiantsString() {
List<Etudiant> list  = VALEUR; //TODO : MODIFIER VALEUR
String promotion  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererEtudiants(), list,  "La méthode recupererEtudiants n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=42)
public void testMethodeRecupererMatieres() {
List<Matiere> list  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererMatieres(), list,  "La méthode recupererMatieres n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=43)
public void testMethodeRecupererAdresses() {
List<Adresse> list  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererAdresses(), list,  "La méthode recupererAdresses n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=44)
public void testMethodeRecupererSemestres() {
List<Semestre> list  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererSemestres(), list,  "La méthode recupererSemestres n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=45)
public void testMethodeRecupererUe() {
List<UniteEnseignement> list  = VALEUR; //TODO : MODIFIER VALEUR
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
assertEquals( gestionnairesql.recupererUe(), list,  "La méthode recupererUe n'a pas retourné le bon résultat ! );
//TODO : FAIRE UN TEST !
}


@Test(priority=46)
public void testMethodeCreerBaseDeDonnee() {
GestionnaireSQL gestionnairesql = new GestionnaireSQL() ;//TODO : AJOUTER ARGUMENT AU CONSTRUCTEUR SI BESOIN
assertNotNull( gestionnairesql );
//TODO : FAIRE UN TEST !
}


@Test(priority=47)
public void testMethodeConnexionBaseDeDonneeStringStringString() {
String url  = VALEUR; //TODO : MODIFIER VALEUR
String user  = VALEUR; //TODO : MODIFIER VALEUR
String password  = VALEUR; //TODO : MODIFIER VALEUR
//TODO : FAIRE UN TEST !
}


@Test(priority=48)
public void testMethodeRequeteLectureString() {
String requete  = VALEUR; //TODO : MODIFIER VALEUR
//TODO : FAIRE UN TEST !
}

@AfterClass
public void afterClass() {
//TODO : AJOUTER DES CHOSES SI NECESSAIRE
}

}
