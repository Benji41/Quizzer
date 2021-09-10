package quizzer;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class SubirPreguntas extends javax.swing.JFrame {

    Connection con;
    DefaultListModel listado = new DefaultListModel();

    public SubirPreguntas(Connection con) throws UnsupportedLookAndFeelException {
        initComponents();
        customJOptionPane();
        NuevaCatLabel.setVisible(false);
        NuevaCatTF.setVisible(false);
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        this.con = con;
        String sql = "SELECT DISTINCT [Categoria] FROM [Quizzer].[dbo].[Preguntas]";
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CategoriaCB.addItem(rs.getString(1));
            }
            CategoriaCB.addItem("Nueva categoría");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void customJOptionPane() throws UnsupportedLookAndFeelException {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        CategoriaLabel = new javax.swing.JLabel();
        btnSubir = new javax.swing.JButton();
        CategoriaCB = new javax.swing.JComboBox<>();
        btnBorrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PreguntasGuardadasJL = new javax.swing.JList<>();
        ResIncorrLabel2 = new javax.swing.JLabel();
        PreguntaLabel = new javax.swing.JLabel();
        ResIncorrTF2 = new javax.swing.JTextField();
        ResCorrTF = new javax.swing.JTextField();
        ResCorrLabel = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        PreguntaTF = new javax.swing.JTextField();
        ResIncorrLabel1 = new javax.swing.JLabel();
        ResIncorrTF1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NuevaCatTF = new javax.swing.JTextField();
        NuevaCatLabel = new javax.swing.JLabel();
        txtButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Subir preguntas");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 64, 53));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 84, 35), 10));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnAgregar.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        CategoriaLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CategoriaLabel.setForeground(new java.awt.Color(255, 255, 255));
        CategoriaLabel.setText("Categoría");

        btnSubir.setBackground(new java.awt.Color(255, 255, 255));
        btnSubir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSubir.setText("Terminar y subir");
        btnSubir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnSubir.setPreferredSize(new java.awt.Dimension(125, 30));
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        CategoriaCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CategoriaCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CategoriaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaCBActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(255, 255, 255));
        btnBorrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnBorrar.setPreferredSize(new java.awt.Dimension(100, 30));
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        PreguntasGuardadasJL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane1.setViewportView(PreguntasGuardadasJL);

        ResIncorrLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResIncorrLabel2.setForeground(new java.awt.Color(255, 255, 255));
        ResIncorrLabel2.setText("Respuesta Incorrecta 2");

        PreguntaLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PreguntaLabel.setForeground(new java.awt.Color(255, 255, 255));
        PreguntaLabel.setText("Pregunta");

        ResIncorrTF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        ResCorrTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        ResCorrLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResCorrLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResCorrLabel.setText("Respuesta Correcta");

        btnAtras.setBackground(new java.awt.Color(255, 255, 255));
        btnAtras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAtras.setText("Volver");
        btnAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnAtras.setPreferredSize(new java.awt.Dimension(70, 30));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        PreguntaTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        ResIncorrLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResIncorrLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ResIncorrLabel1.setText("Respuesta Incorrecta 1");

        ResIncorrTF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/PC.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Ink Free", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sube tus preguntas para jugarlas cuando tengas conexión a internet...");

        NuevaCatLabel.setText("Nueva categoría:");

        txtButton.setBackground(new java.awt.Color(255, 255, 255));
        txtButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtButton.setText("Subir archivo txt");
        txtButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        txtButton.setPreferredSize(new java.awt.Dimension(70, 30));
        txtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PreguntaLabel)
                                    .addComponent(PreguntaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ResCorrLabel)
                                    .addComponent(ResCorrTF, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ResIncorrLabel1)
                                    .addComponent(ResIncorrTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ResIncorrLabel2)
                                    .addComponent(ResIncorrTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addComponent(CategoriaLabel)
                                        .addGap(222, 222, 222))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NuevaCatLabel)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(NuevaCatTF)
                                                .addComponent(CategoriaCB, 0, 157, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtButton, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(PreguntaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PreguntaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(ResCorrLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ResCorrTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(ResIncorrLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(ResIncorrTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CategoriaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CategoriaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(NuevaCatLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ResIncorrLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ResIncorrTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NuevaCatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String pregunta, categoria, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2;
        pregunta = PreguntaTF.getText();
        categoria = CategoriaCB.getSelectedItem().toString();
        respuestaCorrecta = ResCorrTF.getText();
        respuestaIncorrecta1 = ResIncorrTF1.getText();
        respuestaIncorrecta2 = ResIncorrTF2.getText();
        if (!pregunta.isEmpty() & !respuestaCorrecta.isEmpty() & !respuestaIncorrecta1.isEmpty() & !respuestaIncorrecta2.isEmpty()) {
            PreguntasGuardadasJL.setModel(listado);
            if (categoria.equals("Nueva categoría")) {
                String nuevaCategoria = NuevaCatTF.getText();
                if (nuevaCategoria != null) {
                    listado.addElement(pregunta + ", " + nuevaCategoria + ", " + respuestaCorrecta + ", " + respuestaIncorrecta1 + ", " + respuestaIncorrecta2);
                    PreguntaTF.setText("");
                    ResCorrTF.setText("");
                    ResIncorrTF1.setText("");
                    ResIncorrTF2.setText("");
                }
            } else {
                listado.addElement(pregunta + ", " + categoria + ", " + respuestaCorrecta + ", " + respuestaIncorrecta1 + ", " + respuestaIncorrecta2);
                PreguntaTF.setText("");
                ResCorrTF.setText("");
                ResIncorrTF1.setText("");
                ResIncorrTF2.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><h3>Campos vacíos no están permitidos.</h3></html>", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        try {
            int index = PreguntasGuardadasJL.getSelectedIndex();
            listado.remove(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "<html><h3>Selecciona una opción primero</h3></html>", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        if (PreguntasGuardadasJL.getModel().getSize() != 0) {
            String sql = "SELECT * FROM Preguntas";
            String texto;
            ArrayList<String> preguntas = new ArrayList<>();
            try {
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    preguntas.add(rs.getString(2));
                }
                sql = "INSERT INTO Preguntas (Pregunta, Categoria, RespuestaCorrecta, RespuestaIncorrecta1, RespuestaIncorrecta2) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement PS = con.prepareStatement(sql);
                for (int i = 0; i < PreguntasGuardadasJL.getModel().getSize(); i++) {
                    texto = PreguntasGuardadasJL.getModel().getElementAt(i);
                    String[] palabras = texto.split(", ");
                    String pregunta = palabras[0];
                    if (!preguntas.contains(pregunta)) {
                        PS.setString(1, pregunta);
                        preguntas.add(pregunta);
                    }
                    PS.setString(2, palabras[1]);
                    PS.setString(3, palabras[2]);
                    PS.setString(4, palabras[3]);
                    PS.setString(5, palabras[4]);
                    PS.executeUpdate();
                }
                PS.close();
                JOptionPane.showMessageDialog(null, "<html><h3Datos añadidos con éxito</h3></html>", "Añadir", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "<html><h3>Algo salió mal con la conexión a la base de datos.</h3></html>", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.err.println(ex.getMessage());
            }
            listado.removeAllElements();
        }
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            boolean status = true;
            VPrincipal vp = new VPrincipal(null, this.con, status);
            vp.setVisible(true);
            this.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void CategoriaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaCBActionPerformed
        NuevaCatLabel.setVisible(false);
        NuevaCatTF.setVisible(false);
        if (CategoriaCB.getSelectedItem().toString().equals("Nueva categoría")) {
            NuevaCatLabel.setVisible(true);
            NuevaCatTF.setVisible(true);
        }
    }//GEN-LAST:event_CategoriaCBActionPerformed

    private void txtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtButtonActionPerformed
        SubirPreguntasArchivo sp = new SubirPreguntasArchivo(this.con);
        sp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategoriaCB;
    private javax.swing.JLabel CategoriaLabel;
    private javax.swing.JLabel NuevaCatLabel;
    private javax.swing.JTextField NuevaCatTF;
    private javax.swing.JLabel PreguntaLabel;
    private javax.swing.JTextField PreguntaTF;
    private javax.swing.JList<String> PreguntasGuardadasJL;
    private javax.swing.JLabel ResCorrLabel;
    private javax.swing.JTextField ResCorrTF;
    private javax.swing.JLabel ResIncorrLabel1;
    private javax.swing.JLabel ResIncorrLabel2;
    private javax.swing.JTextField ResIncorrTF1;
    private javax.swing.JTextField ResIncorrTF2;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnSubir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton txtButton;
    // End of variables declaration//GEN-END:variables
}
