package org.codebuilder.common.text;

import java.text.StringCharacterIterator;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Escaper {
  public Escaper() {
  }

  public static String escapeRegularExpression(String aRegexFragment){
      final StringBuffer result = new StringBuffer();

      final StringCharacterIterator iterator = new StringCharacterIterator(aRegexFragment);
      char character =  iterator.current();
      while (character != StringCharacterIterator.DONE ){
        /*
        * All literals need to have backslashes doubled.
        */
        if (character == '.') {
          result.append("\\.");
        }
        else if (character == '\\') {
          result.append("\\\\");
        }
        else if (character == '?') {
          result.append("\\?");
        }
        else if (character == '*') {
          result.append("\\*");
        }
        else if (character == '+') {
          result.append("\\+");
        }
        else if (character == '&') {
          result.append("\\&");
        }
        else if (character == ':') {
          result.append("\\:");
        }
        else if (character == '{') {
          result.append("\\{");
        }
        else if (character == '}') {
          result.append("\\}");
        }
        else if (character == '[') {
          result.append("\\[");
        }
        else if (character == ']') {
          result.append("\\]");
        }
        else if (character == '(') {
          result.append("\\(");
        }
        else if (character == ')') {
          result.append("\\)");
        }
        else if (character == '^') {
          result.append("\\^");
        }
        else if (character == '$') {
          result.append("\\$");
        }
        else {
          //the char is not a special one
          //add it to the result as is
          result.append(character);
        }
        character = iterator.next();
      }
      return result.toString();
    }
}
