/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizzer;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        custom();
        this.n = celdas;
        this.players = players;
        this.tipo = tipo;
        this.preguntas = preguntas;
        this.categorias = categorias;
        this.con = con;
        this.tiempoTurno = tiempoTurno;

        //inicial
        offset[0] = 20;
        offset[1] = 45;
        offset[2] = 67;
        offset[3] = 20;
        offset[4] = 78;
        //final
        offsetOut[0] = -5;
        offsetOut[1] = 19;
        offsetOut[2] = 40;
        offsetOut[3] = 2;
        offsetOut[4] = 35;
        //players
        grid = new Celda[(int) Math.sqrt(n)][(int) Math.sqrt(n)];
        //frame
        setDefaultCloseOperation(VJugar.EXIT_ON_CLOSE);
        jlScore.setText("Score");
        this.jlScore.setFont(new Font("Tahoma", Font.BOLD, 16));
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
        /*this.moverJugador(cP1, 2);
        this.moverJugador(cP2, 2);
        this.moverJugador(cP3, 2);*/
    }

    public void cargarFrame(int celdas) {
        switch (celdas) {
            case 9:
                this.setSize(700, 440);
                lp.setBounds(170, 35, 260, 150);
                jlScore.setBounds(261, 190, 60, 20);
                this.panelDado.setLocation(480, 10);
                this.panelScores.setLocation(180, 220);
                this.add(jlScore);
                break;

            case 16:
                this.setSize(700, 470);
                lp.setBounds(135, 20, 330, 200);
                jlScore.setBounds(265, 230, 60, 20);
                this.panelDado.setLocation(495, 45);
                this.panelScores.setLocation(184, 260);
                this.add(jlScore);
                break;

            case 49:
//                520
                this.setSize(850, 610);
                lp.setBounds(128, 20, 540, 350);
                jlScore.setBounds(360, 380, 60, 20);
                this.panelDado.setLocation(650, 85);
                this.panelScores.setLocation(279, 410);
                this.add(jlScore);
                break;

            case 64:
//                560
                this.setSize(880, 640);
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

                this.lbScoreApodo1.cell.setBackground(Color.WHITE);
                lbScoreApodo1.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreApodo1.cell.setForeground(Color.BLACK);

                this.panelScores.add(lbScoreApodo1.cell);

                lbScoreScore1 = new Celda(new JLabel(), 0, p);
                lbScoreScore1.cell.setBounds(this.lbS1.getBounds());
                lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());

                this.lbScoreScore1.cell.setBackground(Color.WHITE);
                lbScoreScore1.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreScore1.cell.setForeground(Color.black);

                this.panelScores.add(lbScoreScore1.cell);
                celdasPlayersScores.add(lbScoreScore1);
            }
            if (n == 2) {
                lbScoreApodo2 = new Celda(new JLabel(), 0, p);
                lbScoreApodo2.cell.setBounds(this.lbA2.getBounds());
                lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());

                lbScoreApodo2.cell.setBackground(Color.WHITE);
                lbScoreApodo2.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreApodo2.cell.setForeground(Color.black);

                this.panelScores.add(lbScoreApodo2.cell);

                lbScoreScore2 = new Celda(new JLabel(), 0, p);
                lbScoreScore2.cell.setBounds(this.lbS2.getBounds());
                lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());

                lbScoreScore2.cell.setBackground(Color.WHITE);
                lbScoreScore2.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreScore2.cell.setForeground(Color.black);
//                lbScoreScore2.cell.setHorizontalAlignment(SwingConstants.CENTER);
//                lbScoreScore2.cell.setVerticalAlignment(SwingConstants.CENTER);
//

                this.panelScores.add(lbScoreScore2.cell);
                celdasPlayersScores.add(lbScoreScore2);
            }
            if (n == 3) {
                lbScoreApodo3 = new Celda(new JLabel(), 0, p);
                lbScoreApodo3.cell.setBounds(this.lbA3.getBounds());
                lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());

                lbScoreApodo3.cell.setBackground(Color.WHITE);
                lbScoreApodo3.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreApodo3.cell.setForeground(Color.black);

                this.panelScores.add(lbScoreApodo3.cell);

                lbScoreScore3 = new Celda(new JLabel(), 0, p);
                lbScoreScore3.cell.setBounds(this.lbS3.getBounds());
                lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());

                lbScoreScore3.cell.setBackground(Color.WHITE);
                lbScoreScore3.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreScore3.cell.setForeground(Color.black);

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
                    grid[minRow][i].cell.setBorder(new LineBorder(Color.WHITE));
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
    }

    void custom() {
        this.getContentPane().setBackground(decode("#344035"));
        getRootPane().setBorder(BorderFactory.createLineBorder(decode("#8C5423"), 10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDado = new FondoPanel2();
        btnLanzarDado = new javax.swing.JButton();
        lbTurno = new javax.swing.JLabel();
        lbDado = new javax.swing.JLabel();
        lbCategoria = new javax.swing.JLabel();
        panelScores = new FondoPanel();
        lbA1 = new javax.swing.JLabel();
        lbS1 = new javax.swing.JLabel();
        lbA2 = new javax.swing.JLabel();
        lbS2 = new javax.swing.JLabel();
        lbA3 = new javax.swing.JLabel();
        lbS3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        panelDado.setBackground(new java.awt.Color(52, 64, 53));

        btnLanzarDado.setBackground(new java.awt.Color(255, 255, 255));
        btnLanzarDado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLanzarDado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/dice.png"))); // NOI18N
        btnLanzarDado.setText("Lanzar dado!");
        btnLanzarDado.setActionCommand("");
        btnLanzarDado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnLanzarDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarDadoActionPerformed(evt);
            }
        });

        lbTurno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTurno.setForeground(new java.awt.Color(255, 255, 255));
        lbTurno.setText("Turno: ");
        lbTurno.setPreferredSize(new java.awt.Dimension(85, 25));

        lbDado.setText("s");

        lbCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lbCategoria.setText("Categoria: ");

        javax.swing.GroupLayout panelDadoLayout = new javax.swing.GroupLayout(panelDado);
        panelDado.setLayout(panelDadoLayout);
        panelDadoLayout.setHorizontalGroup(
            panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbDado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(panelDadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLanzarDado, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        panelDadoLayout.setVerticalGroup(
            panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lbDado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(lbCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLanzarDado)
                .addGap(18, 18, 18))
        );

        getContentPane().add(panelDado);
        panelDado.setBounds(200, 10, 170, 236);

        panelScores.setBackground(new java.awt.Color(52, 64, 53));
        panelScores.setForeground(new java.awt.Color(255, 255, 255));
        panelScores.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScoresLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScoresLayout.createSequentialGroup()
                        .addComponent(lbA1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbS1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelScoresLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelScoresLayout.createSequentialGroup()
                                .addComponent(lbA2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbS2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelScoresLayout.createSequentialGroup()
                                .addComponent(lbA3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbS3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        panelScoresLayout.setVerticalGroup(
            panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScoresLayout.createSequentialGroup()
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScoresLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lbA1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbS1)))
                .addGap(16, 16, 16)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbS2)
                    .addComponent(lbA2))
                .addGap(14, 14, 14)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbA3)
                    .addComponent(lbS3))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(panelScores);
        panelScores.setBounds(10, 140, 200, 144);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarDadoActionPerformed
        System.out.println("pico boton");
    }//GEN-LAST:event_btnLanzarDadoActionPerformed

    

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
class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/resource/letreromadera2.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }

    class FondoPanel2 extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/resource/letreromadera.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
