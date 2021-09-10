package quizzer;

import java.awt.Color;
import static java.awt.Color.decode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class ActualizarPreguntasLocales extends javax.swing.JFrame {

    Connection con;
    int id;
    
    public ActualizarPreguntasLocales(int id, Connection con) {
        initComponents();
        this.id = id;
        this.con = con;
        this.setLocationRelativeTo(null);
        cargarCat();
        custom();
        customJOptionPane();
        String sql = "SELECT * FROM Pregunta WHERE IdPregunta = " + id;
        try {
            Statement stm = con.createStatement();
            con.setAutoCommit(true);
            ResultSet rs = stm.executeQuery(sql);
            PreguntaTF.setText(rs.getString("Pregunta"));
            RespuestaIncorrecta1TF.setText(rs.getString("RespuestaIncorrecta1"));
            RespuestaIncorrecta2TF.setText(rs.getString("RespuestaIncorrecta2"));
            RespuestaCorrectaTF.setText(rs.getString("RespuestaCorrecta"));
            CategoriaCB.setSelectedItem(rs.getString("Categoria"));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        NuevaCategoriaLabel.setVisible(false);
        NuevaCategoriaTF.setVisible(false);
    }
    
     void customJOptionPane() {
        try {
       
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
        this.getContentPane().setBackground(decode("#D99B66"));
    }

    public void cargarCat() {
        String sql = "SELECT Categoria FROM Pregunta";
        try {
            Statement stm = con.createStatement();
            con.setAutoCommit(true);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CategoriaCB.addItem(rs.getString(1));
            }
            CategoriaCB.addItem("Nueva categoría");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RespuestaIncorrecta1TF = new javax.swing.JTextField();
        CategoriaLabel = new javax.swing.JLabel();
        RespuestaCorrectaTF = new javax.swing.JTextField();
        RespuestaIncorrecta2Label = new javax.swing.JLabel();
        RespuestaIncorrecta2TF = new javax.swing.JTextField();
        RespuestaCorrectaLabel = new javax.swing.JLabel();
        ActualizarButton = new javax.swing.JButton();
        RegresarButton = new javax.swing.JButton();
        NuevaCategoriaTF = new javax.swing.JTextField();
        NuevaCategoriaLabel = new javax.swing.JLabel();
        PreguntaLabel = new javax.swing.JLabel();
        PreguntaTF = new javax.swing.JTextField();
        CategoriaCB = new javax.swing.JComboBox<>();
        RespuestaIncorrecta1Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 395));
        setPreferredSize(new java.awt.Dimension(415, 515));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(RespuestaIncorrecta1TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 350, 31));

        CategoriaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CategoriaLabel.setForeground(new java.awt.Color(0, 0, 0));
        CategoriaLabel.setText("Categoría:");
        getContentPane().add(CategoriaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, -1));
        getContentPane().add(RespuestaCorrectaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 350, 31));

        RespuestaIncorrecta2Label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RespuestaIncorrecta2Label.setForeground(new java.awt.Color(0, 0, 0));
        RespuestaIncorrecta2Label.setText("Respuesta Incorrecta 2:");
        getContentPane().add(RespuestaIncorrecta2Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 190, -1));
        getContentPane().add(RespuestaIncorrecta2TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 350, 31));

        RespuestaCorrectaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RespuestaCorrectaLabel.setForeground(new java.awt.Color(0, 0, 0));
        RespuestaCorrectaLabel.setText("Respuesta Correcta:");
        getContentPane().add(RespuestaCorrectaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, -1));

        ActualizarButton.setBackground(new java.awt.Color(255, 255, 255));
        ActualizarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ActualizarButton.setForeground(new java.awt.Color(0, 0, 0));
        ActualizarButton.setText("Actualizar datos");
        ActualizarButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ActualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ActualizarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 130, 50));

        RegresarButton.setBackground(new java.awt.Color(255, 255, 255));
        RegresarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RegresarButton.setForeground(new java.awt.Color(0, 0, 0));
        RegresarButton.setText("Regresar");
        RegresarButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        RegresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(RegresarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 110, 47));
        getContentPane().add(NuevaCategoriaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 350, 31));

        NuevaCategoriaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NuevaCategoriaLabel.setForeground(new java.awt.Color(0, 0, 0));
        NuevaCategoriaLabel.setText("Nueva categoria:");
        getContentPane().add(NuevaCategoriaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 150, -1));

        PreguntaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PreguntaLabel.setForeground(new java.awt.Color(0, 0, 0));
        PreguntaLabel.setText("Pregunta:");
        getContentPane().add(PreguntaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 110, -1));
        getContentPane().add(PreguntaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 350, 31));

        CategoriaCB.setBackground(new java.awt.Color(255, 255, 255));
        CategoriaCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CategoriaCB.setForeground(new java.awt.Color(0, 0, 0));
        CategoriaCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        CategoriaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaCBActionPerformed(evt);
            }
        });
        getContentPane().add(CategoriaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 350, 33));

        RespuestaIncorrecta1Label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RespuestaIncorrecta1Label.setForeground(new java.awt.Color(0, 0, 0));
        RespuestaIncorrecta1Label.setText("Respuesta Incorrecta 1:");
        getContentPane().add(RespuestaIncorrecta1Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarButtonActionPerformed
        String sql = "UPDATE Pregunta SET Pregunta = ?, RespuestaCorrecta = ?, RespuestaIncorrecta1 = ?, RespuestaIncorrecta2 = ?, Categoria = ? WHERE IdPregunta = ?";
        String pregunta = PreguntaTF.getText();
        String respuestaincorrecta1 = RespuestaIncorrecta1TF.getText();
        String respuestaincorrecta2 = RespuestaIncorrecta2TF.getText();
        String respuestacorrecta = RespuestaCorrectaTF.getText();
        String cat = CategoriaCB.getSelectedItem().toString();
        if (cat.equals("Nueva categoría")) {
            cat = NuevaCategoriaTF.getText();
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            con.setAutoCommit(true);
            ps.setString(1, pregunta);
            ps.setString(2, respuestacorrecta);
            ps.setString(3, respuestaincorrecta1);
            ps.setString(4, respuestaincorrecta2);
            ps.setString(5, cat);
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pregunta actualizada", "HECHO", JOptionPane.INFORMATION_MESSAGE);
            Administrar ad = new Administrar(con);
            ad.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(AñadirPreguntasLocales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ActualizarButtonActionPerformed

    private void RegresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarButtonActionPerformed
        try {
            Administrar ad = new Administrar(con);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            ad.setVisible(true);
            this.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActualizarPreguntasLocales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ActualizarPreguntasLocales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ActualizarPreguntasLocales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ActualizarPreguntasLocales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RegresarButtonActionPerformed

    private void CategoriaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaCBActionPerformed
        if (CategoriaCB.getSelectedItem().toString().equals("Nueva categoría")) {
            NuevaCategoriaLabel.setVisible(true);
            NuevaCategoriaTF.setVisible(true);
        } else {
            NuevaCategoriaLabel.setVisible(false);
            NuevaCategoriaTF.setVisible(false);
        }
    }//GEN-LAST:event_CategoriaCBActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualizarButton;
    private javax.swing.JComboBox<String> CategoriaCB;
    private javax.swing.JLabel CategoriaLabel;
    private javax.swing.JLabel NuevaCategoriaLabel;
    private javax.swing.JTextField NuevaCategoriaTF;
    private javax.swing.JLabel PreguntaLabel;
    private javax.swing.JTextField PreguntaTF;
    private javax.swing.JButton RegresarButton;
    private javax.swing.JLabel RespuestaCorrectaLabel;
    private javax.swing.JTextField RespuestaCorrectaTF;
    private javax.swing.JLabel RespuestaIncorrecta1Label;
    private javax.swing.JTextField RespuestaIncorrecta1TF;
    private javax.swing.JLabel RespuestaIncorrecta2Label;
    private javax.swing.JTextField RespuestaIncorrecta2TF;
    // End of variables declaration//GEN-END:variables

}
