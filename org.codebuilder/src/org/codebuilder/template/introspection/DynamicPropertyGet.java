package org.codebuilder.template.introspection;

import org.apache.velocity.util.introspection.VelPropertyGet;

public interface DynamicPropertyGet extends VelPropertyGet, DynamicFeature{

    public boolean accept(Object obj, String name);

}
