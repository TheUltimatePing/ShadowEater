package com.shadowEater;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Locale;

public class ShadowFileInput {
    public static JFileChooser
    getFileChooser(String root_dir, String files_desc, String... exts) {
        JFileChooser jfc;

        // we look if we have a directory
        if (root_dir == null || root_dir.trim().isEmpty()) {
            jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        } else {
            jfc = new JFileChooser(new File(root_dir));
        }

        jfc.setLocale(Locale.ENGLISH);
        jfc.setAcceptAllFileFilterUsed(false); // we only want our file with X extension not all

        if (exts.length > 0) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(files_desc, exts);
            jfc.addChoosableFileFilter(filter);

            jfc.setFileFilter(filter);
        }

        return (jfc);
    }
}
