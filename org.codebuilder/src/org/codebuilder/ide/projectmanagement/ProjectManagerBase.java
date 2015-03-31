package org.codebuilder.ide.projectmanagement;

import java.util.ArrayList;
import java.util.ListIterator;

public class ProjectManagerBase {

  private ArrayList projectChangeListeners = new ArrayList();

  public ProjectManagerBase() {
  }

  public void addProjectChangeListener(ProjectChangeListener listener) {
    projectChangeListeners.add(listener);
  }

  public void removeProjectChangeListener(ProjectChangeListener listener) {
    projectChangeListeners.remove(listener);
  }

  public void notifyProjectChangeListeners(){

    Throwable throwable = new Throwable();
    StackTraceElement[] stack = throwable.getStackTrace();
    for (int i = 1; i< stack.length; i++){
      if (stack[i].toString().indexOf("notifyProjectChangeListeners") > 0)
        return;
    }

    ListIterator li = projectChangeListeners.listIterator();
    while(li.hasNext()){
      ((ProjectChangeListener) li.next()).projectChanged();
    }
  }
}
