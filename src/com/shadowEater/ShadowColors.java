package com.shadowEater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

// used to select the colors
public class ShadowColors {

    final static WPlaceColor[] colors = WPlaceColor.values();
    private static boolean[] choiceCopy = ShadowApp.getChoice();

    ShadowColors() {
        // same image as the main frame
        ImageIcon appIcon = new ImageIcon("src/image/logo.jpg");
        String titleName = "Shadow Eater color selector";

        JFrame frame = new JFrame();

        // the color panel of the window
        // TODO : make both of them appear
        JPanel colorPanel = getColorJPanel();
        JPanel buttonPanel = getButtonJPanel(frame);

        // we set the panel to the frame
        frame.setLayout(new BorderLayout());
        frame.add(colorPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // what do the cross does
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // automatically set the page to full size
        frame.setSize(1500, 800);
        // set the icon to the app icon
        frame.setIconImage(appIcon.getImage());
        // set the name of the window on windows
        frame.setTitle(titleName);
        // set the name of the window on linux for some reason
        frame.setName(titleName);
        // render the window
        frame.setVisible(true);
    }

    private static JPanel getColorJPanel() {
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(0, 4));

        // creation of the checkboxes colors
        //we will have 2 "groups" for paid and free colors
        int i = 0; // used to position us

        for (WPlaceColor color : colors) {

            JCheckBox checkBox = getJCheckBox(color, i);
            colorPanel.add(checkBox);
            i++;
        }

        colorPanel.revalidate();
        colorPanel.repaint();

        return colorPanel;
    }

    private static JPanel getButtonJPanel(JFrame frame) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton cancel = new JButton("cancel");
        JButton ok = new JButton("ok");

        buttonPanel.add(cancel);
        buttonPanel.add(ok);

        cancel.addActionListener(_ -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
        ok.addActionListener(_ -> {
            // we save the change
            ShadowApp.setChoice(choiceCopy);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });

        return buttonPanel;

    }

    private static JCheckBox getJCheckBox(WPlaceColor color, int i) {
        JCheckBox checkBox = new JCheckBox(color.getName(), ShadowApp.getChoice()[i]);
        checkBox.setBackground(new Color(color.getColor()));
        checkBox.setName(color.getName());
        checkBox.setMargin(new Insets(0, 125, 0, 0));

        checkBox.addActionListener(_ -> toggleChoice(i)); // if the user change the state of a button we update the copied list

        if (color.getIsPaid()) {
            checkBox.setForeground(new Color(0xD3, 0xAF, 0x37));
        }

        return checkBox;
    }

    private static void toggleChoice(int pos) {
        choiceCopy[pos] = !choiceCopy[pos];
    }
}