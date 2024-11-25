package Game.Monster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Red_Ghost extends JPanel {

    Image image;

    public Red_Ghost()  {
        setBounds(260,200,20,20);
        setOpaque(false);
        loadImage();

    }

    public void loadImage() {
        try {
            // Ensure to use the correct path starting from the root of the classpath (e.g., "/img/img/redGhost.png")
            image = ImageIO.read(getClass().getResource("Game/img/redGhost.png"));
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }


    protected void painComponent(Graphics g){
        super.paintComponent(g);

        if (image != null){
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }




}
