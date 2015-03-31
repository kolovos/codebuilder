package org.codebuilder.template.introspection;

import org.apache.velocity.util.introspection.VelMethod;
import java.util.List;

public interface DynamicMethod
    extends VelMethod, DynamicFeature{

  public List getParameters();
  public boolean accept(Object obj);
  public boolean accept(Object obj, String name, Object[] args);
  public String getSignature();
}
