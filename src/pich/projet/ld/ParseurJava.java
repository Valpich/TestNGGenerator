package pich.projet.ld;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ParseurJava {

  public ArrayList<String> recupererConstructeur(String path) {
    ArrayList<String> constructeurs = new ArrayList<String>();
    CompilationUnit cu = null;
    try {
      cu = JavaParser.parse(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    List<TypeDeclaration> typeDeclarations = cu.getTypes();
    for (TypeDeclaration typeDec : typeDeclarations) {
      List<BodyDeclaration> members = typeDec.getMembers();
      if (members != null) {
        for (BodyDeclaration member : members) {
          if (member instanceof ConstructorDeclaration) {
            ConstructorDeclaration constructor = (ConstructorDeclaration) member;
            constructor.getBeginLine();
            constructor.getBlock();
            constructeurs.add(constructor.getDeclarationAsString());
          }
        }
      }
    }
    return constructeurs;
  }

  public ArrayList<String> recupererMethodes(String path) {
    ArrayList<String> methodes = new ArrayList<String>();
    CompilationUnit cu = null;
    try {
      cu = JavaParser.parse(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    List<TypeDeclaration> typeDeclarations = cu.getTypes();
    for (TypeDeclaration typeDec : typeDeclarations) {
      List<BodyDeclaration> members = typeDec.getMembers();
      if (members != null) {
        for (BodyDeclaration member : members) {
          if (member instanceof MethodDeclaration) {
            MethodDeclaration methode = (MethodDeclaration) member;
            methodes.add(methode.getDeclarationAsString());
          }
        }
      }
    }
    return methodes;
  }


}
