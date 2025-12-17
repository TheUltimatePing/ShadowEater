package com.shadowEater;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ShadowApp extends JFrame {

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon("src/image/logo.jpg").getImage());
        setTitle("Shadow Eater converter");

        setContentPane(gui.getGUI());
        setVisible(true);
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new ShadowApp();
        });
    }
}