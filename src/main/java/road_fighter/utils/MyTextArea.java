package road_fighter.utils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {

	private BufferedImage image;

    public MyTextArea() {
        super(20, 20);
        setOpaque(false);
        try {
            image = ImageIO.read(new File("src/main/resources/img/fondos/chat.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (image != null) {
            int x = 0;
            int y = 0;
            g2d.drawImage(image, x, y, this);    
        }
        super.paintComponent(g2d);
        g2d.dispose();
    }
}

