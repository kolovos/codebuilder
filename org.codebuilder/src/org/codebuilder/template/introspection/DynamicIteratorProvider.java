package org.codebuilder.template.introspection;

import java.util.Iterator;
import org.apache.velocity.util.introspection.Info;

public interface DynamicIteratorProvider extends DynamicFeature{

  public Iterator getIterator(Object obj, Info i);

  public boolean accept(Object obj);
}
