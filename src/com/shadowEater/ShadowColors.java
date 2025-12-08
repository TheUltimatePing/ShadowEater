package com.shadowEater;


import javax.swing.*;

// used to select the colors
public class ShadowColors extends JFrame {
    ShadowColors() {
        // same image as the main frame
        ImageIcon appIcon = new ImageIcon("src/image/logo.jpg");
        String titleName = "Shadow Eater color selector";

        WPlaceColor[] colors = WPlaceColor.values();
        // creation of the button colors
        for (WPlaceColor color : colors) {

        }

        // what do the cross does
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // automatically set the page to full sized
        setSize(50, 50);
        // set the icon to the app icon
        setIconImage(appIcon.getImage());
        // set the name
        setName(titleName);
        // render the window
        setVisible(true);
    }
}