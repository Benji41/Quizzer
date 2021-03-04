/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizzer;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import objetos.Celda;
import objetos.Jugador;
import objetos.Pregunta;
import objetos.StopWatch;

/**
 *
 * @author Benjimon41
 */
public class VJugar extends javax.swing.JFrame {

    //exterior
    int tipo;
    int n;
    ArrayList<Jugador> players = new ArrayList<>();
    Connection con;
    ArrayList<objetos.Pregunta> preguntas;
    ArrayList<String> categorias;
    //tablero y celdas
    Celda[][] grid;
    Map<Integer, int[]> hash = new HashMap<>();
    JLayeredPane lp = new JLayeredPane();
    int[] offset = new int[5];
    int[] offsetOut = new int[5];
    Celda lbScoreApodo1;
    Celda lbScoreScore1;
    Celda lbScoreApodo2;
    Celda lbScoreScore2;
    Celda lbScoreApodo3;
    Celda lbScoreScore3;
    Celda cP1;
    Celda cP2;
    Celda cP3;
    ArrayList<Celda> celdasPlayers = new ArrayList<>();
    //partida
    boolean partida = false;
    ArrayList<objetos.Pregunta> preguntasCategoria;

    public VJugar() throws IOException {
    }

    VJugar(int celdas, ArrayList<Jugador> players, int tipo, ArrayList<Pregunta> preguntas, ArrayList<String> categorias, Connection con) throws IOException {
        initComponents();
        this.n = celdas;
        this.players = players;
        this.tipo = tipo;
        this.preguntas = preguntas;
        this.categorias = categorias;
        this.con = con;
        //inicial
        offset[0] = 30;
        offset[1] = 50;
        offset[2] = 70;
        offset[3] = 20;
        offset[4] = 78;
        //final
        offsetOut[0] = 7;
        offsetOut[1] = 27;
        offsetOut[2] = 47;
        offsetOut[3] = 5;
        offsetOut[4] = 35;
        //players
        grid = new Celda[(int) Math.sqrt(n)][(int) Math.sqrt(n)];
        //frame
        setDefaultCloseOperation(VJugar.EXIT_ON_CLOSE);
        //layered
        cargarFrame(n);
        this.add(lp);
        this.cargarScore(n);
        //tablero
        this.dibujarTabla(n);
        this.cargarValores(n);
        this.cargarJugadores();
        for (Celda cp : celdasPlayers) {
            System.out.println(cp.cell + " " + cp.player);
        }
        this.moverJugador(cP1, 11);
        this.moverJugador(cP3, 11);
    }

    public void cargarFrame(int celdas) {
        switch (celdas) {
            case 9:
                this.setSize(600, 650);
                lp.setBounds(170, 20, 260, 150);
                break;

            case 16:
                this.setSize(600, 650);
                lp.setBounds(135, 20, 330, 200);

                break;

            case 49:
                this.setSize(800, 715);
                lp.setBounds(128, 20, 540, 350);
                break;

            case 64:
                this.setSize(800, 730);
                lp.setBounds(92, 5, 610, 400);
                break;

        }
    }

    public void cargarScore(int celdas) {
        switch (celdas) {
            case 9:
                for (Jugador p : players) {
                    int n = p.getNumero();
                    if (n == 1) {
                        lbScoreApodo1 = new Celda(new JLabel(), 0, p);
                        lbScoreScore1 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo1.cell.setBounds(430, 20, 120, 20);
                        lbScoreApodo1.cell.setOpaque(true);
                        lbScoreApodo1.cell.setBackground(Color.green);
                        lbScoreScore1.cell.setBackground(Color.green);
                        lbScoreApodo1.cell.setText(lbScoreApodo1.player.getApodo());
                        lbScoreScore1.cell.setBounds(550, 20, 80, 20);
                        lbScoreScore1.cell.setOpaque(true);
                        lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());
                        this.add(lbScoreApodo1.cell);
                        this.add(lbScoreScore1.cell);
                    }
                    if (n == 2) {
                        System.out.println("entro2");
                        lbScoreApodo2 = new Celda(new JLabel(), 0, p);
                        lbScoreScore2 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo2.cell.setBounds(430, 40, 120, 20);
                        lbScoreApodo2.cell.setOpaque(true);
                        lbScoreApodo2.cell.setBackground(Color.red);
                        lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());
                        lbScoreScore2.cell.setBounds(550, 40, 80, 20);
                        lbScoreScore2.cell.setOpaque(true);
                        lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());
                        this.add(lbScoreApodo2.cell);
                        this.add(lbScoreScore2.cell);
                    }
                    if (n == 3) {
                        System.out.println("entro3");
                        lbScoreApodo3 = new Celda(new JLabel(), 0, p);
                        lbScoreScore3 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo3.cell.setBounds(430, 60, 120, 20);
                        lbScoreApodo3.cell.setOpaque(true);
                        lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());
                        lbScoreScore3.cell.setBounds(550, 60, 80, 20);
                        lbScoreScore3.cell.setOpaque(true);
                        lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());
                        this.add(lbScoreApodo3.cell);
                        this.add(lbScoreScore3.cell);
                    }
                }
                break;

            case 16:
                for (Jugador p : players) {
                    int n = p.getNumero();
                    if (n == 1) {
                        lbScoreApodo1 = new Celda(new JLabel(), 0, p);
                        lbScoreScore1 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo1.cell.setBounds(430, 20, 120, 20);
                        lbScoreApodo1.cell.setOpaque(true);
                        lbScoreApodo1.cell.setBackground(Color.green);
                        lbScoreScore1.cell.setBackground(Color.green);
                        lbScoreApodo1.cell.setText(lbScoreApodo1.player.getApodo());
                        lbScoreScore1.cell.setBounds(550, 20, 80, 20);
                        lbScoreScore1.cell.setOpaque(true);
                        lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());
                        this.add(lbScoreApodo1.cell);
                        this.add(lbScoreScore1.cell);
                    }
                    if (n == 2) {
                        System.out.println("entro2");
                        lbScoreApodo2 = new Celda(new JLabel(), 0, p);
                        lbScoreScore2 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo2.cell.setBounds(430, 40, 120, 20);
                        lbScoreApodo2.cell.setOpaque(true);
                        lbScoreApodo2.cell.setBackground(Color.red);
                        lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());
                        lbScoreScore2.cell.setBounds(550, 40, 80, 20);
                        lbScoreScore2.cell.setOpaque(true);
                        lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());
                        this.add(lbScoreApodo2.cell);
                        this.add(lbScoreScore2.cell);
                    }
                    if (n == 3) {
                        System.out.println("entro3");
                        lbScoreApodo3 = new Celda(new JLabel(), 0, p);
                        lbScoreScore3 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo3.cell.setBounds(430, 60, 120, 20);
                        lbScoreApodo3.cell.setOpaque(true);
                        lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());
                        lbScoreScore3.cell.setBounds(550, 60, 80, 20);
                        lbScoreScore3.cell.setOpaque(true);
                        lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());
                        this.add(lbScoreApodo3.cell);
                        this.add(lbScoreScore3.cell);
                    }
                }
                break;

            case 49:
                for (Jugador p : players) {
                    int n = p.getNumero();
                    if (n == 1) {
                        lbScoreApodo1 = new Celda(new JLabel(), 0, p);
                        lbScoreScore1 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo1.cell.setBounds(650, 20, 80, 20);
                        lbScoreApodo1.cell.setOpaque(true);
                        lbScoreApodo1.cell.setBackground(Color.green);
                        lbScoreScore1.cell.setBackground(Color.blue);
                        lbScoreApodo1.cell.setText(lbScoreApodo1.player.getApodo());
                        lbScoreScore1.cell.setBounds(730, 20, 30, 20);
                        lbScoreScore1.cell.setOpaque(true);
                        lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());
                        this.add(lbScoreApodo1.cell);
                        this.add(lbScoreScore1.cell);
                    }
                    if (n == 2) {
                        System.out.println("entro2");
                        lbScoreApodo2 = new Celda(new JLabel(), 0, p);
                        lbScoreScore2 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo2.cell.setBounds(650, 40, 80, 20);
                        lbScoreApodo2.cell.setOpaque(true);
                        lbScoreApodo2.cell.setBackground(Color.red);
                        lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());
                        lbScoreScore2.cell.setBounds(730, 40, 30, 20);
                        lbScoreScore2.cell.setOpaque(true);
                        lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());
                        this.add(lbScoreApodo2.cell);
                        this.add(lbScoreScore2.cell);
                    }
                    if (n == 3) {
                        System.out.println("entro3");
                        lbScoreApodo3 = new Celda(new JLabel(), 0, p);
                        lbScoreScore3 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo3.cell.setBounds(650, 60, 80, 20);
                        lbScoreApodo3.cell.setOpaque(true);
                        lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());
                        lbScoreScore3.cell.setBounds(730, 60, 30, 20);
                        lbScoreScore3.cell.setOpaque(true);
                        lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());
                        this.add(lbScoreApodo3.cell);
                        this.add(lbScoreScore3.cell);
                    }
                }
                break;

            case 64:
                for (Jugador p : players) {
                    int n = p.getNumero();
                    if (n == 1) {
                        lbScoreApodo1 = new Celda(new JLabel(), 0, p);
                        lbScoreScore1 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo1.cell.setBounds(670, 20, 80, 20);
                        lbScoreApodo1.cell.setOpaque(true);
                        lbScoreApodo1.cell.setBackground(Color.green);
                        lbScoreScore1.cell.setBackground(Color.blue);
                        lbScoreApodo1.cell.setText(lbScoreApodo1.player.getApodo());
                        lbScoreScore1.cell.setBounds(750, 20, 30, 20);
                        lbScoreScore1.cell.setOpaque(true);
                        lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());
                        this.add(lbScoreApodo1.cell);
                        this.add(lbScoreScore1.cell);
                    }
                    if (n == 2) {
                        System.out.println("entro2");
                        lbScoreApodo2 = new Celda(new JLabel(), 0, p);
                        lbScoreScore2 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo2.cell.setBounds(670, 40, 80, 20);
                        lbScoreApodo2.cell.setOpaque(true);
                        lbScoreApodo2.cell.setBackground(Color.red);
                        lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());
                        lbScoreScore2.cell.setBounds(750, 40, 30, 20);
                        lbScoreScore2.cell.setOpaque(true);
                        lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());
                        this.add(lbScoreApodo2.cell);
                        this.add(lbScoreScore2.cell);
                    }
                    if (n == 3) {
                        System.out.println("entro3");
                        lbScoreApodo3 = new Celda(new JLabel(), 0, p);
                        lbScoreScore3 = new Celda(new JLabel(), 0, p);
                        lbScoreApodo3.cell.setBounds(670, 60, 80, 20);
                        lbScoreApodo3.cell.setOpaque(true);
                        lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());
                        lbScoreScore3.cell.setBounds(750, 60, 30, 20);
                        lbScoreScore3.cell.setOpaque(true);
                        lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());
                        this.add(lbScoreApodo3.cell);
                        this.add(lbScoreScore3.cell);
                    }
                }
                break;

        }

    }

    public void dibujarTabla(int n) {
        int co = 0;
        for (int i = 0; i < Math.sqrt(n); i++) {
            for (int j = 0; j < Math.sqrt(n); j++) {
                if (i == Math.sqrt(n) - 1 && j == Math.sqrt(n) - 1) {
                    Celda c = new Celda(new JLabel(), 0);
                    c.cell.setBounds(j * 70, i * 50, 120, 50);
                    grid[i][j] = c;
                    lp.add(c.cell, Integer.valueOf(0));
                } else {
                    Celda c = new Celda(new JLabel(), 0);
                    c.cell.setBounds(j * 70, i * 50, 70, 50);
                    grid[i][j] = c;
                    lp.add(c.cell, Integer.valueOf(0));
                }
            }
        }
    }

    public void cargarValores(int n) throws IOException {
        int value = 1;

        int minCol = (int) (Math.sqrt(n) - 1);
        int minRow = (int) (Math.sqrt(n) - 1);

        int maxCol = 0;
        int maxRow = 0;

        while (value <= n) {
            int co = 0;
            int coF = 1;
            for (int i = minCol; i >= maxCol; i--) {
                grid[minRow][i].setValor(value);
                this.direccion(value, grid[minRow][i].getPanelPosicion());
                if (value % 5 == 0) {
                    grid[minRow][i].cell.setText(String.valueOf(value));
                }
                if (value == 1) {
                    grid[minRow][i].asignarImagen(0);
                    grid[minRow][i].cell.setBackground(Color.white);
                    grid[minRow][i].cell.setBorder(new LineBorder(Color.yellow));
                }
                if (value == n) {
                    grid[minRow][i].asignarImagen(1);
                }
                if (value != 1 && value >= Math.sqrt(n) + 1) {
                    if (co == 0) {
                        grid[minRow][i].asignarColor(co);
                        co++;
                    } else {
                        grid[minRow][i].asignarColor(co);
                        co = 0;
                    }
                } else if (value != 1) {
                    if (coF == 0) {
                        grid[minRow][i].asignarColor(coF);
                        coF++;
                    } else {
                        grid[minRow][i].asignarColor(coF);
                        coF = 0;
                    }

                }
                value++;
            }
            co = 0;
            for (int i = minRow - 1; i >= maxRow; i--) {
                grid[i][maxCol].setValor(value);
                if (value % 5 == 0) {
                    grid[i][maxCol].cell.setText(String.valueOf(value));
                }
                this.direccion(value, grid[i][maxCol].getPanelPosicion());
                if (value == n) {
                    grid[i][maxCol].asignarImagen(1);
                }
                if (co == 0) {
                    grid[i][maxCol].asignarColor(co);
                    co++;
                } else {
                    grid[i][maxCol].asignarColor(co);
                    co = 0;
                }
                value++;
            }
            co = 1;
            for (int i = maxCol + 1; i <= minCol; i++) {
                grid[maxRow][i].setValor(value);
                if (value % 5 == 0) {
                    grid[maxRow][i].cell.setText(String.valueOf(value));
                }
                this.direccion(value, grid[maxRow][i].getPanelPosicion());
                if (value == n) {
                    grid[maxRow][i].asignarImagen(1);
                }
                if (co == 0) {
                    grid[maxRow][i].asignarColor(co);
                    co++;
                } else {
                    grid[maxRow][i].asignarColor(co);
                    co = 0;
                }
                value++;
            }
            co = 0;
            for (int i = maxRow + 1; i <= minRow - 1; i++) {
                grid[i][minCol].setValor(value);
                if (value % 5 == 0) {
                    grid[i][minCol].cell.setText(String.valueOf(value));
                }
                this.direccion(value, grid[i][minCol].getPanelPosicion());
                if (value == n) {
                    grid[i][minCol].asignarImagen(1);
                }
                if (co == 0) {
                    grid[i][minCol].asignarColor(co);
                    co++;
                } else {
                    grid[i][minCol].asignarColor(co);
                    co = 0;
                }
                value++;
            }

            minCol--;

            minRow--;

            maxCol++;

            maxRow++;
        }
    }

    public void direccion(int valor, int[] posicion) {
        hash.put(valor, posicion);
    }

    public void cargarJugadores() throws IOException {
        if (tipo == 0) {
            cP1 = new Celda(new JLabel(), 1, players.get(0));
            cP1.asignarImagen(3);
            cP1.cell.setBounds(hash.get(1)[0] + offset[0], hash.get(1)[1] + offset[3], 30, 30);
            lp.add(cP1.cell, Integer.valueOf(1));
            this.celdasPlayers.add(cP1);
            cP3 = new Celda(new JLabel(), 1, new Jugador("",0,4));
            cP3.asignarImagen(2);
            cP3.cell.setBounds(hash.get(1)[0] + offset[2], hash.get(1)[1] + offset[3], 30, 30);
            lp.add(cP3.cell, Integer.valueOf(1));
            this.celdasPlayers.add(cP3);
        } else {
            for (Jugador player : players) {
                if (player.getNumero() == 1) {
                    cP1 = new Celda(new JLabel(), 1, player);
                    cP1.asignarImagen(3);
                    cP1.cell.setBounds(hash.get(1)[0] + offset[0], hash.get(1)[1] + offset[3], 30, 30);
                    lp.add(cP1.cell, Integer.valueOf(1));
                    this.celdasPlayers.add(cP1);
                }
                if (player.getNumero() == 2) {
                    cP2 = new Celda(new JLabel(), 1, player);
                    cP2.asignarImagen(4);
                    cP2.cell.setBounds(hash.get(1)[0] + offset[1], hash.get(1)[1] + offset[3], 30, 30);
                    lp.add(cP2.cell, Integer.valueOf(1));
                    this.celdasPlayers.add(cP2);
                }
                if (player.getNumero() == 3) {
                    cP3 = new Celda(new JLabel(), 1, player);
                    cP3.asignarImagen(5);
                    cP3.cell.setBounds(hash.get(1)[0] + offset[2], hash.get(1)[1] + offset[3], 30, 30);
                    lp.add(cP3.cell, Integer.valueOf(1));
                    this.celdasPlayers.add(cP3);
                }
            }
        }
    }

    public void moverJugador(Celda c, int nuevoValor) {
        if (nuevoValor < 1) {
            nuevoValor = 1;
        }
        if (nuevoValor > n) {
            nuevoValor = n;
        }
        int num = c.player.getNumero();
        if (num == 4) {
            c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + 20, 30, 30);
        } else {
            num--;
            if (num != 1) {
                c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + 20, 30, 30);
            } else {
                c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + offsetOut[3], 30, 30);
            }
        }
//        num--;
//        if (num != 1) {
//            c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + 20, 30, 30);
//        } else {
//            c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + offsetOut[3], 30, 30);
//        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VJugar().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
