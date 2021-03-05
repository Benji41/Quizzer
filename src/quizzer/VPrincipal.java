/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import objetos.Jugador;
import objetos.Pregunta;
import org.sqlite.JDBC;

/**
 *
 * @author Benjimon41
 */
public class VPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VPrincipal
     */
    FondoPanel fondo = new FondoPanel();
    public Connection con = null;
    public Connection con2 = null;
    public String urlR[] = new String[3];
    public String urlL;
    Timer t;
    ArrayList<objetos.Pregunta> preguntas;
    ArrayList<String> categorias;
    URL url = getClass().getResource("/resource/fondo.jpg");
    URL path = getClass().getResource("/resource/localdbog.db");
    String queryCat = "select distinct Categoria from dbo.Preguntas;";
    String queryCatL = "select distinct Categoria from Pregunta;";
    String queryQuest = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from dbo.Preguntas;";
    String queryQuestL = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from Pregunta;";
    int tipo = 1;
    int celdas = 16;
    ArrayList<Jugador> players = new ArrayList<>();
    Jugador x = new Jugador("benji", 0, 1);
    Jugador y = new Jugador("hector", 0, 2);
    Jugador z = new Jugador("denia", 0, 3);

    public VPrincipal() throws InterruptedException, ClassNotFoundException {
        initComponents();
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        this.preguntas = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.btnPlay.setEnabled(false);
        this.btnStudy.setEnabled(false);
        this.btnUploadQuestions.setEnabled(false);
        this.lb_conexion.setText("Esperando a servicios online");
        urlR[0] = "jdbc:sqlserver://localhost:1433;databaseName=Quizzer";
        urlR[1] = "sa";
        urlR[2] = "lalito24";
        urlL = "jdbc:sqlite:" + path.getFile().substring(1);
        this.connectLocal();
        t = new Timer();
        this.busqueda();
    }

    public void busqueda() {
        TimerTask tl = new TimerTask() {
            int time = 2;

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
                                Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            lb_conexion.setText("Conectado con servicio local");
                            VPrincipal.this.pan_conexion.setBackground(Color.green.brighter());
                            VPrincipal.this.btnPlay.setEnabled(true);
                            VPrincipal.this.repaint();
                            Thread.sleep(1000);
                            VPrincipal.this.pan_conexion.setVisible(false);
                            cancel();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            Thread.sleep(1000);
                            lb_conexion.setText("Esperando a servicio local");
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
            int time = 3;

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
                            try {
                                VPrincipal.this.cargarPreguntasCatRemoto();
                            } catch (SQLException ex) {
                                Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }                        
                                                       
                            Thread.sleep(500);
                            VPrincipal.this.btnPlay.setEnabled(true);
                            VPrincipal.this.btnStudy.setEnabled(true);
                            VPrincipal.this.btnUploadQuestions.setEnabled(true);
                            VPrincipal.this.repaint();
                            
                            VPrincipal.this.pan_conexion.setVisible(false);
                            cancel();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                } else {
                    try {
                        Thread.sleep(1000);
                        lb_conexion.setText("Cambiando de servicio");
                        VPrincipal.this.repaint();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(urlR[0], urlR[1], urlR[2]);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }

    public Connection connectLocal() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            con2 = DriverManager.getConnection(urlL);
//            if (con2 != null) {
//                VPrincipal.this.btnScores.setEnabled(true);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return con2;
return null;
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
        btnUploadQuestions = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        pan_conexion = new javax.swing.JPanel();
        lb_conexion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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
                .addGap(38, 38, 38)
                .addComponent(lb_conexion, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(29, 29, 29))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStudy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPlay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnUploadQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145)))))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(pan_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pan_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUploadQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59))
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
        SubirPreguntas sb = new SubirPreguntas(con);
        sb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUploadQuestionsActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        try {
            System.exit(0);
            con2.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnStudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudyActionPerformed
        try {
            new Estudiar(con).setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStudyActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        VPersonalizacion vp = new VPersonalizacion(this.categorias, this.preguntas, this.con2);
        vp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPlayActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        new VPrincipal().setVisible(true);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStudy;
    private javax.swing.JButton btnUploadQuestions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
