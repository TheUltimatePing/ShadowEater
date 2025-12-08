package com.shadowEater;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// used to select the colors
public class ShadowColors extends JFrame {
    ShadowColors() {
        // same image as the main frame
        ImageIcon appIcon = new ImageIcon("src/image/logo.jpg");
        String titleName = "Shadow Eater color selector";
        Border paidBorder;

        paidBorder = BorderFactory.createLineBorder(Color.getColor("D3AF37")); // set the color to golden

        // the panel of the screen
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(64, 2)); // 64 to be sure

        WPlaceColor[] colors = WPlaceColor.values();

        // creation of the button colors
        //we will have 2 "groups" for paid and free colors
        for (WPlaceColor color : colors) {
            JButton btn = new JButton(color.getName());
            btn.setBackground(new Color(color.getColor()));
            btn.setName(color.getName());

            if (color.getIsPaid()) {
                btn.setBorder(paidBorder);
            }

            // we add each button to the pannel
            colorPanel.add(btn);
        }

        colorPanel.revalidate();
        colorPanel.repaint();

        // what do the cross does
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // automatically set the page to full size
        setSize(500, 500);
        // set the icon to the app icon
        setIconImage(appIcon.getImage());
        // set the name
        setName(titleName);
        // render the window
        setVisible(true);
    }
}