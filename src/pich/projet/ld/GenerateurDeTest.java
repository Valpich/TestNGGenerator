package pich.projet.ld;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GenerateurDeTest {
  private String fichier;
  private StringBuilder path;

  public void genererTest(final String path, ArrayList<String> constructeurs,
      ArrayList<String> methodes) {
    this.path = new StringBuilder(path);
    try {
      FileOutputStream fin =
          new FileOutputStream(new File(path.replaceFirst(".java", "Test.java")));
      try {
        String className =
            new String(path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(fin, "UTF-8"), true);
        writer.println("//Classe de test générée automatiquement :)");
        writer.println("//Pensez à changer partout la string VALEUR :)");
        writer.println("");
        writer.println("import static org.testng.Assert.*;");
        writer.println("import org.testng.annotations.*;");
        writer.println("");
        writer.println("public class " + className + "Test {");
        writer.println("");
        writer.println("@BeforeClass");
        writer.println("public void beforeClass() {");
        writer.println("//TODO : AJOUTER DES CHOSES SI NECESSAIRE !");
        writer.println("}");
        int i = 1;
        for (String constructeur : constructeurs) {
          writer.println("");
          genererConstructeur(constructeur, writer, className, i);
          writer.println("");
          i++;
        }
        for (String methode : methodes) {
          writer.println("");
          genererMethode(methode, writer, className, i);
          writer.println("");
          i++;
        }
        writer.println("@AfterClass");
        writer.println("public void afterClass() {");
        writer.println("//TODO : AJOUTER DES CHOSES SI NECESSAIRE !");
        writer.println("}");
        writer.println("");
        writer.println("}");
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void genererConstructeur(String constructeur, PrintWriter writer, String className,
      int iteration) {
    writer.println("@Test(priority=" + iteration + ")");
    if (constructeur.indexOf("(") + 1 != constructeur.indexOf(")")) {
      String arguments =
          constructeur.substring(constructeur.indexOf("(") + 1, constructeur.indexOf(")"));
      String[] tokens = arguments.split(",");
      writer.print("public void testConstructeur");
      for (String token : tokens) {
        String type = "";
        if (token.contains("final")) {
          type = token.substring(token.indexOf("final") + 5, token.lastIndexOf(" ")).trim();
        } else {
          type = token.substring(0, token.lastIndexOf(" ")).trim();
        }
        writer.print(type.substring(0, 1).toUpperCase());
        writer.print(type.substring(1));
      }
      writer.println("() {");
      for (String token : tokens) {
        String type = "";
        if (token.contains("final")) {
          type = token.substring(token.indexOf("final") + 5, token.lastIndexOf(" ")).trim();
        } else {
          type = token.substring(0, token.lastIndexOf(" ")).trim();
        }
        String variable = token.substring(token.lastIndexOf(" "));
        writer.println(type + " " + variable.trim() + " = VALEUR; //TODO : MODIFIER VALEUR !");
      }
      writer.print(className + " " + className.toLowerCase() + " = new " + className + "(");
      int i = 0;
      for (String token : tokens) {
        String variable = token.substring(token.lastIndexOf(" "));
        if (i != 0)
          writer.print(",");
        writer.print(variable);
        i++;
      }
      writer.println(");");
      writer.println("assertNotNull( " + className.toLowerCase() + " );");
      for (String token : tokens) {
        String variable = token.substring(token.lastIndexOf(" "));
        variable = variable.trim();
        String type = "";
        if (token.contains("final")) {
          type = token.substring(token.lastIndexOf("final") + 5, token.lastIndexOf(" ")).trim();
        } else {
          type = token.substring(0, token.lastIndexOf(" ")).trim();
        }

        if (type.equals("Double") || type.equals("Float") || type.equals("double")
            || type.equals("float")) {
          String delta = "0.00001";
          writer.println("assertEquals(" + className.toLowerCase() + ".get"
              + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), " + variable
              + ", " + delta + ", \"Erreur sur la valeur de l'attribut suivant : " + variable
              + " !\");");
        } else {
          writer.println("assertEquals(" + className.toLowerCase() + ".get"
              + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), " + variable
              + " , \"Erreur sur la valeur de l'attribut suivant : " + variable + " !\");");
        }
      }
      writer.println("}");
    } else {
      writer.println("public void testConstructeurVide(){");
      writer.println(className + " " + className.toLowerCase() + " = new " + className + "();");
      writer.println("assertNotNull( " + className.toLowerCase() + " );");
      writer.print("}");
    }
  }

  public void genererMethode(String methode, PrintWriter writer, String className, int iteration) {
    writer.println("@Test(priority=" + iteration + ")");
    boolean accessor = false;
    boolean get = false;
    boolean afficherTest = false;
    String valeurTestRetour = "";
    String methodName = "";
    String arguments = methode.substring(methode.indexOf("(") + 1, methode.indexOf(")"));
    String[] tokens = arguments.split(",");
    if (methode.contains("get") || methode.contains("set")) {
      writer.print("public void testAccesseur");
      accessor = true;
      String name = "";
      if (methode.contains("get")) {
        get = true;
        name = methode.substring(methode.indexOf("get"), methode.indexOf("("));
      } else {
        name = methode.substring(methode.indexOf("set"), methode.indexOf("("));
      }
      writer.print(name.substring(0, 1).trim().toUpperCase());
      writer.print(name.substring(1).trim());
    } else {
      String name = "";
      name = methode.substring(0, methode.indexOf("("));
      name = name.substring(name.lastIndexOf(" "));
      writer.print("public void testMethode");
      name = name.trim();
      writer.print(name.substring(0, 1).toUpperCase());
      writer.print(name.substring(1).trim());
    }
    if (methode.indexOf("(") + 1 != methode.indexOf(")")) {
      for (String token : tokens) {
        String type = "";
        if (token.contains("final")) {
          type = token.substring(token.indexOf("final") + 5, token.lastIndexOf(" ")).trim();
        } else {
          type = token.substring(0, token.lastIndexOf(" ")).trim();
        }
        writer.print(type.substring(0, 1).toUpperCase());
        writer.print(type.substring(1));
      }
    }
    writer.println("() {");
    String typeRetour = "";
    methodName = methode.substring(0, methode.indexOf("("));
    typeRetour = methodName.substring(0, methodName.lastIndexOf(" "));
    typeRetour = typeRetour.substring(typeRetour.lastIndexOf(" "));
    if (!typeRetour.contains("void")) {
      if (!typeRetour.contains("<")) {
        writer.println(typeRetour.trim() + " " + typeRetour.toLowerCase().trim()
            + "  = VALEUR; //TODO : MODIFIER VALEUR !");
        valeurTestRetour = typeRetour.toLowerCase().trim();
      } else {
        writer.println(typeRetour.trim() + " "
            + typeRetour.substring(0, typeRetour.indexOf("<")).toLowerCase().trim()
            + "  = VALEUR; //TODO : MODIFIER VALEUR !");
        valeurTestRetour = typeRetour.substring(0, typeRetour.indexOf("<")).toLowerCase().trim();
      }
    }
    if (methode.indexOf("(") + 1 != methode.indexOf(")")) {
      for (String token : tokens) {
        String type = "";
        String variable = "";
        if (token.contains("final")) {
          variable = token.substring(token.lastIndexOf(" ")).trim();
          type = token.substring(token.indexOf("final") + 5, token.lastIndexOf(" ")).trim();
        } else {
          variable = token.substring(token.lastIndexOf(" ")).trim();
          type = token.substring(0, token.lastIndexOf(" ")).trim();
        }
        writer
            .println(type.trim() + " " + variable.trim() + "  = VALEUR; //TODO : MODIFIER VALEUR !");
      }
    }
    if (!methode.contains("static")) {
      writer.print(className + " " + className.toLowerCase() + " = new " + className + "() ;");
      writer.println("//TODO : AJOUTER ARGUMENT(S) AU CONSTRUCTEUR SI BESOIN !");
      writer.println("assertNotNull( " + className.toLowerCase() + " );");
    }
    if (accessor == false && (!methode.contains("void"))) {
      writer.print("assertEquals( " + className.toLowerCase() + "."
          + methodName.substring(methodName.lastIndexOf(" ")).trim() + "(), ");
      writer.print(valeurTestRetour + ", ");
      writer.println(" \"La méthode " + methodName.substring(methodName.lastIndexOf(" ")).trim()
          + " n'a pas retourné le bon résultat !\" );");
    }
    if (accessor == true && get == false) {
      if (methode.indexOf("(") + 1 != methode.indexOf(")")) {
        String nom = methode.substring(0, methode.indexOf("("));
        nom = nom.substring(nom.lastIndexOf(" ")).trim();
        writer.print("" + className + "." + nom + "(");
        int j = 0;
        for (String token : tokens) {
          String variable = "";
          if (token.contains("final")) {
            variable = token.substring(token.lastIndexOf(" ")).trim();
          } else {
            variable = token.substring(token.lastIndexOf(" ")).trim();
          }
          if (j != 0)
            writer.print(", ");
          writer.print(variable);
          j++;
        }
      }
      writer.println(");");
    }
    if (methode.indexOf("(") + 1 != methode.indexOf(")")) {
      for (String token : tokens) {
        String type = "";
        String variable = "";
        if (token.contains("final")) {
          variable = token.substring(token.lastIndexOf(" ")).trim();
          type = token.substring(token.indexOf("final") + 5, token.lastIndexOf(" ")).trim();
        } else {
          variable = token.substring(token.lastIndexOf(" ")).trim();
          type = token.substring(0, token.lastIndexOf(" ")).trim();
        }

        if (!methode.contains("static")) {
          if (accessor) {
            if (get) {
              if (type.equals("Double") || type.equals("Float") || type.equals("double")
                  || type.equals("float")) {
                String delta = "0.00001";
                writer.println("assertEquals(" + className.toLowerCase() + ".get"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + ", " + delta + ", \"Erreur sur l'accesseur en lecture suivant : "
                    + variable + " !\");");
              } else {
                writer.println("assertEquals(" + className.toLowerCase() + ".get"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + " , \"Erreur sur l'accesseur en lecture suivant : " + variable
                    + " !\");");
              }
            } else {
              if (type.equals("Double") || type.equals("Float") || type.equals("double")
                  || type.equals("float")) {
                String delta = "0.00001";
                writer.println("assertEquals(" + className.toLowerCase() + ".set"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + ", " + delta + ", \"Erreur sur l'accesseur en écriture suivant : "
                    + variable + " !\");");
              } else {
                writer.println("assertEquals(" + className.toLowerCase() + ".set"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + " , \"Erreur sur l'accesseur en écriture suivant : " + variable
                    + " !\");");
              }
            }
          }
        } else {
          if (accessor) {
            if (get) {
              if (type.equals("Double") || type.equals("Float") || type.equals("double")
                  || type.equals("float")) {
                String delta = "0.00001";
                writer.println(
                    "assertEquals(" + className + ".get" + variable.substring(0, 1).toUpperCase()
                        + variable.substring(1) + "(), " + variable + ", " + delta
                        + ", \"Erreur sur l'accesseur en lecture suivant : " + variable + " !\");");
              } else {
                writer.println("assertEquals(" + className + ".get"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + " , \"Erreur sur l'accesseur en lecture suivant : " + variable
                    + " !\");");
              }
            } else {
              if (type.equals("Double") || type.equals("Float") || type.equals("double")
                  || type.equals("float")) {
                String delta = "0.00001";

                writer.println("assertEquals(" + className + ".get"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + ", " + delta + ", \"Erreur sur l'accesseur en écriture suivant : "
                    + variable + " !\");");
              } else {
                writer.println("assertEquals(" + className + ".get"
                    + variable.substring(0, 1).toUpperCase() + variable.substring(1) + "(), "
                    + variable + " , \"Erreur sur l'accesseur en écriture suivant : " + variable
                    + " !\");");
              }
            }
          }
        }
      }
    } else {
      if (afficherTest == false) {
        writer.println("//TODO : FAIRE UN TEST !");
        afficherTest = true;
      }
    }
    if (methode.contains("static") && methode.contains("void")) {
      if (afficherTest == false) {
        writer.println("//TODO : FAIRE UN TEST !");
        afficherTest = true;
      }
    }
    if (methode.indexOf("(") + 1 != methode.indexOf(")")) {
      if (afficherTest == false) {
        writer.println("//TODO : FAIRE UN TEST !");
        afficherTest = true;
      }
    }
    writer.println("}");
  }

  public String getFichier() {
    return fichier;
  }

  public void setFichier(String fichier) {
    this.fichier = fichier;
  }

  public StringBuilder getPath() {
    return path;
  }

  public void setPath(StringBuilder path) {
    this.path = path;
  }

}
