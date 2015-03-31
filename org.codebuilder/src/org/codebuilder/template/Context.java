package org.codebuilder.template;

import org.apache.velocity.VelocityContext;

public class Context
    extends VelocityContext
    implements Cloneable {

  public Context() {
    super();
  }

  public Context(Context context) {
    super(context);
  }

  public Object clone() {
    return super.clone(); // field-by-field copy
  }

  public Object put(String key, Object value){
    super.put(key, value);
    return "";
  }

}
