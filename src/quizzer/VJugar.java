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
    JLabel jlScore = new JLabel();
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
    int tiempoTurno;
    ArrayList<Celda> celdasPlayers = new ArrayList<>();
    ArrayList<Celda> celdasPlayersScores = new ArrayList<>();
    //partida
    boolean partida = false;
    ArrayList<objetos.Pregunta> preguntasCategoria;

    public VJugar() throws IOException {
    }

    VJugar(int celdas, ArrayList<Jugador> players, int tipo, ArrayList<Pregunta> preguntas, ArrayList<String> categorias, Connection con, int tiempoTurno) throws IOException {
        initComponents();
        this.n = celdas;
        this.players = players;
        this.tipo = tipo;
        this.preguntas = preguntas;
        this.categorias = categorias;
        this.con = con;
        this.tiempoTurno = tiempoTurno;

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
        jlScore.setText("Score");
        this.lbA1.setVisible(false);
        this.lbA2.setVisible(false);
        this.lbA3.setVisible(false);
        this.lbS1.setVisible(false);
        this.lbS2.setVisible(false);
        this.lbS3.setVisible(false);
        //layered
        cargarFrame(n);
        this.add(lp);
        this.cargarScore();
        //tablero
        this.dibujarTabla(n);
        this.cargarValores(n);
        this.cargarJugadores();
    }

    public void cargarFrame(int celdas) {
        switch (celdas) {
            case 9:
                this.setSize(700, 400);
                lp.setBounds(170, 35, 260, 150);
                jlScore.setBounds(261, 190, 60, 20);
                this.panelDado.setLocation(480, 10);
                this.panelScores.setLocation(180, 220);
                this.add(jlScore);
                break;

            case 16:
                this.setSize(700, 400);
                lp.setBounds(135, 20, 330, 200);
                jlScore.setBounds(265, 230, 60, 20);
                this.panelDado.setLocation(495, 45);
                this.panelScores.setLocation(184, 260);
                this.add(jlScore);
                break;

            case 49:
                this.setSize(850, 520);
                lp.setBounds(128, 20, 540, 350);
                jlScore.setBounds(360, 380, 60, 20);
                this.panelDado.setLocation(650, 85);
                this.panelScores.setLocation(279, 410);
                this.add(jlScore);
                break;

            case 64:
                this.setSize(880, 560);
                lp.setBounds(92, 5, 610, 400);
                jlScore.setBounds(360, 420, 60, 20);
                this.panelDado.setLocation(680, 85);
                this.panelScores.setLocation(280, 440);
                this.add(jlScore);
                break;

        }
    }

    public void cargarScore() {

        for (Jugador p : players) {
            int n = p.getNumero();
            if (n == 1) {
                lbScoreApodo1 = new Celda(new JLabel(), 0, p);
                lbScoreApodo1.cell.setBounds(this.lbA1.getBounds());
                lbScoreApodo1.cell.setText(lbScoreApodo1.player.getApodo());
                this.panelScores.add(lbScoreApodo1.cell);
                
                lbScoreScore1 = new Celda(new JLabel(), 0, p);
                lbScoreScore1.cell.setBounds(this.lbS1.getBounds());
                lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());
                this.panelScores.add(lbScoreScore1.cell);
                celdasPlayersScores.add(lbScoreScore1);
            }
            if (n == 2) {
                lbScoreApodo2 = new Celda(new JLabel(), 0, p);
                lbScoreApodo2.cell.setBounds(this.lbA2.getBounds());
                lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());
                this.panelScores.add(lbScoreApodo2.cell);
                
                lbScoreScore2 = new Celda(new JLabel(), 0, p);
                lbScoreScore2.cell.setBounds(this.lbS2.getBounds());
                lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());
                this.panelScores.add(lbScoreScore2.cell);
                celdasPlayersScores.add(lbScoreScore2);
            }
            if (n == 3) {
                lbScoreApodo3 = new Celda(new JLabel(), 0, p);
                lbScoreApodo3.cell.setBounds(this.lbA3.getBounds());
                lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());
                this.panelScores.add(lbScoreApodo3.cell);
                
                lbScoreScore3 = new Celda(new JLabel(), 0, p);
                lbScoreScore3.cell.setBounds(this.lbS3.getBounds());
                lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());
                this.panelScores.add(lbScoreScore3.cell);
                celdasPlayersScores.add(lbScoreScore3);
            }
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
            cP3 = new Celda(new JLabel(), 1, new Jugador("", 0, 4));
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
        System.out.println("entro aqui en mover");
        if (nuevoValor < 1) {
            nuevoValor = 1;
            System.out.println("entro move1");
        }
        if (nuevoValor > n) {
            System.out.println(c.player);
            nuevoValor = n;
            System.out.println("entro move2");
        }

        int num = c.player.getNumero();
        System.out.println(num);
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

        panelDado = new javax.swing.JPanel();
        btnLanzarDado = new javax.swing.JButton();
        lbTurno = new javax.swing.JLabel();
        lbDado = new javax.swing.JLabel();
        lbCategoria = new javax.swing.JLabel();
        panelScores = new javax.swing.JPanel();
        lbA1 = new javax.swing.JLabel();
        lbS1 = new javax.swing.JLabel();
        lbA2 = new javax.swing.JLabel();
        lbS2 = new javax.swing.JLabel();
        lbA3 = new javax.swing.JLabel();
        lbS3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        btnLanzarDado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/dice.png"))); // NOI18N
        btnLanzarDado.setText("Lanzar dado!");
        btnLanzarDado.setActionCommand("");

        lbTurno.setText("jLabel1");

        lbDado.setText("s");

        lbCategoria.setText("Categoria: ");

        javax.swing.GroupLayout panelDadoLayout = new javax.swing.GroupLayout(panelDado);
        panelDado.setLayout(panelDadoLayout);
        panelDadoLayout.setHorizontalGroup(
            panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadoLayout.createSequentialGroup()
                .addGroup(panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLanzarDado, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                    .addGroup(panelDadoLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTurno)
                            .addComponent(lbDado, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDadoLayout.setVerticalGroup(
            panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTurno)
                .addGap(18, 18, 18)
                .addComponent(lbDado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(lbCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLanzarDado)
                .addContainerGap())
        );

        getContentPane().add(panelDado);
        panelDado.setBounds(170, 20, 160, 180);

        lbA1.setText("jLabel1");

        lbS1.setText("jLabel2");

        lbA2.setText("jLabel3");

        lbS2.setText("jLabel4");

        lbA3.setText("jLabel5");

        lbS3.setText("jLabel6");

        javax.swing.GroupLayout panelScoresLayout = new javax.swing.GroupLayout(panelScores);
        panelScores.setLayout(panelScoresLayout);
        panelScoresLayout.setHorizontalGroup(
            panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScoresLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbA3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbA2, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addComponent(lbA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbS1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbS2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbS3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18))
        );
        panelScoresLayout.setVerticalGroup(
            panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbA1)
                    .addComponent(lbS1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbS2)
                    .addComponent(lbA2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbS3)
                    .addComponent(lbA3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(panelScores);
        panelScores.setBounds(20, 160, 182, 100);

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
    private javax.swing.JButton btnLanzarDado;
    private javax.swing.JLabel lbA1;
    private javax.swing.JLabel lbA2;
    private javax.swing.JLabel lbA3;
    private javax.swing.JLabel lbCategoria;
    private javax.swing.JLabel lbDado;
    private javax.swing.JLabel lbS1;
    private javax.swing.JLabel lbS2;
    private javax.swing.JLabel lbS3;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JPanel panelDado;
    private javax.swing.JPanel panelScores;
    // End of variables declaration//GEN-END:variables
}
