package org.codebuilder.util;

/**
 * <p>Title: CodeBuilder</p>
 *


 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */
public class StringUtilities{
  public StringUtilities() {
  }

  public boolean isEmpty(Object obj){
     return obj == null || obj.toString().length() == 0;
  }

  public String removeSuffix(String str){
    if (str.indexOf(".") > -1){
      str = str.substring(0,str.indexOf("."));
    }
    return str;
  }

  public String concat (Object obj1 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 ,Object obj14 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (obj14 != null)
              retVal = retVal + obj14.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 ,Object obj14 ,Object obj15 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (obj14 != null)
              retVal = retVal + obj14.toString();
      if (obj15 != null)
              retVal = retVal + obj15.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 ,Object obj14 ,Object obj15 ,Object obj16 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (obj14 != null)
              retVal = retVal + obj14.toString();
      if (obj15 != null)
              retVal = retVal + obj15.toString();
      if (obj16 != null)
              retVal = retVal + obj16.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 ,Object obj14 ,Object obj15 ,Object obj16 ,Object obj17 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (obj14 != null)
              retVal = retVal + obj14.toString();
      if (obj15 != null)
              retVal = retVal + obj15.toString();
      if (obj16 != null)
              retVal = retVal + obj16.toString();
      if (obj17 != null)
              retVal = retVal + obj17.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 ,Object obj14 ,Object obj15 ,Object obj16 ,Object obj17 ,Object obj18 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (obj14 != null)
              retVal = retVal + obj14.toString();
      if (obj15 != null)
              retVal = retVal + obj15.toString();
      if (obj16 != null)
              retVal = retVal + obj16.toString();
      if (obj17 != null)
              retVal = retVal + obj17.toString();
      if (obj18 != null)
              retVal = retVal + obj18.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}
public String concat (Object obj1 ,Object obj2 ,Object obj3 ,Object obj4 ,Object obj5 ,Object obj6 ,Object obj7 ,Object obj8 ,Object obj9 ,Object obj10 ,Object obj11 ,Object obj12 ,Object obj13 ,Object obj14 ,Object obj15 ,Object obj16 ,Object obj17 ,Object obj18 ,Object obj19 , Object last){
      String retVal = "";
      if (obj1 != null)
              retVal = retVal + obj1.toString();
      if (obj2 != null)
              retVal = retVal + obj2.toString();
      if (obj3 != null)
              retVal = retVal + obj3.toString();
      if (obj4 != null)
              retVal = retVal + obj4.toString();
      if (obj5 != null)
              retVal = retVal + obj5.toString();
      if (obj6 != null)
              retVal = retVal + obj6.toString();
      if (obj7 != null)
              retVal = retVal + obj7.toString();
      if (obj8 != null)
              retVal = retVal + obj8.toString();
      if (obj9 != null)
              retVal = retVal + obj9.toString();
      if (obj10 != null)
              retVal = retVal + obj10.toString();
      if (obj11 != null)
              retVal = retVal + obj11.toString();
      if (obj12 != null)
              retVal = retVal + obj12.toString();
      if (obj13 != null)
              retVal = retVal + obj13.toString();
      if (obj14 != null)
              retVal = retVal + obj14.toString();
      if (obj15 != null)
              retVal = retVal + obj15.toString();
      if (obj16 != null)
              retVal = retVal + obj16.toString();
      if (obj17 != null)
              retVal = retVal + obj17.toString();
      if (obj18 != null)
              retVal = retVal + obj18.toString();
      if (obj19 != null)
              retVal = retVal + obj19.toString();
      if (last != null)
              retVal = retVal + last.toString();
      return retVal;
}

}
