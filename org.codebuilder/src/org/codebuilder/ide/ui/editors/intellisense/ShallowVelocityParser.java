package org.codebuilder.ide.ui.editors.intellisense;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ListIterator;

public class ShallowVelocityParser {

  private String text = "";
  private ArrayList variables = new ArrayList();
  private ArrayList macros = new ArrayList();
  Pattern setVariablePattern = Pattern.compile(".*?#set\\s*\\(\\$(.*?)\\s+.*?", Pattern.DOTALL);
  Pattern foreachVariablePattern = Pattern.compile(".*?#foreach\\s*\\(\\$(.*?)\\s+.*?",Pattern.DOTALL);

  public ShallowVelocityParser() {

  }

  public void parse(String text){
    variables.clear();
    macros.clear();
    Matcher setVariableMatcher = setVariablePattern.matcher(text);
    while (setVariableMatcher.find()){
      String variable = setVariableMatcher.group(1);
      if (!variables.contains(variable) && variable.compareTo("") != 0)
        variables.add(variable);
    }
    Matcher foreachVariableMatcher = foreachVariablePattern.matcher(text);
    while (foreachVariableMatcher.find()){
      String variable = foreachVariableMatcher.group(1);
      if (!variables.contains(variable) && variable.compareTo("") != 0)
        variables.add(variable);
    }
  }

  public ArrayList getMacros() {
    return macros;
  }

  public ArrayList getVariables() {
    return variables;
  }

  public static void main(String[] args){
    ShallowVelocityParser parser= new ShallowVelocityParser();
    parser.parse("#set($a = $b)\n#set($c = $d)");
    ListIterator li = parser.getVariables().listIterator();
  }

}
