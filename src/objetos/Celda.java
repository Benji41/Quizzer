/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
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
        this.cell.setSize(32, 32);
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
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/start.png"))));
        }
        if (SE == 1) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/meta.png"))));
        }
        if (SE == 2) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/ignorancia.png"))));
        }
        if (SE == 3) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/p1.png"))));
        }

        if (SE == 4) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/p2.png"))));
        }

        if (SE == 5) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/p3.png"))));
        }
        if (SE == 6) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/pistaV.png"))));
        }
        if (SE == 7) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/pistaH.png"))));
        }

    }

    public void asignarImagen(int SE, String img) throws IOException {
        if (SE == 0) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/start.png"))));
        }
        if (SE == 1) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/meta.png"))));
        }
        if (SE == 2) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/ignorancia.png"))));
        }
        if (SE == 3) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource(img))));
        }

        if (SE == 4) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource(img))));
        }

        if (SE == 5) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource(img))));
        }
        if (SE == 6) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/pistaV.png"))));
        }
        if (SE == 7) {
            cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/pistaH.png"))));
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
