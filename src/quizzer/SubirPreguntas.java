package quizzer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class SubirPreguntas extends javax.swing.JFrame {
    Connection con;
    DefaultListModel listado = new DefaultListModel();
    public SubirPreguntas(Connection con) {
        initComponents();
        this.con = con;
        String sql = "SELECT DISTINCT [Categoria] FROM [Quizzer].[dbo].[Preguntas]";
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CategoriaCB.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(52, 64, 53));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 84, 35), 10));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
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
        btnSubir.setForeground(new java.awt.Color(0, 0, 0));
        btnSubir.setText("Terminar y subir");
        btnSubir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnSubir.setPreferredSize(new java.awt.Dimension(125, 30));
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        CategoriaCB.setBackground(new java.awt.Color(255, 255, 255));
        CategoriaCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CategoriaCB.setForeground(new java.awt.Color(0, 0, 0));
        CategoriaCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        btnBorrar.setBackground(new java.awt.Color(255, 255, 255));
        btnBorrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrar.setText("Borrar");
        btnBorrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnBorrar.setPreferredSize(new java.awt.Dimension(100, 30));
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(PreguntasGuardadasJL);

        ResIncorrLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResIncorrLabel2.setForeground(new java.awt.Color(255, 255, 255));
        ResIncorrLabel2.setText("Respuesta Incorrecta 2");

        PreguntaLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PreguntaLabel.setForeground(new java.awt.Color(255, 255, 255));
        PreguntaLabel.setText("Pregunta");

        ResCorrLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResCorrLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResCorrLabel.setText("Respuesta Correcta");

        btnAtras.setBackground(new java.awt.Color(255, 255, 255));
        btnAtras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(0, 0, 0));
        btnAtras.setText("Volver");
        btnAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnAtras.setPreferredSize(new java.awt.Dimension(70, 30));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        ResIncorrLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResIncorrLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ResIncorrLabel1.setText("Respuesta Incorrecta 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(CategoriaLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CategoriaCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(138, 138, 138))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PreguntaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PreguntaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(ResCorrLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ResCorrTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(ResIncorrLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(ResIncorrTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(ResIncorrLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ResIncorrTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CategoriaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CategoriaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
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
            listado.addElement(pregunta + ", " + categoria + ", " + respuestaCorrecta + ", " + respuestaIncorrecta1 + ", " + respuestaIncorrecta2);
        } else {
            JOptionPane.showMessageDialog(null, "Campos vacíos no están permitidos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        PreguntaTF.setText("");
        ResCorrTF.setText("");
        ResIncorrTF1.setText("");
        ResIncorrTF2.setText("");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        try {
            int index = PreguntasGuardadasJL.getSelectedIndex();
            listado.remove(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecciona una opción primero", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
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
                PS.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo salió mal con la conexión a la base de datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        VPrincipal vp = null;
        try {
            vp = new VPrincipal();
        } catch (InterruptedException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubirPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
        vp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategoriaCB;
    private javax.swing.JLabel CategoriaLabel;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
