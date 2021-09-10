package quizzer;

import java.applet.AudioClip;
import java.awt.Checkbox;
import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import objetos.Categoria;
import objetos.Jugador;
import objetos.Pregunta;

public class VPersonalizacion extends javax.swing.JFrame {

    Connection conL = null;
    Connection conR = null;
    int categoriasSeleccionadas = 0;
    int celdas;
    int tipo;
    int tiempoTurno;
    String imgjugador1 = "/resource/c9rojo.png";
    String imgjugador2 = "/resource/c8azul.png";
    String imgjugador3 = "/resource/c5amarillo.png";

    int n;

    ArrayList<String> listaCatego = new ArrayList<>();
    ArrayList<Jugador> players = new ArrayList<>();
    ArrayList<String> categoriasJugar = new ArrayList<>();
    ArrayList<objetos.Pregunta> preguntas;
    Map<Integer, objetos.Categoria> preguntasOrdenadas = new HashMap<>();
    Jugador j1;
    Jugador j2;
    Jugador j3;
    String queryQuestL = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from Pregunta;";
    DefaultListModel list = new DefaultListModel();

    AudioClip sonidoMenu;
    
    public VPersonalizacion(ArrayList<String> categorias, ArrayList<Pregunta> preguntas, Connection con, AudioClip sm) {
        initComponents();
        customJOptionPane();
        custom();
        VentanaMemes.setVisible(false);
        this.conR = con;
        this.preguntas = preguntas;
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        setLocationRelativeTo(null);
        LabelNombrej1.setVisible(false);
        LabelNombrej2.setVisible(false);
        LabelNombrej3.setVisible(false);
        nombreJ1.setVisible(false);
        nombreJ2.setVisible(false);
        nombreJ3.setVisible(false);
        player1.setVisible(false);
        player2.setVisible(false);
        player3.setVisible(false);
        ListaCategorias.setModel(list);
        this.listaCatego = new ArrayList<>();
        CategoriaCB.addItem("---");
        for (String categoria : categorias) {
            CategoriaCB.addItem(categoria);
        }
        this.sonidoMenu=sm;
    }

    void customJOptionPane() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("OptionPane.background", Color.decode("#FFFFF6"));
            UIManager.put("Panel.background", Color.decode("#FFFFF6"));
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

    public void ordenarPreguntas() {
        for (int i = 0; i < this.listaCatego.size(); i++) {
            ArrayList<objetos.Pregunta> preguntasCategoria = new ArrayList<>();
            for (int j = 0; j < this.preguntas.size(); j++) {
                if (this.preguntas.get(j).getCategoria().equals(this.listaCatego.get(i))) {
                    preguntasCategoria.add(this.preguntas.get(j));
                }
            }
            this.preguntasOrdenadas.put(i + 1, new Categoria(preguntasCategoria, preguntasCategoria.size()));
        }
    }

    void custom() {
        this.getContentPane().setBackground(decode("#344035"));
        getRootPane().setBorder(BorderFactory.createLineBorder(decode("#8C5423"), 10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numJugadores = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        LabelNombrej2 = new javax.swing.JLabel();
        LabelNombrej1 = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        nombreJ3 = new javax.swing.JTextField();
        LabelNombrej3 = new javax.swing.JLabel();
        comboBoxCasillas = new javax.swing.JComboBox<>();
        RadioButton1j = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        RadioButton3j = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        RadioButton2j = new javax.swing.JRadioButton();
        nombreJ1 = new javax.swing.JTextField();
        nombreJ2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CategoriaCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaCategorias = new javax.swing.JList<>();
        BorrarBTN = new javax.swing.JButton();
        RegresarBTN = new javax.swing.JButton();
        comboBoxPreguntas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        player3 = new javax.swing.JButton();
        player1 = new javax.swing.JButton();
        player2 = new javax.swing.JButton();
        VentanaMemes = new javax.swing.JPanel();
        x6 = new javax.swing.JButton();
        x7 = new javax.swing.JButton();
        x8 = new javax.swing.JButton();
        x9 = new javax.swing.JButton();
        x10 = new javax.swing.JButton();
        x5 = new javax.swing.JButton();
        x1 = new javax.swing.JButton();
        x2 = new javax.swing.JButton();
        x4 = new javax.swing.JButton();
        x3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Personalizar partida");
        setBackground(new java.awt.Color(52, 64, 53));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 64, 53));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 84, 35), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelNombrej2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelNombrej2.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombrej2.setText("Nombre del jugador 2:");
        jPanel1.add(LabelNombrej2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        LabelNombrej1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelNombrej1.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombrej1.setText("Nombre del jugador 1:");
        jPanel1.add(LabelNombrej1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        start.setBackground(new java.awt.Color(255, 255, 255));
        start.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        start.setText("Iniciar");
        start.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMouseClicked(evt);
            }
        });
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        jPanel1.add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 480, 80, 30));

        nombreJ3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombreJ3.setText("Jugador3");
        jPanel1.add(nombreJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 100, -1));

        LabelNombrej3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelNombrej3.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombrej3.setText("Nombre del jugador 3:");
        jPanel1.add(LabelNombrej3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        comboBoxCasillas.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxCasillas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboBoxCasillas.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxCasillas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "9", "16", "49", "64" }));
        comboBoxCasillas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        comboBoxCasillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCasillasActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxCasillas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 91, -1));

        numJugadores.add(RadioButton1j);
        RadioButton1j.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        RadioButton1j.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton1j.setText("1 jugador");
        RadioButton1j.setPreferredSize(new java.awt.Dimension(128, 28));
        RadioButton1j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton1jActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton1j, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 120, -1));

        jLabel5.setFont(new java.awt.Font("Ink Free", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Personalice la partida");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Número de jugadores:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        numJugadores.add(RadioButton3j);
        RadioButton3j.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        RadioButton3j.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton3j.setText("3 jugadores");
        RadioButton3j.setPreferredSize(new java.awt.Dimension(128, 28));
        RadioButton3j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton3jActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton3j, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Seleccione la duración por pregunta:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccione 6 categorías:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        numJugadores.add(RadioButton2j);
        RadioButton2j.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        RadioButton2j.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton2j.setText("2 jugadores");
        RadioButton2j.setPreferredSize(new java.awt.Dimension(128, 28));
        RadioButton2j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton2jActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton2j, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        nombreJ1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombreJ1.setText("Jugador 1");
        nombreJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreJ1ActionPerformed(evt);
            }
        });
        jPanel1.add(nombreJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 100, -1));

        nombreJ2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombreJ2.setText("Jugador2");
        jPanel1.add(nombreJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 100, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/book.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 80, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/book.png"))); // NOI18N
        jLabel7.setText("jLabel6");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 80, 60));

        CategoriaCB.setBackground(new java.awt.Color(255, 255, 255));
        CategoriaCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CategoriaCB.setForeground(new java.awt.Color(0, 0, 0));
        CategoriaCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CategoriaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaCBActionPerformed(evt);
            }
        });
        jPanel1.add(CategoriaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 150, -1));

        ListaCategorias.setBackground(new java.awt.Color(52, 64, 53));
        ListaCategorias.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
        ListaCategorias.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(ListaCategorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 180, 170));

        BorrarBTN.setBackground(new java.awt.Color(255, 255, 255));
        BorrarBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BorrarBTN.setText("Borrar");
        BorrarBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BorrarBTN.setPreferredSize(new java.awt.Dimension(65, 30));
        BorrarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarBTNActionPerformed(evt);
            }
        });
        jPanel1.add(BorrarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, -1, -1));

        RegresarBTN.setBackground(new java.awt.Color(255, 255, 255));
        RegresarBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RegresarBTN.setText("Regresar");
        RegresarBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        RegresarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarBTNActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 80, 30));

        comboBoxPreguntas.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxPreguntas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboBoxPreguntas.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxPreguntas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----", "00:05", "00:10", "00:30", "00:45", "01:00" }));
        comboBoxPreguntas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.add(comboBoxPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Seleccione el número de casillas:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        player3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/c5am.png"))); // NOI18N
        player3.setBorder(null);
        player3.setFocusable(false);
        player3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player3ActionPerformed(evt);
            }
        });
        jPanel1.add(player3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 40, 40));

        player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/c9r.png"))); // NOI18N
        player1.setBorder(null);
        player1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player1ActionPerformed(evt);
            }
        });
        jPanel1.add(player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 40, 40));

        player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/c8az.png"))); // NOI18N
        player2.setBorder(null);
        player2.setFocusable(false);
        player2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2ActionPerformed(evt);
            }
        });
        jPanel1.add(player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 40, 40));

        VentanaMemes.setBackground(new java.awt.Color(217, 155, 102));

        x6.setBorder(null);
        x6.setPreferredSize(new java.awt.Dimension(40, 40));
        x6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x6ActionPerformed(evt);
            }
        });

        x7.setBorder(null);
        x7.setPreferredSize(new java.awt.Dimension(40, 40));
        x7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x7ActionPerformed(evt);
            }
        });

        x8.setBorder(null);
        x8.setPreferredSize(new java.awt.Dimension(40, 40));
        x8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x8ActionPerformed(evt);
            }
        });

        x9.setBorder(null);
        x9.setPreferredSize(new java.awt.Dimension(40, 40));
        x9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x9ActionPerformed(evt);
            }
        });

        x10.setBorder(null);
        x10.setPreferredSize(new java.awt.Dimension(40, 40));
        x10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x10ActionPerformed(evt);
            }
        });

        x5.setBorder(null);
        x5.setPreferredSize(new java.awt.Dimension(40, 40));
        x5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x5ActionPerformed(evt);
            }
        });

        x1.setBorder(null);
        x1.setPreferredSize(new java.awt.Dimension(40, 40));
        x1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x1ActionPerformed(evt);
            }
        });

        x2.setBorder(null);
        x2.setPreferredSize(new java.awt.Dimension(40, 40));
        x2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x2ActionPerformed(evt);
            }
        });

        x4.setBorder(null);
        x4.setPreferredSize(new java.awt.Dimension(40, 40));
        x4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x4ActionPerformed(evt);
            }
        });

        x3.setBorder(null);
        x3.setPreferredSize(new java.awt.Dimension(40, 40));
        x3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Elige un personaje");

        javax.swing.GroupLayout VentanaMemesLayout = new javax.swing.GroupLayout(VentanaMemes);
        VentanaMemes.setLayout(VentanaMemesLayout);
        VentanaMemesLayout.setHorizontalGroup(
            VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaMemesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(VentanaMemesLayout.createSequentialGroup()
                        .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(VentanaMemesLayout.createSequentialGroup()
                                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(VentanaMemesLayout.createSequentialGroup()
                                .addComponent(x9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(x10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        VentanaMemesLayout.setVerticalGroup(
            VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaMemesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, VentanaMemesLayout.createSequentialGroup()
                            .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(x10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(VentanaMemesLayout.createSequentialGroup()
                            .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(x9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(VentanaMemesLayout.createSequentialGroup()
                        .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(VentanaMemesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 163, Short.MAX_VALUE)
                    .addComponent(VentanaMemes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 162, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VentanaMemes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxCasillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCasillasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxCasillasActionPerformed

    private void RadioButton1jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton1jActionPerformed
        // TODO add your handling code here:
        players.clear();
        LabelNombrej1.setVisible(true);
        nombreJ1.setVisible(true);
        LabelNombrej2.setVisible(false);
        nombreJ2.setVisible(false);
        LabelNombrej3.setVisible(false);
        nombreJ3.setVisible(false);
        player1.setVisible(true);
        player2.setVisible(false);
        player3.setVisible(false);
        tipo = 0;
    }//GEN-LAST:event_RadioButton1jActionPerformed

    private void RadioButton2jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton2jActionPerformed
        // TODO add your handling code here:
        players.clear();
        LabelNombrej1.setVisible(true);
        nombreJ1.setVisible(true);
        LabelNombrej2.setVisible(true);
        nombreJ2.setVisible(true);
        LabelNombrej3.setVisible(false);
        nombreJ3.setVisible(false);
        player1.setVisible(true);
        player2.setVisible(true);
        player3.setVisible(false);
        tipo = 1;
    }//GEN-LAST:event_RadioButton2jActionPerformed

    private void RadioButton3jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton3jActionPerformed
        // TODO add your handling code here:
        players.clear();
        LabelNombrej1.setVisible(true);
        nombreJ1.setVisible(true);
        LabelNombrej2.setVisible(true);
        nombreJ2.setVisible(true);
        LabelNombrej3.setVisible(true);
        nombreJ3.setVisible(true);
        player1.setVisible(true);
        player2.setVisible(true);
        player3.setVisible(true);
        tipo = 1;
    }//GEN-LAST:event_RadioButton3jActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        switch (this.comboBoxPreguntas.getSelectedItem().toString()) {
            case "00:05":
                this.tiempoTurno = 5;
                break;
            case "00:10":
                this.tiempoTurno = 10;
                break;
            case "00:30":
                this.tiempoTurno = 30;
                break;
            case "00:45":
                this.tiempoTurno = 45;
                break;
            case "01:00":
                this.tiempoTurno = 60;
                break;
        }
        if (RadioButton1j.isSelected()) {
            String name = nombreJ1.getText();
            j1 = new Jugador(name, 0, 1, 0);
            players.add(j1);
        }

        if (RadioButton2j.isSelected()) {
            String name1 = nombreJ1.getText();
            String name2 = nombreJ2.getText();
            j1 = new Jugador(name1, 0, 1, 0);
            j2 = new Jugador(name2, 0, 2, 0);
            players.add(j1);
            players.add(j2);
        }
        if (RadioButton3j.isSelected()) {
            String name1 = nombreJ1.getText();
            String name2 = nombreJ2.getText();
            String name3 = nombreJ3.getText();
            j1 = new Jugador(name1, 0, 1, 0);
            j2 = new Jugador(name2, 0, 2, 0);
            j3 = new Jugador(name3, 0, 3, 0);
            players.add(j1);
            players.add(j2);
            players.add(j3);
        }
        String celdasN = comboBoxCasillas.getSelectedItem().toString();
        if (!celdasN.equals("---")) {
            this.celdas = Integer.parseInt(celdasN);
        }
        for (int i = 0; i < ListaCategorias.getModel().getSize(); i++) {
            this.listaCatego.add(ListaCategorias.getModel().getElementAt(i));
        }
        this.ordenarPreguntas();

        if (RadioButton1j.isSelected() & !nombreJ1.getText().isEmpty() & ListaCategorias.getModel().getSize() == 6 & !comboBoxCasillas.getSelectedItem().equals("---") & !comboBoxPreguntas.getSelectedItem().equals("---")) {
            try {
                VJugar vj = new VJugar(this.celdas, this.players, this.tipo, this.preguntasOrdenadas, this.listaCatego, this.conR, this.tiempoTurno, this.imgjugador1, this.imgjugador2, this.imgjugador3);
                vj.setVisible(true);
                sonidoMenu.stop();
                this.dispose();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (RadioButton2j.isSelected() & !nombreJ2.getText().isEmpty() & !nombreJ1.getText().isEmpty() & ListaCategorias.getModel().getSize() == 6 & !comboBoxCasillas.getSelectedItem().equals("---") & !comboBoxPreguntas.getSelectedItem().equals("---")) {
            try {
                VJugar vj = new VJugar(this.celdas, this.players, this.tipo, this.preguntasOrdenadas, this.listaCatego, this.conR, this.tiempoTurno, this.imgjugador1, this.imgjugador2, this.imgjugador3);
                vj.setVisible(true);
                sonidoMenu.stop();
                this.dispose();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (RadioButton3j.isSelected() & !nombreJ1.getText().isEmpty() & !nombreJ2.getText().isEmpty() & !nombreJ3.getText().isEmpty() & ListaCategorias.getModel().getSize() == 6 & !comboBoxCasillas.getSelectedItem().equals("---") & !comboBoxPreguntas.getSelectedItem().equals("---")) {
            try {
                VJugar vj = new VJugar(this.celdas, this.players, this.tipo, this.preguntasOrdenadas, this.listaCatego, this.conR, this.tiempoTurno, this.imgjugador1, this.imgjugador2, this.imgjugador3);
                vj.setVisible(true);
                sonidoMenu.stop();
                this.dispose();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Algo está vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_startActionPerformed

    private void nombreJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreJ1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreJ1ActionPerformed

    private void startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMouseClicked

    }//GEN-LAST:event_startMouseClicked

    private void CategoriaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaCBActionPerformed
        String seleccion = CategoriaCB.getSelectedItem().toString();
        if (!seleccion.equals("---")) {
            if (ListaCategorias.getModel().getSize() <= 5 & !list.contains(seleccion)) {
                list.addElement(seleccion);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede repetir o tener más de 6 categorías", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_CategoriaCBActionPerformed

    private void BorrarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarBTNActionPerformed
        int seleccion = ListaCategorias.getSelectedIndex();
        System.out.println(seleccion);
        if (seleccion != -1) {
            list.remove(seleccion);
        }
    }//GEN-LAST:event_BorrarBTNActionPerformed

    private void RegresarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarBTNActionPerformed
//        VPrincipal vp = null;
//        try {
//            vp = new VPrincipal(this.conR);
//        } catch (SQLException ex) {
//            Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        vp.setVisible(true);
//        this.dispose();
        if (conR != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = true;
                VPrincipal vp = new VPrincipal(null, this.conR,status);
                vp.setVisible(true);
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
                boolean status = true;
                VPrincipal vp = new VPrincipal(status);
                vp.setVisible(true);
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

    }//GEN-LAST:event_RegresarBTNActionPerformed

    private void player1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player1ActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(false);

        ImageIcon I1 = new ImageIcon(getClass().getResource("/resource/c1r.png"));
        ImageIcon I2 = new ImageIcon(getClass().getResource("/resource/c2r.png"));
        ImageIcon I3 = new ImageIcon(getClass().getResource("/resource/c3r.png"));
        ImageIcon I4 = new ImageIcon(getClass().getResource("/resource/c4r.png"));
        ImageIcon I5 = new ImageIcon(getClass().getResource("/resource/c5r.png"));
        ImageIcon I6 = new ImageIcon(getClass().getResource("/resource/c6r.png"));
        ImageIcon I7 = new ImageIcon(getClass().getResource("/resource/c7r.png"));
        ImageIcon I8 = new ImageIcon(getClass().getResource("/resource/c8r.png"));
        ImageIcon I9 = new ImageIcon(getClass().getResource("/resource/c9r.png"));
        ImageIcon I10 = new ImageIcon(getClass().getResource("/resource/c10r.png"));

        x1.setSize(40, 40);
        x2.setSize(40, 40);
        x3.setSize(40, 40);
        x4.setSize(40, 40);
        x5.setSize(40, 40);
        x6.setSize(40, 40);
        x7.setSize(40, 40);
        x8.setSize(40, 40);
        x9.setSize(40, 40);
        x10.setSize(40, 40);

        x1.setIcon(I1);
        x2.setIcon(I2);
        x3.setIcon(I3);
        x4.setIcon(I4);
        x5.setIcon(I5);
        x6.setIcon(I6);
        x7.setIcon(I7);
        x8.setIcon(I8);
        x9.setIcon(I9);
        x10.setIcon(I10);

        n = 1;

        VentanaMemes.setVisible(true);
    }//GEN-LAST:event_player1ActionPerformed

    private void x1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x1ActionPerformed
        // TODO add your handling code here:

        if (n == 1) {
            this.imgjugador1 = "/resource/c1rojo.png";
            player1.setIcon(x1.getIcon());
            VentanaMemes.setVisible(false);

//            CategoriaCB.setVisible(true);
//            comboBoxCasillas.setVisible(true);
//            comboBoxPreguntas.setVisible(true);
        }

        if (n == 2) {
            this.imgjugador2 = "/resource/c1azul.png";
            player2.setIcon(x1.getIcon());
            VentanaMemes.setVisible(false);

//            CategoriaCB.setVisible(true);
//            comboBoxCasillas.setVisible(true);
//            comboBoxPreguntas.setVisible(true);
        }

        if (n == 3) {
            this.imgjugador3 = "/resource/c1amarillo.png";
            player3.setIcon(x1.getIcon());
            VentanaMemes.setVisible(false);

//            CategoriaCB.setVisible(true);
//            comboBoxCasillas.setVisible(true);
//            comboBoxPreguntas.setVisible(true);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);

    }//GEN-LAST:event_x1ActionPerformed

    private void player2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2ActionPerformed
        // TODO add your handling code here:
//        CategoriaCB.setVisible(false);
//        comboBoxCasillas.setVisible(false);
//        comboBoxPreguntas.setVisible(false);
        jPanel1.setVisible(false);

        ImageIcon I1 = new ImageIcon(getClass().getResource("/resource/c1az.png"));
        ImageIcon I2 = new ImageIcon(getClass().getResource("/resource/c2az.png"));
        ImageIcon I3 = new ImageIcon(getClass().getResource("/resource/c3az.png"));
        ImageIcon I4 = new ImageIcon(getClass().getResource("/resource/c4az.png"));
        ImageIcon I5 = new ImageIcon(getClass().getResource("/resource/c5az.png"));
        ImageIcon I6 = new ImageIcon(getClass().getResource("/resource/c6az.png"));
        ImageIcon I7 = new ImageIcon(getClass().getResource("/resource/c7az.png"));
        ImageIcon I8 = new ImageIcon(getClass().getResource("/resource/c8az.png"));
        ImageIcon I9 = new ImageIcon(getClass().getResource("/resource/c9az.png"));
        ImageIcon I10 = new ImageIcon(getClass().getResource("/resource/c10az.png"));

        x1.setSize(40, 40);
        x2.setSize(40, 40);
        x3.setSize(40, 40);
        x4.setSize(40, 40);
        x5.setSize(40, 40);
        x6.setSize(40, 40);
        x7.setSize(40, 40);
        x8.setSize(40, 40);
        x9.setSize(40, 40);
        x10.setSize(40, 40);

        x1.setIcon(I1);
        x2.setIcon(I2);
        x3.setIcon(I3);
        x4.setIcon(I4);
        x5.setIcon(I5);
        x6.setIcon(I6);
        x7.setIcon(I7);
        x8.setIcon(I8);
        x9.setIcon(I9);
        x10.setIcon(I10);

        n = 2;

        VentanaMemes.setVisible(true);
    }//GEN-LAST:event_player2ActionPerformed

    private void player3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player3ActionPerformed
        // TODO add your handling code here:
//        CategoriaCB.setVisible(false);
//        comboBoxCasillas.setVisible(false);
//        comboBoxPreguntas.setVisible(false);
        jPanel1.setVisible(false);
        ImageIcon I1 = new ImageIcon(getClass().getResource("/resource/c1am.png"));
        ImageIcon I2 = new ImageIcon(getClass().getResource("/resource/c2am.png"));
        ImageIcon I3 = new ImageIcon(getClass().getResource("/resource/c3am.png"));
        ImageIcon I4 = new ImageIcon(getClass().getResource("/resource/c4am.png"));
        ImageIcon I5 = new ImageIcon(getClass().getResource("/resource/c5am.png"));
        ImageIcon I6 = new ImageIcon(getClass().getResource("/resource/c6am.png"));
        ImageIcon I7 = new ImageIcon(getClass().getResource("/resource/c7am.png"));
        ImageIcon I8 = new ImageIcon(getClass().getResource("/resource/c8am.png"));
        ImageIcon I9 = new ImageIcon(getClass().getResource("/resource/c9am.png"));
        ImageIcon I10 = new ImageIcon(getClass().getResource("/resource/c10am.png"));

        x1.setSize(40, 40);
        x2.setSize(40, 40);
        x3.setSize(40, 40);
        x4.setSize(40, 40);
        x5.setSize(40, 40);
        x6.setSize(40, 40);
        x7.setSize(40, 40);
        x8.setSize(40, 40);
        x9.setSize(40, 40);
        x10.setSize(40, 40);

        x1.setIcon(I1);
        x2.setIcon(I2);
        x3.setIcon(I3);
        x4.setIcon(I4);
        x5.setIcon(I5);
        x6.setIcon(I6);
        x7.setIcon(I7);
        x8.setIcon(I8);
        x9.setIcon(I9);
        x10.setIcon(I10);

        n = 3;

        VentanaMemes.setVisible(true);
    }//GEN-LAST:event_player3ActionPerformed

    private void x2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x2ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c2rojo.png";
            player1.setIcon(x2.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c2azul.png";
            player2.setIcon(x2.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c2amarillo.png";
            player3.setIcon(x2.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x2ActionPerformed

    private void x3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x3ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c3rojo.png";
            player1.setIcon(x3.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c3azul.png";
            player2.setIcon(x3.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c3amarillo.png";
            player3.setIcon(x3.getIcon());
            VentanaMemes.setVisible(false);
        }

//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x3ActionPerformed

    private void x4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x4ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c4rojo.png";
            player1.setIcon(x4.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c4azul.png";
            player2.setIcon(x4.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c4amarillo.png";
            player3.setIcon(x4.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x4ActionPerformed

    private void x5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x5ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c5rojo.png";
            player1.setIcon(x5.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c5azul.png";
            player2.setIcon(x5.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c5amarillo.png";
            player3.setIcon(x5.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x5ActionPerformed

    private void x6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x6ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c6rojo.png";
            player1.setIcon(x6.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c6azul.png";
            player2.setIcon(x6.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c6amarillo.png";
            player3.setIcon(x6.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x6ActionPerformed

    private void x7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x7ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c7rojo.png";
            player1.setIcon(x7.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c7azul.png";
            player2.setIcon(x7.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c7amarillo.png";
            player3.setIcon(x7.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x7ActionPerformed

    private void x8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x8ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c8rojo.png";
            player1.setIcon(x8.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c8azul.png";
            player2.setIcon(x8.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c8amarillo.png";
            player3.setIcon(x8.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x8ActionPerformed

    private void x9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x9ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c9rojo.png";
            player1.setIcon(x9.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c9azul.png";
            player2.setIcon(x9.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c9amarillo.png";
            player3.setIcon(x9.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x9ActionPerformed

    private void x10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x10ActionPerformed
        // TODO add your handling code here:
        if (n == 1) {
            imgjugador1 = "/resource/c10rojo.png";
            player1.setIcon(x10.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 2) {
            imgjugador2 = "/resource/c10azul.png";
            player2.setIcon(x10.getIcon());
            VentanaMemes.setVisible(false);
        }

        if (n == 3) {
            imgjugador3 = "/resource/c10amarillo.png";
            player3.setIcon(x10.getIcon());
            VentanaMemes.setVisible(false);
        }
//        CategoriaCB.setVisible(true);
//        comboBoxCasillas.setVisible(true);
//        comboBoxPreguntas.setVisible(true);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_x10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BorrarBTN;
    private javax.swing.JComboBox<String> CategoriaCB;
    private javax.swing.JLabel LabelNombrej1;
    private javax.swing.JLabel LabelNombrej2;
    private javax.swing.JLabel LabelNombrej3;
    private javax.swing.JList<String> ListaCategorias;
    private javax.swing.JRadioButton RadioButton1j;
    private javax.swing.JRadioButton RadioButton2j;
    private javax.swing.JRadioButton RadioButton3j;
    private javax.swing.JButton RegresarBTN;
    private javax.swing.JPanel VentanaMemes;
    private javax.swing.JComboBox<String> comboBoxCasillas;
    private javax.swing.JComboBox<String> comboBoxPreguntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreJ1;
    private javax.swing.JTextField nombreJ2;
    private javax.swing.JTextField nombreJ3;
    private javax.swing.ButtonGroup numJugadores;
    private javax.swing.JButton player1;
    private javax.swing.JButton player2;
    private javax.swing.JButton player3;
    private javax.swing.JButton start;
    private javax.swing.JButton x1;
    private javax.swing.JButton x10;
    private javax.swing.JButton x2;
    private javax.swing.JButton x3;
    private javax.swing.JButton x4;
    private javax.swing.JButton x5;
    private javax.swing.JButton x6;
    private javax.swing.JButton x7;
    private javax.swing.JButton x8;
    private javax.swing.JButton x9;
    // End of variables declaration//GEN-END:variables
}
