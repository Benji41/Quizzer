/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizzer;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    public Connection con = null;
    public Connection con2 = null;
    public String urlR[] = new String[3];
    public String urlL;
    Timer t;
    ArrayList<objetos.Pregunta> preguntas;
    ArrayList<String> categorias;
    URL path = getClass().getResource("/resource/localdbog.db");
    String queryCat = "select distinct Categoria from dbo.Preguntas;";
    String queryCatL = "select distinct Categoria from Pregunta;";
    String queryQuest = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from dbo.Preguntas;";
    String queryQuestL = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from Pregunta;";

    public VPrincipal() throws InterruptedException, ClassNotFoundException {
        initComponents();
        this.preguntas = new ArrayList<Pregunta>();
        this.categorias = new ArrayList<String>();
        this.btnPlay.setEnabled(false);
        this.btnStudy.setEnabled(false);
        this.btnUploadQuestions.setEnabled(false);
        this.btnScores.setEnabled(false);
        this.btnAdmin.setEnabled(false);
        this.lb_conexion.setText("Esperando a servicios online");
        urlR[0] = "jdbc:sqlserver://189.173.190.140:1433;databaseName=Quizzer";
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
                System.out.println(time);
                if (time != 0) {
                    if (VPrincipal.this.connectRemote() != null) {
                        try {
                            Thread.sleep(1000);
                            lb_conexion.setText("Conectado con servicio online");
                            VPrincipal.this.btnPlay.setEnabled(true);
                            VPrincipal.this.btnStudy.setEnabled(true);
                            VPrincipal.this.btnUploadQuestions.setEnabled(true);
                            VPrincipal.this.pan_conexion.setBackground(Color.green.brighter());
                            VPrincipal.this.repaint();
                            Thread.sleep(500);
                            try {
                                VPrincipal.this.cargarPreguntasCatRemoto();
                            } catch (SQLException ex) {
                                Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
            System.out.println("entro1");
            con = DriverManager.getConnection(urlR[0], urlR[1], urlR[2]);
            System.out.println(con);
            System.out.println("Connected1");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }

    public Connection connectLocal() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            con2 = DriverManager.getConnection(urlL);
            if (con2 != null) {
                VPrincipal.this.btnScores.setEnabled(true);
                VPrincipal.this.btnAdmin.setEnabled(true);
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
            System.out.println(q + c + ans + wr1 + wr2);
            this.preguntas
                    .add(new Pregunta(q, c, ans, wr1, wr2));
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
            System.out.println(q + c + ans + wr1 + wr2);
            this.preguntas
                    .add(new Pregunta(q, c, ans, wr1, wr2));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pContenedor = new javax.swing.JPanel();
        btnQuit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnPlay = new javax.swing.JButton();
        btnStudy = new javax.swing.JButton();
        btnScores = new javax.swing.JButton();
        btnUploadQuestions = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        pan_conexion = new javax.swing.JPanel();
        lb_conexion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnQuit.setText("Salir");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        btnPlay.setText("Jugar");

        btnStudy.setText("Estudiar");
        btnStudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudyActionPerformed(evt);
            }
        });

        btnScores.setText("Scores");
        btnScores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScoresActionPerformed(evt);
            }
        });

        btnUploadQuestions.setText("Subir nuevas preguntas");
        btnUploadQuestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadQuestionsActionPerformed(evt);
            }
        });

        btnAdmin.setText("Administrador");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(btnScores, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnUploadQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnPlay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnScores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnUploadQuestions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdmin)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout pContenedorLayout = new javax.swing.GroupLayout(pContenedor);
        pContenedor.setLayout(pContenedorLayout);
        pContenedorLayout.setHorizontalGroup(
            pContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContenedorLayout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnQuit)
                .addGap(36, 36, 36))
        );
        pContenedorLayout.setVerticalGroup(
            pContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContenedorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuit))
                .addContainerGap(22, Short.MAX_VALUE))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_conexionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_conexion, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        pan_conexionLayout.setVerticalGroup(
            pan_conexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_conexionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_conexion))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(pan_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pan_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadQuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadQuestionsActionPerformed
        System.out.println(con);
        this.setVisible(false);
    }//GEN-LAST:event_btnUploadQuestionsActionPerformed

    private void btnScoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScoresActionPerformed
        t.cancel();
        new VScore(this.con2).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnScoresActionPerformed

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

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
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
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnScores;
    private javax.swing.JButton btnStudy;
    private javax.swing.JButton btnUploadQuestions;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JLabel lb_conexion;
    private javax.swing.JPanel pContenedor;
    private javax.swing.JPanel pan_conexion;
    // End of variables declaration//GEN-END:variables
}
