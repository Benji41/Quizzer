/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Benjimon41
 */
public class Celda extends JLabel {

    public JLabel cell;

    public Celda(JLabel panel) {
        this.cell = panel;
        this.cell.setSize(30, 30);
        this.cell.setOpaque(true);
        this.cell.setBackground(Color.green);
        this.cell.setBorder(new LineBorder(Color.BLACK));
    }

    public void asignarImagen(int SE) throws IOException {
        if (SE == 0) {
            URL path = getClass().getResource("/resource/start.png");
            System.out.println(path + "1");
            BufferedImage myPicture = ImageIO.read(new File(path.getFile().substring(1)));
            cell.setIcon(new ImageIcon(myPicture));
        }
        if (SE == 1) {
            URL path = getClass().getResource("/resource/meta.png");
            System.out.println(path + "2");
            BufferedImage myPicture = ImageIO.read(new File(path.getFile().substring(1)));
            cell.setIcon(new ImageIcon(myPicture));
        }
    }

    public void asignarColor(int color) {
        if (color == 0) {
            cell.setBackground(Color.yellow);
        }
        if (color == 1) {
            cell.setBackground(Color.yellow);
        }
    }

    public void asignarPosicionPanel(int x, int y) {
        cell.setLocation(x, y);
    }

    public Point getPanelPosicion() {
        return cell.getLocation();
    }
}
