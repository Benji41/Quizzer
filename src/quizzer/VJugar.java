package quizzer;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.util.List;
import java.util.ListIterator;
import javax.swing.Icon;
import javax.swing.Timer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import objetos.Categoria;
import objetos.Celda;
import objetos.Jugador;
import objetos.Pregunta;
import java.applet.AudioClip;

/**
 *
 * @author Benjimon41
 */
public class VJugar extends javax.swing.JFrame {

    //exterior
    int tipo;
    int n;
    ArrayList<Jugador> players = new ArrayList<>();
    Connection conL;
    Connection conR;
    String urlL = "jdbc:sqlite:DB/localdb.db";
    Map<Integer, Categoria> preguntas = new HashMap<>();
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
    Celda lbRachaC1;
    Celda lbRachaC2;
    Celda lbRachaC3;
    Celda cP1;
    Celda cP2;
    Celda cP3;
    int tiempoTurno;
    ArrayList<Celda> celdasPlayers = new ArrayList<>();
    ArrayList<Celda> celdasPlayersScores = new ArrayList<>();
    ArrayList<Celda> celdasPlayersRacha = new ArrayList<>();
    ArrayList<JLabel> celdasPlayersApodo = new ArrayList<>();
    ListIterator<Celda> t;
    String imgjugador1="";
    String imgjugador2="";
    String imgjugador3="";
    
    //partida
    Random d = new Random();
    Celda jugadorEnTurno;
    int indiceJugador = 0;
    long start, end;
    Stopwatch sw;
    ImageIcon icon;
    
    AudioClip sonidoAvanzar;
    AudioClip sonidoGanar;
    AudioClip sonidoPartida;
    AudioClip sonidoPerder;
    AudioClip sonidoRetroceder;
    
    public VPrincipal pri;

    VJugar(int celdas, ArrayList<Jugador> players, int tipo, Map<Integer, Categoria> preguntas, ArrayList<String> categorias, Connection con, int tiempoTurno, String imgj1,String imgj2,String imgj3) throws IOException {
        initComponents();
        TimeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        TimeLabel.setForeground(new Color(255, 252, 246));
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        this.setLocationRelativeTo(null);
        custom();
        customJOptionPane();
        this.n = celdas;
        this.players = players;
        this.tipo = tipo;
        this.preguntas = preguntas;
        this.categorias = categorias;

        this.tiempoTurno = tiempoTurno;
        this.imgjugador1=imgj1;
        this.imgjugador2=imgj2;
        this.imgjugador3=imgj3;
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
        this.lbRacha1.setVisible(false);    
        this.lbRacha2.setVisible(false);
        this.lbRacha3.setVisible(false);
        this.lbS1.setVisible(false);
        this.lbS2.setVisible(false);
        this.lbS3.setVisible(false);
        this.lbDado.setVisible(false);
        //layered
        crearConL();
        System.out.println(conL);
        this.conR = con;
        System.out.println(conR);
        cargarFrame(n);
        this.cargarScore();
        //tablero
        this.dibujarTabla(n);
        this.cargarValores(n);
        this.cargarJugadores(this.imgjugador1,this.imgjugador2,this.imgjugador3);
        this.obtenerJugador(this.indiceJugador);
        
        //pri.stopSound();
        sonidoPartida = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/partida.wav"));
        sonidoPartida.loop();
    }

    //backend
    public void crearConL() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conL = DriverManager.getConnection(urlL);
            if (conL != null) {
                conL.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cargarFrame(int celdas) {
        switch (celdas) {
            case 9:
                this.setSize(700, 440);
                lp.setBounds(170, 35, 260, 150);
                this.labelReloj.setLocation(515, 10);
                this.TimeLabel.setLocation(516, 49);
                this.panelDado.setLocation(480, 80);
                this.panelScores.setLocation(147, 220);
                this.panelCancelar.setLocation(490, 340);
                break;

            case 16:
                this.setSize(700, 470);
                lp.setBounds(135, 20, 330, 200);
                this.labelReloj.setLocation(530, 10);
                this.TimeLabel.setLocation(531, 49);
                this.panelDado.setLocation(495, 80);
                this.panelScores.setLocation(147, 260);
                this.panelCancelar.setLocation(505, 370);
                break;

            case 49:
//                520
                this.setSize(850, 610);
                lp.setBounds(128, 20, 540, 350);
                this.labelReloj.setLocation(675, 10);
                this.TimeLabel.setLocation(676, 49);
                this.panelDado.setLocation(640, 80);
                this.panelScores.setLocation(249, 400);
                this.panelCancelar.setLocation(660, 510);
                break;

            case 64:
//                560
                this.setSize(900, 640);
                lp.setBounds(92, 5, 610, 400);
                this.labelReloj.setLocation(717, 10);
                this.TimeLabel.setLocation(718, 49);
                this.panelDado.setLocation(680, 85);
                this.panelScores.setLocation(249, 430);
                this.panelCancelar.setLocation(690, 540);
                break;

        }
        sw = new Stopwatch(TimeLabel);
        sw.Start();
        this.add(lp);
        this.setLocationRelativeTo(null);
    }

    public void cargarScore() {

        for (Jugador p : players) {
            int n = p.getNumero();
            if (n == 1) {
                lbScoreApodo1 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbScoreApodo1.cell.setBounds(this.lbA1.getBounds());
                lbScoreApodo1.cell.setText(lbScoreApodo1.player.getApodo());

                this.lbScoreApodo1.cell.setBackground(Color.WHITE);
                lbScoreApodo1.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreApodo1.cell.setForeground(Color.red);
                this.celdasPlayersApodo.add(this.lbA1);
                this.panelScores.add(lbScoreApodo1.cell);

                lbScoreScore1 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbScoreScore1.cell.setBounds(this.lbS1.getBounds());
                lbScoreScore1.cell.setText("" + lbScoreScore1.player.getScore());

                lbScoreScore1.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreScore1.cell.setForeground(Color.black);

                this.panelScores.add(lbScoreScore1.cell);
                celdasPlayersScores.add(lbScoreScore1);

                lbRachaC1 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbRachaC1.cell.setBounds(this.lbRacha1.getBounds());
                this.lbRachaC1.cell.setForeground(Color.getHSBColor(51, 100, 100));
                this.lbRachaC1.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                this.celdasPlayersRacha.add(lbRachaC1);
                this.panelScores.add(lbRachaC1.cell);
                this.lbRachaC1.setVisible(false);
                lpScores.add(this.lbScoreApodo1.cell, Integer.valueOf(1));
            }
            if (n == 2) {
                lbScoreApodo2 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbScoreApodo2.cell.setBounds(this.lbA2.getBounds());
                lbScoreApodo2.cell.setText(lbScoreApodo2.player.getApodo());

                lbScoreApodo2.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreApodo2.cell.setForeground(Color.BLUE);
                this.celdasPlayersApodo.add(this.lbA2);
                this.panelScores.add(lbScoreApodo2.cell);

                lbScoreScore2 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbScoreScore2.cell.setBounds(this.lbS2.getBounds());
                lbScoreScore2.cell.setText("" + lbScoreScore2.player.getScore());

                lbScoreScore2.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreScore2.cell.setForeground(Color.black);

                this.panelScores.add(lbScoreScore2.cell);
                celdasPlayersScores.add(lbScoreScore2);

                lbRachaC2 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbRachaC2.cell.setBounds(this.lbRacha2.getBounds());
                this.lbRachaC2.cell.setForeground(Color.getHSBColor(51, 100, 100));
                this.lbRachaC2.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                this.celdasPlayersRacha.add(lbRachaC2);
                this.panelScores.add(lbRachaC2.cell);
                this.lbRachaC2.setVisible(false);
                lpScores.add(this.lbScoreApodo2.cell, Integer.valueOf(1));
            }
            if (n == 3) {
                lbScoreApodo3 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbScoreApodo3.cell.setBounds(this.lbA3.getBounds());
                lbScoreApodo3.cell.setText(lbScoreApodo3.player.getApodo());

                lbScoreApodo3.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreApodo3.cell.setForeground(Color.YELLOW);
                this.celdasPlayersApodo.add(this.lbA3);
                this.panelScores.add(lbScoreApodo3.cell);

                lbScoreScore3 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbScoreScore3.cell.setBounds(this.lbS3.getBounds());
                lbScoreScore3.cell.setText("" + lbScoreScore3.player.getScore());

                lbScoreScore3.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                lbScoreScore3.cell.setForeground(Color.black);

                this.panelScores.add(lbScoreScore3.cell);
                celdasPlayersScores.add(lbScoreScore3);

                lbRachaC3 = new Celda(new JLabel("", SwingConstants.CENTER), 0, p);
                lbRachaC3.cell.setBounds(this.lbRacha3.getBounds());
                this.lbRachaC3.cell.setForeground(Color.getHSBColor(51, 100, 100));
                this.lbRachaC3.cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                this.celdasPlayersRacha.add(lbRachaC3);
                this.panelScores.add(lbRachaC3.cell);
                this.lbRachaC3.setVisible(false);
                lpScores.add(this.lbScoreApodo3.cell, Integer.valueOf(1));
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
                    Border border = BorderFactory.createLineBorder(Color.black);
                    Celda c = new Celda(new JLabel(), 0);
                    c.cell.setBorder(border);
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
                    if (value != n && i > maxCol) {
                        grid[minRow][i].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_l.png")));
                        System.out.println(value + "entro a primer if");
                    } else {
                        if (i == maxCol) {
                            grid[minRow][i].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_u.png")));
                        }

                    }
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
                    System.out.println(value + "entro a seg if" + "i" + i);
                    grid[i][maxCol].cell.setText(String.valueOf(value));
                    if (value != n && i > maxRow) {
                        grid[i][maxCol].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_u.png")));
                    } else {
                        if (i == maxRow) {
                            System.out.println("entro a r");
                            grid[i][maxCol].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_r.png")));
                        }

                    }

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
                    System.out.println(value + "entro a ter if");
                    if (value != n && i < minCol) {
                        grid[maxRow][i].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_r.png")));
                    } else {
                        if (i == minCol) {
                            grid[maxRow][i].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_d.png")));
                        }
                    }
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
                    System.out.println(value + "entro a cuar if");
                    if (value != n && i < (minRow - 1)) {
                        grid[i][minCol].cell.setIcon((new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_d.png"))));
                    } else {
                        if (i == (minRow - 1)) {
                            grid[i][minCol].cell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/arrow_l.png")));
                        }
                    }

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

    public void cargarJugadores(String imgjugador1,String imgjugador2,String imgjugador3) throws IOException {
        if (tipo == 0) {
            cP1 = new Celda(new JLabel(), 1, players.get(0));
            cP1.asignarImagen(3,imgjugador1);
            cP1.cell.setBounds(hash.get(1)[0] + offset[0], hash.get(1)[1] + offset[3], 32, 32);
            lp.add(cP1.cell, Integer.valueOf(1));
            this.celdasPlayers.add(cP1);
            cP3 = new Celda(new JLabel(), 1, new Jugador("", 0, 4, 0));
            cP3.asignarImagen(2);
            cP3.cell.setBounds(hash.get(1)[0] + offset[2], hash.get(1)[1] + offset[3], 32, 32);
            lp.add(cP3.cell, Integer.valueOf(1));
        } else {
            for (Jugador player : players) {
                if (player.getNumero() == 1) {
                    cP1 = new Celda(new JLabel(), 1, player);
                    cP1.asignarImagen(3,imgjugador1);
                    cP1.cell.setBounds(hash.get(1)[0] + offset[0], hash.get(1)[1] + offset[3], 32, 32);
                    lp.add(cP1.cell, Integer.valueOf(1));
                    this.celdasPlayers.add(cP1);
                }
                if (player.getNumero() == 2) {
                    cP2 = new Celda(new JLabel(), 1, player);
                    cP2.asignarImagen(4,imgjugador2);
                    cP2.cell.setBounds(hash.get(1)[0] + offset[1], hash.get(1)[1] + offset[3], 32, 32);
                    lp.add(cP2.cell, Integer.valueOf(1));
                    this.celdasPlayers.add(cP2);
                }
                if (player.getNumero() == 3) {
                    cP3 = new Celda(new JLabel(), 1, player);
                    cP3.asignarImagen(5,imgjugador3);
                    cP3.cell.setBounds(hash.get(1)[0] + offset[2], hash.get(1)[1] + offset[3], 32, 32);
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
        c.valor = nuevoValor;
        System.out.println("mover jugador " + c.valor);
        int num = c.player.getNumero();
        if (num == 4) {
            c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + 20, 32, 32);
        } else {
            num--;
            if (c.valor == 1) {
                //cP1.cell.setBounds(hash.get(1)[0] + offset[0], hash.get(1)[1] + offset[3], 30, 30);
                if (num != 1) {
                    c.cell.setBounds(hash.get(1)[0] + offset[num], hash.get(1)[1] + 20, 32, 32);
                } else {
                    c.cell.setBounds(hash.get(1)[0] + offset[num], hash.get(1)[1] + offset[3], 32, 32);
                }
            } else if (num != 1) {
                c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + 20, 32, 32);
            } else {
                c.cell.setBounds(hash.get(nuevoValor)[0] + offsetOut[num], hash.get(nuevoValor)[1] + offsetOut[3], 32, 32);
            }
        }
    }

    void customJOptionPane() {
        try {
            //        UIManager.put("OptionPane.background", Color.decode("#344035"));
//        UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#344035"));
//        UIManager.put("OptionPane.messageForeground", new Color(13, 13, 13));
//        UIManager.put("Button.background", Color.WHITE);
            UIManager.put("RootPane.dialogBorder", new LineBorder(Color.red));
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("OptionPane.background", Color.decode("#D99B66"));
            UIManager.put("Panel.background", Color.decode("#D99B66"));
            UIManager.put("OptionPane.messageForeground", new Color(13, 13, 13));
            UIManager.put("Button.background", Color.WHITE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void custom() {
        this.getContentPane().setBackground(decode("#344035"));
        getRootPane().setBorder(BorderFactory.createLineBorder(decode("#8C5423"), 10));
    }

    public Icon icono(String path, int width, int height) {
        Icon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return img;
    }

    //partida
    public void obtenerJugador(int indice) {
        this.jugadorEnTurno = this.celdasPlayers.get(indice);
        this.lbApodo.setText(jugadorEnTurno.player.getApodo());
        switch (jugadorEnTurno.player.getNumero()) {
            case 1:
                this.lbApodo.setForeground(new Color(240, 0, 0));
                this.lbApodo.setFont(new Font("Ink Free", Font.BOLD, 16));
                break;
            case 2:
                this.lbApodo.setForeground(new Color(0, 123, 246));
                this.lbApodo.setFont(new Font("Ink Free", Font.BOLD, 16));
                break;
            case 3:
                this.lbApodo.setForeground(new Color(255, 233, 0));
                this.lbApodo.setFont(new Font("Ink Free", Font.BOLD, 16));
                break;

        }
    }

    public Pregunta busquedaPreguntas(Categoria preguntas) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        boolean found = false;
        Pregunta p = null;
        Celda ganador = new Celda(new JLabel(), 0, new Jugador("", 0, 0, 0));
        if (preguntas != null) {
            do {
                int numPregunta = d.nextInt(((preguntas.getPreguntas().size() - 1) - 0) + 1) + 0;
                p = preguntas.getPreguntas().get(numPregunta);
                if (!p.isUsada()) {
                    int restantes = preguntas.getContador();
                    System.out.println(restantes);
                    restantes = restantes - 1;
                    preguntas.setContador(restantes);
                    System.out.println("cuantas quedan " + preguntas.getContador());
                    p.setUsada(true);
                    found = true;
                }
            } while (!found);
        } else {
            for (int i = 0; i < this.celdasPlayers.size(); i++) {
                if (this.celdasPlayers.get(i).valor > ganador.valor) {
                    ganador = celdasPlayers.get(i);
                }
            }
            if (ganador.valor != 1) {
                this.jugadorEnTurno = ganador;
                this.ganoMulti(1);
            } else {
                this.empate();
            }

        }

        return p;
    }

    public Pregunta dado() throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        boolean found = false;
        int numDado = -1;
        Categoria categoria = null;
        if (this.preguntas.get(1).getContador() > 1 || this.preguntas.get(2).getContador() > 1 || this.preguntas.get(3).getContador() > 1 || this.preguntas.get(4).getContador() > 1 || this.preguntas.get(5).getContador() > 1 || this.preguntas.get(6).getContador() > 1) {
            do {
                numDado = 1 + d.nextInt(6);
                categoria = this.preguntas.get(numDado);
                if (categoria.getContador() >= 1) {
                    found = true;
                    ImageIcon imagen;
                    imagen = new ImageIcon(getClass().getResource("/resource/dado" + numDado + ".png"));
                    this.lbDado.setIcon(imagen);
                    this.lbDado.setVisible(true);
                    numDado = numDado - 1;
                    String cate = this.categorias.get(numDado);
                    this.lbCategoria.setText("Categoria:" + " " + cate);
                    repaint();

                }
            } while (!found);
        }
        return this.busquedaPreguntas(categoria);
    }

    public String mostrarPregunta(Pregunta preg) {
        ArrayList<String> respuestas = new ArrayList<>();
        respuestas.add(preg.getRespuestaCorrecta());
        respuestas.add(preg.getRespuestaIncorrecta1());
        respuestas.add(preg.getRespuestaIncorrecta2());
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lista.add(respuestas.get(i));
        }
        Collections.shuffle(lista);
        Object[] res = new Object[3];
        for (int i = 0; i < 3; i++) {
            res[i] = lista.get(i);
        }
        JOptionPane jp = new JOptionPane(preg.getPregunta(), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icono("/resource/Thinking.png", 40, 40), res, res[0]);
        final JDialog dlg = jp.createDialog("Hora de pensar!");
        dlg.setIconImage(new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage());
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        ActionListener taskPerformer = new ActionListener() {
            long startTime = -1;

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                    System.out.println(startTime);
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= tiempoTurno * 1000) {
                    clockTime = tiempoTurno * 1000;
                    dlg.setVisible(false);
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss");
                dlg.setTitle(df.format(tiempoTurno * 1000 - clockTime));
            }
        };

        dlg.addComponentListener(
                new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                final javax.swing.Timer t = new javax.swing.Timer(800, taskPerformer);
                t.start();
            }
        }
        );
        dlg.setVisible(true);
        String selectedvalue = (String) jp.getValue();
        if (selectedvalue == null) {
            selectedvalue = "uninitializedValue";
        }
        System.out.println(selectedvalue);

        if (selectedvalue.equals("uninitializedValue")) {
            System.out.println("entro a se acabo tiempo");
            JOptionPane.showMessageDialog(null, "No se escogió respuesta", "", JOptionPane.WARNING_MESSAGE);
            return "-1";
        }
        return selectedvalue;
    }

    public void verificarRespuesta(Pregunta pregunta, String respuesta) throws InterruptedException {
        this.btnLanzarDado.setEnabled(false);
        System.out.println(jugadorEnTurno.valor);
        if (respuesta.equals(pregunta.getRespuestaCorrecta())) {
            System.out.println("entro a correcta");
            jugadorEnTurno.player.setRacha(jugadorEnTurno.player.getRacha() + 1);
            sonidoAvanzar = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/avanzar.wav"));
            sonidoAvanzar.play();
            if (jugadorEnTurno.player.getRacha() >= 2) {
                this.celdasPlayersApodo.get(this.indiceJugador).setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/racha_fuego.gif")));
                this.celdasPlayersRacha.get(this.indiceJugador).cell.setText("x" + String.valueOf(this.celdasPlayersRacha.get(this.indiceJugador).player.getRacha()));
                this.celdasPlayersRacha.get(this.indiceJugador).cell.setVisible(true);
            }

            SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(1150);
                        jugadorEnTurno.valor += 1;
                        moverJugador(jugadorEnTurno, jugadorEnTurno.valor);

                    }
                    return true;
                }

                protected void done() {
                    jugadorEnTurno.player.setScore(jugadorEnTurno.player.getScore() + (100 * jugadorEnTurno.player.getRacha()));
                    celdasPlayersScores.get(indiceJugador).cell.setBackground(Color.yellow);
                    celdasPlayersScores.get(indiceJugador).cell.setText("+100");
                    celdasPlayersScores.get(indiceJugador).cell.setText(String.valueOf(celdasPlayersScores.get(indiceJugador).player.getScore()));
                    celdasPlayersScores.get(indiceJugador).cell.setBackground(Color.black);
                    System.out.println("Puntaje: " + jugadorEnTurno.player.getScore());
                    try {
                        verificarGanoPartida();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btnLanzarDado.setEnabled(true);
                }

            };
            worker.execute();

        }
        if (!respuesta.equals(pregunta.getRespuestaCorrecta())) {
            if (!respuesta.equals("-1")) {
                sonidoRetroceder = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/retroceder.wav"));
                sonidoRetroceder.play();
                System.out.println("entro a incorrecta pero intento");
                jugadorEnTurno.player.setScore(jugadorEnTurno.player.getScore() - 15);
                celdasPlayersScores.get(indiceJugador).cell.setText(String.valueOf(celdasPlayersScores.get(indiceJugador).player.getScore()));
                celdasPlayersScores.get(indiceJugador).cell.setBackground(Color.orange);
                System.out.println("Puntaje: " + jugadorEnTurno.player.getScore());
                celdasPlayersScores.get(indiceJugador).cell.setBackground(Color.black);
            } else {
                System.out.println("entro a incorrecta pero no intento");
                jugadorEnTurno.player.setScore(jugadorEnTurno.player.getScore() - 25);
                celdasPlayers.get(indiceJugador).cell.setText("-25");
                celdasPlayersScores.get(indiceJugador).cell.setBackground(Color.red);
                celdasPlayersScores.get(indiceJugador).cell.setText("-25");
                celdasPlayersScores.get(indiceJugador).cell.setText(String.valueOf(celdasPlayersScores.get(indiceJugador).player.getScore()));
                System.out.println("Puntaje: " + jugadorEnTurno.player.getScore());
                celdasPlayersScores.get(indiceJugador).cell.setBackground(Color.black);

            }
            jugadorEnTurno.valor -= 1;
            moverJugador(jugadorEnTurno, jugadorEnTurno.valor);
            jugadorEnTurno.player.setRacha(0);
            this.celdasPlayersRacha.get(this.indiceJugador).cell.setText("");
            this.celdasPlayersRacha.get(this.indiceJugador).cell.setVisible(false);
            this.celdasPlayersApodo.get(this.indiceJugador).setIcon(null);
            if (tipo == 1) {
                turno();
                this.obtenerJugador(this.indiceJugador);
            }
            if (tipo == 0) {
                SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        for (int i = 0; i < 2; i++) {
                            Thread.sleep(1150);
                            cP3.valor += 1;
                            moverJugador(cP3, cP3.valor);
                        }
                        return true;
                    }

                    protected void done() {
                        try {
                            verificarPerdioPartida();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                };
                worker.execute();
            }
            this.btnLanzarDado.setEnabled(true);
        }

    }

    public void verificarGanoPartida() throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        if (jugadorEnTurno.valor >= n) {
            if (tipo == 0) {
                sonidoGanar = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/ganar.wav"));
                sonidoGanar.play();
                ganoSingle();
            }
            if (tipo == 1) {
                sonidoGanar = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/ganar.wav"));
                sonidoGanar.play();
                ganoMulti(0);
            }
        } else {
            System.out.println("entro contestar bien pero no ganar");
            turno();
            this.obtenerJugador(this.indiceJugador);
        }

    }

    public void verificarPerdioPartida() throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        if (this.cP3.valor >= n) {
            //Gano ignorancia
            sw.Stop();
            sonidoPerder = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/perder.wav"));
            sonidoPerder.play();
            String sql = "INSERT INTO ScoreSingle (Outcome, Score, Duration, DateS) VALUES (?, ?, ?, ?);";
            try {
                PreparedStatement ps = conL.prepareStatement(sql);
                ps.setString(1, "Perdió");
                ps.setInt(2, jugadorEnTurno.player.getScore());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                ps.setString(3, TimeLabel.getText());
                ps.setString(4, formatter.format(date));
                ps.executeUpdate();
                ps.close();
                conL.commit();
                icon = new ImageIcon(getClass().getResource("/resource/tryagain.png"));
                JOptionPane.showMessageDialog(null, "<html><h3>Ganó la ignorancia</h3></html>", "SAD", JOptionPane.INFORMATION_MESSAGE, icon);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se conectó con la base de datos interna." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            this.conL.close();
            if (conR != null) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    boolean status = false;
                    VPrincipal vp = new VPrincipal(null, this.conR,status);
                    vp.setVisible(true);
                    this.dispose();
                } catch (InterruptedException ex) {
                    Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    boolean status = false;
                    VPrincipal vp = new VPrincipal(status);
                    vp.setVisible(true);
                    this.dispose();
                } catch (InterruptedException ex) {
                    Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("entro ganar la ignorancia");
            sonidoPartida.stop();
        }
    }

    public void ganoSingle() throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        //Ganar singleplayer
        sw.Stop();
        String sql = "INSERT INTO ScoreSingle (Outcome, Score, Duration, DateS) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = conL.prepareStatement(sql);
            ps.setString(1, "Ganó");
            ps.setInt(2, jugadorEnTurno.player.getScore());
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            ps.setString(3, TimeLabel.getText());
            ps.setString(4, formatter.format(date));
            System.out.println(jugadorEnTurno.player.getApodo() + ", " + jugadorEnTurno.player.getScore() + "," + formatter.format(date));
            ps.executeUpdate();
            ps.close();
            conL.commit();
            icon = new ImageIcon(getClass().getResource("/resource/congrats-baloons.png"));
            JOptionPane.showMessageDialog(null, "<html><h3>Ganó " + jugadorEnTurno.player.getApodo() + " con " + jugadorEnTurno.player.getScore() + " puntos </h3></html>", "Veri gud", JOptionPane.INFORMATION_MESSAGE, icon);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se conectó con la base de datos interna." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        if (conR != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(null, this.conR,status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
            }
        } else {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("entro ganar solo");
        sonidoPartida.stop();
    }

    public void ganoMulti(int caso) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        //Ganar multijugador

        String sql = "INSERT INTO ScoreMulti (Winner, ScoreM, DateM) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = conL.prepareStatement(sql);
            ps.setString(1, jugadorEnTurno.player.getApodo());
            ps.setInt(2, jugadorEnTurno.player.getScore());
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            ps.setString(3, formatter.format(date));
            ps.executeUpdate();
            ps.close();
            conL.commit();
            icon = new ImageIcon(getClass().getResource("/resource/congrats-baloons.png"));
            if (caso == 0) {
                JOptionPane.showMessageDialog(null, "<html><h3>Ganó " + jugadorEnTurno.player.getApodo() + " con " + jugadorEnTurno.player.getScore() + " puntos</h3></html>", "Veri gud", JOptionPane.INFORMATION_MESSAGE, icon);
            } else {
                JOptionPane.showMessageDialog(null, "<html><h3>Ganó " + jugadorEnTurno.player.getApodo() + " con " + jugadorEnTurno.player.getScore() + " puntos con la mejor posicion en el tablero " + jugadorEnTurno.valor + "</h3></html>", "Veri gud", JOptionPane.INFORMATION_MESSAGE, icon);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se conectó con la base de datos interna." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("entro ganar multijugador");
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        try {
            this.conL.close();
        } catch (SQLException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conR != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(null, this.conR,status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }sonidoPartida.stop();
    }

    public void empate() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        JOptionPane.showMessageDialog(null, "<html><h3>¡Se recomienda que todos los jugadores estudien!</h3></html>", "Empate", JOptionPane.PLAIN_MESSAGE, icon);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        try {
            this.conL.close();
        } catch (SQLException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conR != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(null, this.conR,status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }sonidoPartida.stop();
    }

    public void turno() {
        System.out.println("indice turno" + this.indiceJugador);
        if (this.indiceJugador < (this.players.size() - 1)) {
            this.indiceJugador++;
        } else {
            this.indiceJugador = 0;
        }
        System.out.println(this.indiceJugador);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDado = new FondoPanel2();
        btnLanzarDado = new javax.swing.JButton();
        lbTurno = new javax.swing.JLabel();
        lbDado = new javax.swing.JLabel();
        lbCategoria = new javax.swing.JLabel();
        lbApodo = new javax.swing.JLabel();
        panelScores = new FondoPanel();
        lb_Scoretxt = new javax.swing.JLabel();
        lbRacha3 = new javax.swing.JLabel();
        lbRacha2 = new javax.swing.JLabel();
        lbRacha1 = new javax.swing.JLabel();
        lpScores = new javax.swing.JLayeredPane();
        lbA1 = new javax.swing.JLabel();
        lbA2 = new javax.swing.JLabel();
        lbA3 = new javax.swing.JLabel();
        lbS3 = new javax.swing.JLabel();
        lbS1 = new javax.swing.JLabel();
        lbS2 = new javax.swing.JLabel();
        TimeLabel = new javax.swing.JLabel("", SwingConstants.CENTER);
        labelReloj = new javax.swing.JLabel();
        panelCancelar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Partida");
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

        lbTurno.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbTurno.setForeground(new java.awt.Color(255, 255, 255));
        lbTurno.setText("Turno: ");
        lbTurno.setPreferredSize(new java.awt.Dimension(85, 25));

        lbDado.setText("s");

        lbCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCategoria.setForeground(new java.awt.Color(255, 255, 255));

        lbApodo.setText("jLabel1");

        javax.swing.GroupLayout panelDadoLayout = new javax.swing.GroupLayout(panelDado);
        panelDado.setLayout(panelDadoLayout);
        panelDadoLayout.setHorizontalGroup(
            panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLanzarDado, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addGroup(panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDadoLayout.createSequentialGroup()
                                .addComponent(lbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(panelDadoLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lbDado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDadoLayout.setVerticalGroup(
            panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDado, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(lbCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLanzarDado)
                .addGap(18, 18, 18))
        );

        getContentPane().add(panelDado);
        panelDado.setBounds(280, 20, 170, 210);

        panelScores.setBackground(new java.awt.Color(52, 64, 53));
        panelScores.setForeground(new java.awt.Color(255, 255, 255));
        panelScores.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N

        lb_Scoretxt.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lb_Scoretxt.setForeground(new java.awt.Color(255, 252, 246));
        lb_Scoretxt.setText("Score");
        lb_Scoretxt.setToolTipText("");

        lbRacha3.setText("jLabel1");

        lbRacha2.setText("jLabel1");

        lbRacha1.setText("jLabel1");

        lbS3.setText("jLabel6");

        lbS1.setText("jLabel2");

        lbS2.setText("jLabel4");

        lpScores.setLayer(lbA1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpScores.setLayer(lbA2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpScores.setLayer(lbA3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpScores.setLayer(lbS3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpScores.setLayer(lbS1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpScores.setLayer(lbS2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpScoresLayout = new javax.swing.GroupLayout(lpScores);
        lpScores.setLayout(lpScoresLayout);
        lpScoresLayout.setHorizontalGroup(
            lpScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpScoresLayout.createSequentialGroup()
                .addComponent(lbA1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(lpScoresLayout.createSequentialGroup()
                .addComponent(lbA2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(lpScoresLayout.createSequentialGroup()
                .addComponent(lbA3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lpScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbS3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbS2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbS1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        lpScoresLayout.setVerticalGroup(
            lpScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpScoresLayout.createSequentialGroup()
                .addComponent(lbA1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbA2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lpScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbA3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbS1))
                .addGap(4, 4, 4)
                .addComponent(lbS2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbS3)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelScoresLayout = new javax.swing.GroupLayout(panelScores);
        panelScores.setLayout(panelScoresLayout);
        panelScoresLayout.setHorizontalGroup(
            panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScoresLayout.createSequentialGroup()
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScoresLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lpScores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRacha3)
                            .addComponent(lbRacha2)
                            .addComponent(lbRacha1)))
                    .addGroup(panelScoresLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lb_Scoretxt)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        panelScoresLayout.setVerticalGroup(
            panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Scoretxt)
                .addGap(18, 18, 18)
                .addGroup(panelScoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lpScores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelScoresLayout.createSequentialGroup()
                        .addComponent(lbRacha1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbRacha2)
                        .addGap(11, 11, 11)
                        .addComponent(lbRacha3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelScores);
        panelScores.setBounds(0, 200, 270, 170);

        TimeLabel.setBackground(new java.awt.Color(212, 149, 98));
        TimeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TimeLabel.setOpaque(true);
        getContentPane().add(TimeLabel);
        TimeLabel.setBounds(70, 80, 98, 20);

        labelReloj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/reloj.png"))); // NOI18N
        getContentPane().add(labelReloj);
        labelReloj.setBounds(70, 40, 100, 40);

        panelCancelar.setBackground(new java.awt.Color(52, 64, 53));
        panelCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Cancelar partida");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelCancelar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 30));

        getContentPane().add(panelCancelar);
        panelCancelar.setBounds(290, 320, 150, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarDadoActionPerformed
        System.out.println("dado" + this.indiceJugador);
        Pregunta p = null;
        try {
            p = this.dado();
        } catch (InterruptedException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (p != null) {
            String respuesta = this.mostrarPregunta(p);
            try {
                verificarRespuesta(p, respuesta);
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLanzarDadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (conR != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(null, this.conR,status);
                vp.setVisible(true);
                sonidoPartida.stop();
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = false;
                VPrincipal vp = new VPrincipal(status);
                vp.setVisible(true);
                sonidoPartida.stop();
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(VJugar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JButton btnLanzarDado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel labelReloj;
    private javax.swing.JLabel lbA1;
    private javax.swing.JLabel lbA2;
    private javax.swing.JLabel lbA3;
    private javax.swing.JLabel lbApodo;
    private javax.swing.JLabel lbCategoria;
    private javax.swing.JLabel lbDado;
    private javax.swing.JLabel lbRacha1;
    private javax.swing.JLabel lbRacha2;
    private javax.swing.JLabel lbRacha3;
    private javax.swing.JLabel lbS1;
    private javax.swing.JLabel lbS2;
    private javax.swing.JLabel lbS3;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JLabel lb_Scoretxt;
    private javax.swing.JLayeredPane lpScores;
    private javax.swing.JPanel panelCancelar;
    private javax.swing.JPanel panelDado;
    private javax.swing.JPanel panelScores;
    // End of variables declaration//GEN-END:variables
class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/resource/letreroh.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }

    class FondoPanel2 extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/resource/letrerov.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
