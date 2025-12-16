package com.shadowEater;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static com.shadowEater.ShadowFileInput.getFileChooser;

public class ShadowApp extends JFrame {
    private ShadowAppGUI gui;

    public static ShadowImage image;
    private static boolean[] choice = new boolean[64];
    {
        Arrays.fill(choice, true); // fill all the array with true (all the colors are selected by default)
    }

    public static boolean[] getChoice() {
        return choice;
    }

    public static void setChoice(boolean[] copy) {
        choice = copy;
    }

    public static void setChoice(int pos, boolean state) {
        // used to change the state of the user choices list
        choice[pos] = state;
    }

    // constructor
    public ShadowApp() {
        ShadowAppGUI gui = new ShadowAppGUI();
        // TODO : add safer filepath
        // File fileSafe = new File("tmp"+File.separator+"abc.txt");
        // safer
    }

    public static void main(String[] args) {
        // set the style to the default system one
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // we start the app
        SwingUtilities.invokeLater(() -> {
            ShadowApp app = new ShadowApp();
            // we bind the app to the main pannel
            app.setContentPane(app.gui.getGUI());
        });
    }
}