package quizzer;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import objetos.GestionCeldas;

public class Administrar extends javax.swing.JFrame {

    Connection con2;
    DefaultTableModel modelo;
    JTableHeader th;

    public Administrar(Connection con) {
        initComponents();
        custom();
        customJOptionPane();
        custom(th);
        this.con2 = con;
        this.setLocationRelativeTo(null);
        DefaultTableModel DTM = new DefaultTableModel();
        String sql = "SELECT * FROM Pregunta";

        try {
            Statement ps = con2.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("IdPregunta");
                String pregunta = rs.getString("Pregunta");
                String respuestaincorrecta1 = rs.getString("RespuestaIncorrecta1");
                String respuestaincorrecta2 = rs.getString("RespuestaIncorrecta2");
                String respuestacorrecta = rs.getString("RespuestaCorrecta");
                String categoria = rs.getString("Categoria");
                DTM = (DefaultTableModel) TablaPreguntas.getModel();
                String tbData[] = {id + "", pregunta, respuestacorrecta, respuestaincorrecta1, respuestaincorrecta2, categoria};
                DTM.addRow(tbData);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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

    void custom(JTableHeader th) {
        TablaPreguntas.getColumnModel().getColumn(0).setHeaderRenderer(new Administrar.MyRenderer(new Color(140, 84, 35), Color.WHITE));
        TablaPreguntas.getColumnModel().getColumn(1).setHeaderRenderer(new Administrar.MyRenderer(new Color(140, 84, 35), Color.WHITE));
        TablaPreguntas.getColumnModel().getColumn(2).setHeaderRenderer(new Administrar.MyRenderer(new Color(140, 84, 35), Color.WHITE));
        TablaPreguntas.getColumnModel().getColumn(3).setHeaderRenderer(new Administrar.MyRenderer(new Color(140, 84, 35), Color.WHITE));
        TablaPreguntas.getColumnModel().getColumn(4).setHeaderRenderer(new Administrar.MyRenderer(new Color(140, 84, 35), Color.WHITE));
        TablaPreguntas.getColumnModel().getColumn(5).setHeaderRenderer(new Administrar.MyRenderer(new Color(140, 84, 35), Color.WHITE));

        TablaPreguntas.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas("respuesta"));
        TablaPreguntas.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldas("pregunta"));
        TablaPreguntas.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldas("respuesta"));
        TablaPreguntas.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldas("pregunta"));
        TablaPreguntas.getColumnModel().getColumn(4).setCellRenderer(new GestionCeldas("pregunta"));
        TablaPreguntas.getColumnModel().getColumn(5).setCellRenderer(new GestionCeldas("pregunta"));
        TablaPreguntas.setRowHeight(30);
        TablaPreguntas.getColumnModel().getColumn(1).setPreferredWidth(50);
        TablaPreguntas.getColumnModel().getColumn(1).setPreferredWidth(250);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        AddPreguntasButton = new javax.swing.JButton();
        BorrarPreguntasButton = new javax.swing.JButton();
        ActualizarPreguntasButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPreguntas = new javax.swing.JTable();
        RegresarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("¿Qué quiere hacer?");

        AddPreguntasButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPreguntasButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AddPreguntasButton.setForeground(new java.awt.Color(0, 0, 0));
        AddPreguntasButton.setText("Añadir preguntas");
        AddPreguntasButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        AddPreguntasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPreguntasButtonActionPerformed(evt);
            }
        });

        BorrarPreguntasButton.setBackground(new java.awt.Color(255, 255, 255));
        BorrarPreguntasButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BorrarPreguntasButton.setForeground(new java.awt.Color(0, 0, 0));
        BorrarPreguntasButton.setText("Borrar pregunta seleccionada");
        BorrarPreguntasButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BorrarPreguntasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarPreguntasButtonActionPerformed(evt);
            }
        });

        ActualizarPreguntasButton.setBackground(new java.awt.Color(255, 255, 255));
        ActualizarPreguntasButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ActualizarPreguntasButton.setForeground(new java.awt.Color(0, 0, 0));
        ActualizarPreguntasButton.setText("Actualizar preguntas");
        ActualizarPreguntasButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ActualizarPreguntasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarPreguntasButtonActionPerformed(evt);
            }
        });

        TablaPreguntas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdPregunta", "Pregunta", "Respuesta Correcta", "Respuesta Incorrecta 1", "Respuesta Incorrecta 2", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaPreguntas);
        if (TablaPreguntas.getColumnModel().getColumnCount() > 0) {
            TablaPreguntas.getColumnModel().getColumn(0).setResizable(false);
            TablaPreguntas.getColumnModel().getColumn(1).setResizable(false);
            TablaPreguntas.getColumnModel().getColumn(2).setResizable(false);
            TablaPreguntas.getColumnModel().getColumn(3).setResizable(false);
            TablaPreguntas.getColumnModel().getColumn(4).setResizable(false);
            TablaPreguntas.getColumnModel().getColumn(5).setResizable(false);
        }

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BorrarPreguntasButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(ActualizarPreguntasButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddPreguntasButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RegresarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addComponent(AddPreguntasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(ActualizarPreguntasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(BorrarPreguntasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(RegresarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddPreguntasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPreguntasButtonActionPerformed
        AñadirPreguntasLocales APL = new AñadirPreguntasLocales(con2);
        APL.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AddPreguntasButtonActionPerformed

    private void ActualizarPreguntasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarPreguntasButtonActionPerformed
        int row = TablaPreguntas.getSelectedRow();
        String value = TablaPreguntas.getModel().getValueAt(row, 0).toString();
        int idSelected = Integer.parseInt(value);
        System.out.println("pregunta señalada " + idSelected);
        ActualizarPreguntasLocales APL = new ActualizarPreguntasLocales(idSelected, con2);
        APL.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ActualizarPreguntasButtonActionPerformed

    private void BorrarPreguntasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarPreguntasButtonActionPerformed
        String botones[] = {"Si", "No"};
        int res = JOptionPane.showOptionDialog(this, "¿Seguro que quieres borrar?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
        if (res == 0) {
            int row = TablaPreguntas.getSelectedRow();
            String value = TablaPreguntas.getModel().getValueAt(row, 0).toString();
            int idSelected = Integer.parseInt(value);
            String sql = "DELETE FROM Pregunta WHERE IdPregunta = ?";
            try {
                PreparedStatement PS = con2.prepareStatement(sql);
                con2.setAutoCommit(true);
                PS.setInt(1, idSelected);
                PS.executeUpdate();
                PS.close();
                Administrar ad = new Administrar(con2);
                ad.setVisible(true);
                this.dispose();

            } catch (SQLException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BorrarPreguntasButtonActionPerformed

    private void RegresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarButtonActionPerformed
        if (con2 != null) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = true;
                VPrincipal vp = new VPrincipal(this.con2, null, status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                boolean status = true;
                VPrincipal vp = new VPrincipal(status);
                vp.setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_RegresarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualizarPreguntasButton;
    private javax.swing.JButton AddPreguntasButton;
    private javax.swing.JButton BorrarPreguntasButton;
    private javax.swing.JButton RegresarButton;
    private javax.swing.JTable TablaPreguntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
public class MyRenderer extends DefaultTableCellRenderer {

        Color background;
        Color foreground;
        Font font;

        public MyRenderer(Color background, Color foreground) {
            super();
            this.background = background;
            this.foreground = foreground;
            this.font = new Font("Tahoma", 1, 14);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            comp.setBackground(background);
            comp.setForeground(foreground);
            comp.setFont(font);
            return comp;
        }
    }
}
