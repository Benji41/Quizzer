/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

/**
 *
 * @author Benjimon41
 */
public class Celda extends JLabel {

    public JLabel cell;
    public int valor;
    public Jugador player;
    public int[] pos = new int[2];

    public Celda(JLabel panel, int valor) {
        this.cell = panel;
        this.valor = valor;
        this.cell.setSize(30, 30);
        this.cell.setFont(this.cell.getFont().deriveFont(Font.BOLD, this.cell.getFont().getSize()));
        this.cell.setOpaque(true);
    }

    public Celda(JLabel panel, int valor, Jugador player) {
        this.cell = panel;
        this.valor = valor;
        this.player = player;
    }

    public void asignarColor(int color) {
        if (color == 0) {
            this.cell.setBackground(Color.LIGHT_GRAY);
        }
        if (color == 1) {
            this.cell.setBackground(Color.decode("#05AFF2"));
        }

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
        if (SE == 2) {
            URL path = getClass().getResource("/resource/ignorancia.png");
            BufferedImage myPicture = ImageIO.read(new File(path.getFile().substring(1)));
            cell.setIcon(new ImageIcon(myPicture));
        }
        if (SE == 3) {
//            URL path = getClass().getResource("/resource/player1.png");
            URL path = getClass().getResource("/resource/p1.png");
            BufferedImage myPicture = ImageIO.read(new File(path.getFile().substring(1)));
            cell.setIcon(new ImageIcon(myPicture));
        }

        if (SE == 4) {
//            URL path = getClass().getResource("/resource/player2.png");
            URL path = getClass().getResource("/resource/p2.png");
            BufferedImage myPicture = ImageIO.read(new File(path.getFile().substring(1)));
            cell.setIcon(new ImageIcon(myPicture));
        }

        if (SE == 5) {
//            URL path = getClass().getResource("/resource/player3.png");
            URL path = getClass().getResource("/resource/p3.png");
            BufferedImage myPicture = ImageIO.read(new File(path.getFile().substring(1)));
            cell.setIcon(new ImageIcon(myPicture));
        }

    }

    public void asignarPosicionPanel(int x, int y) {
        cell.setLocation(x, y);
    }

    public int[] getPanelPosicion() {
        pos[0] = cell.getLocation().x;
        pos[1] = cell.getLocation().y;
        return pos;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Jugador getPlayer() {
        return player;
    }

    public void setPlayer(Jugador player) {
        this.player = player;
    }

}
