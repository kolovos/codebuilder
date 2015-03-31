package org.codebuilder.ide.ui.swing.filefilters;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ProjectFileFilter extends FileFilter {

    //Accept all directories and all project files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        return f.getAbsolutePath().endsWith(".cbp");
    }

    //The description of this filter
    public String getDescription() {
        return "CodeBuilder Studio Projects";
    }
}
