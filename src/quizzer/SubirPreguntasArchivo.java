package quizzer;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class SubirPreguntasArchivo extends javax.swing.JFrame {

    Connection con;
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    boolean aceptado = false;

    public SubirPreguntasArchivo(Connection con) {
        initComponents();
        customJOptionPane();
        custom();
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        SubirPreguntasArchivo.this.setLocationRelativeTo(null);
        this.con = con;
        this.archivoTF.setEditable(false);
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
        this.getContentPane().setBackground(decode("#344035"));
        getRootPane().setBorder(BorderFactory.createLineBorder(decode("#8C5423"), 10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        archivoTF = new javax.swing.JTextField();
        SelectButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Selecciona archivo");

        archivoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoTFActionPerformed(evt);
            }
        });

        SelectButton.setBackground(new java.awt.Color(255, 255, 255));
        SelectButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SelectButton.setForeground(new java.awt.Color(0, 0, 0));
        SelectButton.setText("...");
        SelectButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectButtonActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Aceptar");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        volverButton.setBackground(new java.awt.Color(255, 255, 255));
        volverButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        volverButton.setForeground(new java.awt.Color(0, 0, 0));
        volverButton.setText("Volver");
        volverButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(volverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(archivoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(archivoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectButtonActionPerformed
        archivoTF.setEditable(true);
        if (this.seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            this.archivo = seleccionar.getSelectedFile();
            if (this.archivo.canRead()) {
                if (this.archivo.getName().endsWith("txt")) {
                    this.aceptado = true;
                    archivoTF.setText(this.archivo.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no compatible");
                }
            }
        }
        archivoTF.setEditable(false);
    }//GEN-LAST:event_SelectButtonActionPerformed

    private void archivoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_archivoTFActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //C:\Users\aaron\OneDrive\Escritorio\asd.txt
//        String document = "";
//        try {
//            this.entrada = new FileInputStream(this.archivo);
//            int ascci;
//            while ((ascci = this.entrada.read()) != -1) {
//                char caracter = (char) ascci;
//                document += caracter;
//            }
//        } catch (Exception e) {
//
//        }

        if (this.aceptado) {

            BufferedReader reader;
            String[] q;
            PreparedStatement PS = null;
            try {
                System.out.println(archivoTF.getText());
                reader = new BufferedReader(new FileReader(
                        archivoTF.getText()));
                String line = reader.readLine();
                while (line != null) {
                    //pregunta#respuestacorrecta#respuestaincorrecta1#respuestaincorrecta2#categoria
                    System.out.println(line);

                    //q = line.split("\\s*#\\s*");
                    q = line.split("#");

                    System.out.println(q[0]);
                    System.out.println(q[1]);
                    System.out.println(q[2]);
                    System.out.println(q[3]);
                    System.out.println(q[4]);

                    String sql;
                    try {
                        sql = "INSERT INTO Preguntas (Pregunta, Categoria, RespuestaCorrecta, RespuestaIncorrecta1, RespuestaIncorrecta2) VALUES (?, ?, ?, ?, ?)";
                        PS = con.prepareStatement(sql);
                        PS.setString(1, q[0]);
                        PS.setString(2, q[1]);
                        PS.setString(3, q[2]);
                        PS.setString(4, q[3]);
                        PS.setString(5, q[4]);

                        PS.executeUpdate();

                        JOptionPane.showMessageDialog(null, "<html><h3>Datos añadidos con éxito</h3></html>", "Añadir", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "<html><h3>Algo salió mal con la conexión a la base de datos.</h3></html>", "ERROR", JOptionPane.ERROR_MESSAGE);
                        System.err.println(ex.getMessage());
                    }
                    line = reader.readLine();
                }
                PS.close();
                reader.close();
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(null, "<html><h3>Algo salió mal con el archivo.</h3></html>", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverButtonActionPerformed
        try {
            SubirPreguntas sp = new SubirPreguntas(this.con);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            sp.setVisible(true);
            this.dispose();
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SubirPreguntasArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubirPreguntasArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SubirPreguntasArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SubirPreguntasArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_volverButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SelectButton;
    private javax.swing.JTextField archivoTF;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables
}
