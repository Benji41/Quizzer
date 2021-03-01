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

        PreguntaLabel = new javax.swing.JLabel();
        ResCorrTF = new javax.swing.JTextField();
        CategoriaLabel = new javax.swing.JLabel();
        CategoriaCB = new javax.swing.JComboBox<>();
        ResCorrLabel = new javax.swing.JLabel();
        PreguntaTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        PreguntasGuardadasJL = new javax.swing.JList<>();
        ResIncorrLabel1 = new javax.swing.JLabel();
        ResIncorrTF1 = new javax.swing.JTextField();
        ResIncorrLabel2 = new javax.swing.JLabel();
        ResIncorrTF2 = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PreguntaLabel.setText("Pregunta");

        CategoriaLabel.setText("Categoría");

        ResCorrLabel.setText("Respuesta Correcta");

        jScrollPane1.setViewportView(PreguntasGuardadasJL);

        ResIncorrLabel1.setText("Respuesta Incorrecta 1");

        ResIncorrLabel2.setText("Respuesta Incorrecta 2");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSubir.setText("Terminar y subir");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PreguntaLabel)
                            .addComponent(PreguntaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResCorrLabel)
                            .addComponent(ResCorrTF, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResIncorrLabel1)
                            .addComponent(ResIncorrTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResIncorrLabel2)
                            .addComponent(ResIncorrTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBorrar)
                                    .addComponent(btnSubir))
                                .addGap(0, 148, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(CategoriaLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CategoriaCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreguntaLabel)
                    .addComponent(CategoriaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CategoriaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreguntaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ResCorrLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ResCorrTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(ResIncorrLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(ResIncorrTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(ResIncorrLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ResIncorrTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSubir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addContainerGap())
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
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
