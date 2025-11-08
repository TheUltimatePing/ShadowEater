import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {

        // the text for the image
        JLabel convertedLabel = new JLabel();
        convertedLabel.setText( "<|°_°|>" );
        convertedLabel.setForeground( Color.black);
        convertedLabel.setSize(250, 500);

        // set the name image for the app
        ImageIcon appIcon = new ImageIcon("image/reginleif.jpg");
        String titleName = "Shadow Eater";

        // create the image to process
        BufferedImage image = ImageIO.read(new File("image/reginleif.jpg"));

        // create the array for the image
        int[][] imageArray = {};

        // if the image is not null we convert is to an array
        if (image != null) {
            imageArray = Converter.bufferedToArray(image);
        }

        // create the window
        JFrame mainWindow = new JFrame(titleName);

        // transform an array to a render able image
        BufferedImage img = Converter.arrayToBufferedImage(imageArray);
        // put the image in a label
        JLabel convertedImage = new JLabel(new ImageIcon(img));

        // what do the cross does
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // automatically set the page to full sized
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // set the icon to the app icon
        mainWindow.setIconImage(appIcon.getImage());

        // add the different element
        mainWindow.add(convertedLabel);
        mainWindow.add(convertedImage);

        // render the window
        mainWindow.setVisible(true);
    }
}