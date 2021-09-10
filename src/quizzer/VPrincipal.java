package quizzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import objetos.Jugador;
import objetos.Pregunta;
import org.sqlite.JDBC;
import java.applet.AudioClip;
import javax.swing.UIManager;

/**
 *
 * @author Benjimon41
 */
public final class VPrincipal extends javax.swing.JFrame {

    //GUI
    URL url = getClass().getResource("/resource/fondo.jpg");
    FondoPanel fondo = new FondoPanel();
    //Conectores, timer y credenciales
    Timer t;
    public Connection con;
    public Connection con2;
    public String urlR[] = new String[3];
    public String urlL;
    public String ip = "localhost";
    //Contenedeores de objetos
    ArrayList<objetos.Pregunta> preguntas;
    ArrayList<String> categorias;
    AudioClip sonidoMenu;
    static public boolean estadoSonido;

    //Consultas
    String queryCat = "select distinct Categoria from dbo.Preguntas;";
    String queryCatL = "select distinct Categoria from Pregunta;";
    String queryQuest = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from dbo.Preguntas;";
    String queryQuestL = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from Pregunta;";

    public VPrincipal(boolean status) throws InterruptedException, ClassNotFoundException {
        initComponents();
        this.btnScores.setEnabled(false);
        inicio();
        bloqueo();
        sonidoMenu = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/menu.wav"));
        this.estadoSonido=status;
        if(estadoSonido==false){
            estadoSonido=true;
            sonidoMenu.loop();
        
        }else{
            estadoSonido=false;
            sonidoMenu.stop();
        
        }
    }

    public VPrincipal(Connection conL, Connection conR, boolean status) throws InterruptedException, ClassNotFoundException, SQLException {
        initComponents();
        this.btnScores.setEnabled(false);
        this.btnPlay.setEnabled(false);
        VPrincipal.this.pan_conexion.setVisible(false);
        inicio();
        bloqueo();
        
        sonidoMenu = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/menu.wav"));
        this.estadoSonido=status;
        if(estadoSonido==false){
            estadoSonido=true;
            sonidoMenu.loop();
        }else{
            estadoSonido=false;
            sonidoMenu.stop();
        }
    }

    public boolean checkIfLetter(String strin) {
        char[] chars = strin.toCharArray();
        for (char c : chars) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public void Stop() {
        sonidoMenu.stop();
        estadoSonido=false;
    }
    
    public void Start() {
        sonidoMenu.loop();
        estadoSonido=true;
    }
    
    public void stopSound() {
    this.sonidoMenu.stop();
    }
    
    public void inicio() {
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        this.preguntas = new ArrayList<>();
        this.categorias = new ArrayList<>();
//        urlR[0] = "jdbc:sqlserver://localhost:1433;databaseName=Quizzer";
        urlR[0] = "jdbc:sqlserver://"+ip+":1433;databaseName=Quizzer";
        urlR[1] = "sa";
        urlR[2] = "lalito24";
        //"jdbc:sqlite:DB/local.db";
        urlL = "jdbc:sqlite:DB/localdb.db";
    }

    public void bloqueo() {
        this.btnPlay.setEnabled(false);
        this.btnStudy.setEnabled(false);
        this.AdministrarButton.setEnabled(false);
        this.btnUploadQuestions.setEnabled(false);
        this.ConnectButton.setEnabled(false);
        this.lb_conexion.setText("Esperando a servicios online");
        t = new Timer();
        this.busqueda();
    }

    public void busqueda() {
        TimerTask tl = new TimerTask() {
            int time = 5;

            @Override
            public void run() {
                time--;

                if (time != 0) {

                    if (VPrincipal.this.connectLocal() != null) {
                        try {
                            Thread.sleep(500);
                            try {
                                cargarPreguntasCatLocal();

                            } catch (SQLException ex) {
                                Logger.getLogger(VPrincipal.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                            lb_conexion.setText("Conectado con servicio local");
                            VPrincipal.this.pan_conexion.setBackground(Color.green.brighter());
                            VPrincipal.this.btnPlay.setEnabled(true);
                            VPrincipal.this.ConnectButton.setEnabled(true);
                            VPrincipal.this.AdministrarButton.setEnabled(true);
                            VPrincipal.this.repaint();
                            Thread.sleep(1000);
                            VPrincipal.this.pan_conexion.setVisible(false);
                            cancel();

                        } catch (InterruptedException ex) {
                            Logger.getLogger(VPrincipal.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            Thread.sleep(1000);
                            lb_conexion.setText("Esperando a servicio local");

                        } catch (InterruptedException ex) {
                            Logger.getLogger(VPrincipal.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    cancel();
                    lb_conexion.setText("No se pudo conectar a ningun servicio");
                    VPrincipal.this.pan_conexion.setBackground(Color.red);
                    VPrincipal.this.repaint();
                }
            }
        };
        TimerTask tr = new TimerTask() {
            int time = 2;

            @Override
            public void run() {
                time--;
                if (time != 0) {
                    if (VPrincipal.this.connectRemote() != null) {
                        try {
                            Thread.sleep(1000);
                            lb_conexion.setText("Conectado con servicio online");
                            VPrincipal.this.pan_conexion.setBackground(Color.green.brighter());
                            VPrincipal.this.repaint();
                            VPrincipal.this.connectLocal();
                            try {
                                VPrincipal.this.cargarPreguntasCatRemoto();

                            } catch (SQLException ex) {
                                Logger.getLogger(VPrincipal.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                            Thread.sleep(500);
                            VPrincipal.this.btnPlay.setEnabled(true);
                            VPrincipal.this.btnStudy.setEnabled(true);
                            VPrincipal.this.btnUploadQuestions.setEnabled(true);
                            VPrincipal.this.ConnectButton.setEnabled(true);
                            VPrincipal.this.AdministrarButton.setEnabled(true);
                            VPrincipal.this.repaint();

                            VPrincipal.this.pan_conexion.setVisible(false);
                            cancel();

                        } catch (InterruptedException ex) {
                            Logger.getLogger(VPrincipal.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    try {
                        Thread.sleep(1000);
                        lb_conexion.setText("Cambiando de servicio");
                        VPrincipal.this.repaint();

                    } catch (InterruptedException ex) {
                        Logger.getLogger(VPrincipal.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    t.schedule(tl, 0, 1000);
                    cancel();
                }
            }
        };
        t.schedule(tr, 0, 1000);      
    }

    public Connection connectRemote() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(urlR[0], urlR[1], urlR[2]);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }

    public Connection connectLocal() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con2 = DriverManager.getConnection(urlL);
            if (con2 != null) {
                con2.setAutoCommit(false);
                System.out.println("entro score");
                VPrincipal.this.btnScores.setEnabled(true);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con2;
    }

    public void cargarPreguntasCatRemoto() throws SQLException {
        ResultSet rs;
        PreparedStatement ps;
        ps = con.prepareStatement(this.queryQuest);
        rs = ps.executeQuery();

        while (rs.next()) {
            String q = rs.getString("Pregunta");
            String c = rs.getString("Categoria");
            String ans = rs.getString("RespuestaCorrecta");
            String wr1 = rs.getString("RespuestaIncorrecta1");
            String wr2 = rs.getString("RespuestaIncorrecta2");
            this.preguntas.add(new Pregunta(q, c, ans, wr1, wr2));
        }
        ps.close();
        rs.close();
        ps = null;
        rs = null;
        ps = con.prepareStatement(this.queryCat);
        rs = rs = ps.executeQuery();
        while (rs.next()) {
            String c = rs.getString("Categoria");
            this.categorias.add(c);
        }
        ps.close();
        rs.close();
        System.out.println("pre" + this.preguntas);
        System.out.println("que" + this.categorias);

    }

    public void cargarPreguntasCatLocal() throws SQLException {
        Statement st = null;
        con2.setAutoCommit(false);
        st = con2.createStatement();
        ResultSet rs = st.executeQuery(this.queryQuestL);

        while (rs.next()) {
            String q = rs.getString("Pregunta");
            String c = rs.getString("Categoria");
            String ans = rs.getString("RespuestaCorrecta");
            String wr1 = rs.getString("RespuestaIncorrecta1");
            String wr2 = rs.getString("RespuestaIncorrecta2");
            this.preguntas.add(new Pregunta(q, c, ans, wr1, wr2));
        }
        rs.close();
        st.close();
        st = null;
        rs = null;
        st = con2.createStatement();
        rs = st.executeQuery(this.queryCatL);
        while (rs.next()) {
            String c = rs.getString("Categoria");
            this.categorias.add(c);
        }
        rs.close();
        st.close();
        System.out.println("pre" + this.preguntas);
        System.out.println("que" + this.categorias);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pContenedor = new javax.swing.JPanel();
        jPanel1 = new FondoPanel();
        btnPlay = new javax.swing.JButton();
        btnStudy = new javax.swing.JButton();
        btnScores = new javax.swing.JButton();
        btnUploadQuestions = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        pan_conexion = new javax.swing.JPanel();
        lb_conexion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        IPv2TF = new javax.swing.JTextField();
        IPv3TF = new javax.swing.JTextField();
        IPv4TF = new javax.swing.JTextField();
        IPv1TF = new javax.swing.JTextField();
        Punto1Label = new javax.swing.JLabel();
        Punto2Label = new javax.swing.JLabel();
        Punto3Label = new javax.swing.JLabel();
        ConnectButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        AdministrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú");
        setBackground(new java.awt.Color(140, 84, 35));
        setResizable(false);

        btnPlay.setBackground(new java.awt.Color(255, 255, 255));
        btnPlay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlay.setText("Jugar");
        btnPlay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnPlay.setMaximumSize(new java.awt.Dimension(45, 30));
        btnPlay.setMinimumSize(new java.awt.Dimension(45, 30));
        btnPlay.setOpaque(false);
        btnPlay.setPreferredSize(new java.awt.Dimension(45, 30));
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnStudy.setBackground(new java.awt.Color(255, 255, 255));
        btnStudy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnStudy.setText("Estudiar");
        btnStudy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnStudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudyActionPerformed(evt);
            }
        });

        btnScores.setBackground(new java.awt.Color(255, 255, 255));
        btnScores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnScores.setText("Scores");
        btnScores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnScores.setPreferredSize(new java.awt.Dimension(52, 30));
        btnScores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScoresActionPerformed(evt);
            }
        });

        btnUploadQuestions.setBackground(new java.awt.Color(255, 255, 255));
        btnUploadQuestions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUploadQuestions.setText("Subir nuevas preguntas");
        btnUploadQuestions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnUploadQuestions.setPreferredSize(new java.awt.Dimension(141, 30));
        btnUploadQuestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadQuestionsActionPerformed(evt);
            }
        });

        btnQuit.setBackground(new java.awt.Color(255, 255, 255));
        btnQuit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnQuit.setText("Salir");
        btnQuit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnQuit.setPreferredSize(new java.awt.Dimension(55, 30));
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        pan_conexion.setBackground(new java.awt.Color(238, 238, 105));
        pan_conexion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_conexion.setFont(new java.awt.Font("Century Gothic", 3, 11)); // NOI18N
        lb_conexion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_conexion.setText("Conexión");
        lb_conexion.setToolTipText("");

        javax.swing.GroupLayout pan_conexionLayout = new javax.swing.GroupLayout(pan_conexion);
        pan_conexion.setLayout(pan_conexionLayout);
        pan_conexionLayout.setHorizontalGroup(
            pan_conexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_conexionLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lb_conexion, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );
        pan_conexionLayout.setVerticalGroup(
            pan_conexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_conexionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_conexion))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/school2 (1).png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/school (1).png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/logo.png"))); // NOI18N

        Punto1Label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        Punto1Label.setText(".");

        Punto2Label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        Punto2Label.setText(".");

        Punto3Label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        Punto3Label.setText(".");

        ConnectButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ConnectButton.setText("Conectar");
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Especificar IP:");

        AdministrarButton.setBackground(new java.awt.Color(255, 255, 255));
        AdministrarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AdministrarButton.setText("Administrar");
        AdministrarButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        AdministrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdministrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnScores, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(20, 20, 20))
                        .addComponent(btnUploadQuestions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(AdministrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pan_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(IPv1TF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(Punto1Label)
                                .addGap(4, 4, 4)
                                .addComponent(IPv2TF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(Punto2Label)
                                .addGap(2, 2, 2)
                                .addComponent(IPv3TF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(Punto3Label)
                                .addGap(4, 4, 4)
                                .addComponent(IPv4TF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(ConnectButton))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IPv1TF)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IPv2TF)
                    .addComponent(IPv3TF)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Punto1Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Punto2Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Punto3Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ConnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(IPv4TF))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnScores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUploadQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AdministrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pContenedorLayout = new javax.swing.GroupLayout(pContenedor);
        pContenedor.setLayout(pContenedorLayout);
        pContenedorLayout.setHorizontalGroup(
            pContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pContenedorLayout.setVerticalGroup(
            pContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadQuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadQuestionsActionPerformed
        try {
            this.con2.close();
            SubirPreguntas sb = new SubirPreguntas(con);
            sb.setVisible(true);
            this.dispose();

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUploadQuestionsActionPerformed

    private void btnScoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScoresActionPerformed
        VScore vs = new VScore(this.con2, this.con);
        vs.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnScoresActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        try {
            System.exit(0);
            con2.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnStudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudyActionPerformed
        try {
            new Estudiar(con).setVisible(true);
            this.setVisible(false);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (InterruptedException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStudyActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        System.out.println(con);
        System.out.println(con2);
        try {
            con2.close();

        } catch (SQLException ex) {
            Logger.getLogger(VPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        VPersonalizacion vp = new VPersonalizacion(this.categorias, this.preguntas, this.con, this.sonidoMenu);
        vp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        String n1 = IPv1TF.getText();
        String n2 = IPv2TF.getText();
        String n3 = IPv3TF.getText();
        String n4 = IPv4TF.getText();
        if (!checkIfLetter(n1) & !checkIfLetter(n2) & !checkIfLetter(n3) & !checkIfLetter(n4)) {
            ip = n1 + "." + n2 + "." + n3 + "." + n4;
        }
        if (con != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = true;
                VPrincipal vp = new VPrincipal(null, this.con,status);
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
        
    }//GEN-LAST:event_ConnectButtonActionPerformed

    private void AdministrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministrarButtonActionPerformed
        Administrar ad = new Administrar(con2);
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdministrarButtonActionPerformed
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        boolean status = false;
                        new VPrincipal(status).setVisible(true);

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(VPrincipal.class
                                .getName()).log(Level.SEVERE, null, ex);

                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(VPrincipal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdministrarButton;
    private javax.swing.JButton ConnectButton;
    private javax.swing.JTextField IPv1TF;
    private javax.swing.JTextField IPv2TF;
    private javax.swing.JTextField IPv3TF;
    private javax.swing.JTextField IPv4TF;
    private javax.swing.JLabel Punto1Label;
    private javax.swing.JLabel Punto2Label;
    private javax.swing.JLabel Punto3Label;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnScores;
    private javax.swing.JButton btnStudy;
    private javax.swing.JButton btnUploadQuestions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JLabel lb_conexion;
    private javax.swing.JPanel pContenedor;
    private javax.swing.JPanel pan_conexion;
    // End of variables declaration//GEN-END:variables
   class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(url).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }

}
