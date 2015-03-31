package org.codebuilder.template.introspection;

import org.apache.velocity.util.introspection.VelPropertySet;

public interface DynamicPropertySet extends VelPropertySet, DynamicFeature{

    public boolean accept(Object obj, String name, Object value);

}
