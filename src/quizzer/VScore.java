/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizzer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import objetos.GestionCeldas;
import quizzer.VPrincipal;

/**
 *
 * @author denia
 */
public class VScore extends javax.swing.JFrame {

    Connection con = null;
    DefaultTableModel tblModel1 = new DefaultTableModel();
    DefaultTableModel tblModel2 = new DefaultTableModel();
    public VScore(Connection con) {

        initComponents();
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        this.con = con;
        jBMulti.setVisible(false);
        jBPlayer.setVisible(false);
        tablaMulti.setVisible(false);
        ShowtableSingle();
    }
    void custom1() {
        tablaMulti.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaMulti.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaMulti.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaMulti.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas("respuesta"));
        tablaMulti.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldas("pregunta"));
        tablaMulti.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldas("pregunta"));

        tablaMulti.setRowHeight(30);

    }

    void custom2() {
        tablaSingle.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaSingle.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaSingle.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaSingle.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(new Color (140,84,35),Color.WHITE));
        tablaSingle.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas("respuesta"));
        tablaSingle.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldas("pregunta"));
        tablaSingle.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldas("pregunta"));
        tablaSingle.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldas("pregunta"));
        tablaSingle.getColumnModel().getColumn(0).setPreferredWidth(55);
        tablaSingle.setRowHeight(30);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSingle = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMulti = new javax.swing.JTable();
        jBack = new javax.swing.JButton();
        jBPlayer = new javax.swing.JButton();
        jBMulti = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scores");
        setBackground(new java.awt.Color(52, 64, 53));
        setMinimumSize(new java.awt.Dimension(475, 420));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 64, 53));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 84, 35), 10));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label.setFont(new java.awt.Font("Ink Free", 1, 30)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("Puntuaci??n: Un jugador");
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 500, 49));

        tablaSingle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaSingle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Resultado", "Tiempo", "Puntuaci??n", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaSingle);
        if (tablaSingle.getColumnModel().getColumnCount() > 0) {
            tablaSingle.getColumnModel().getColumn(0).setResizable(false);
            tablaSingle.getColumnModel().getColumn(1).setResizable(false);
            tablaSingle.getColumnModel().getColumn(2).setResizable(false);
            tablaSingle.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 500, 390));

        tablaMulti.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaMulti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ganador", "Puntuaci??n", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaMulti);
        if (tablaMulti.getColumnModel().getColumnCount() > 0) {
            tablaMulti.getColumnModel().getColumn(0).setResizable(false);
            tablaMulti.getColumnModel().getColumn(1).setResizable(false);
            tablaMulti.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 500, 390));

        jBack.setBackground(new java.awt.Color(255, 255, 255));
        jBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBack.setForeground(new java.awt.Color(0, 0, 0));
        jBack.setText("Volver");
        jBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jBack.setPreferredSize(new java.awt.Dimension(65, 30));
        jBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackActionPerformed(evt);
            }
        });
        jPanel1.add(jBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, -1, -1));

        jBPlayer.setBackground(new java.awt.Color(255, 255, 255));
        jBPlayer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBPlayer.setForeground(new java.awt.Color(0, 0, 0));
        jBPlayer.setText("1 Jugador");
        jBPlayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jBPlayer.setMinimumSize(new java.awt.Dimension(85, 23));
        jBPlayer.setPreferredSize(new java.awt.Dimension(85, 30));
        jBPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPlayerActionPerformed(evt);
            }
        });
        jPanel1.add(jBPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, -1, -1));

        jBMulti.setBackground(new java.awt.Color(255, 255, 255));
        jBMulti.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBMulti.setForeground(new java.awt.Color(0, 0, 0));
        jBMulti.setText("Mas Jugadores");
        jBMulti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jBMulti.setPreferredSize(new java.awt.Dimension(115, 30));
        jBMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMultiActionPerformed(evt);
            }
        });
        jPanel1.add(jBMulti, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, 60, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/book.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 80, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackActionPerformed
        try {
            new VPrincipal().setVisible(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(VScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_jBackActionPerformed

    private void jBPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPlayerActionPerformed
        // TODO add your handling code here:
        CleanTables();
        ShowtableSingle();

    }//GEN-LAST:event_jBPlayerActionPerformed

    private void jBMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMultiActionPerformed
        // TODO add your handling code here:

        label.setText("Puntuaci??n: Multijugador");
        tablaMulti.setVisible(true);
        jScrollPane3.setVisible(true);
        tablaSingle.setVisible(false);
        jScrollPane2.setVisible(false);

        CleanTables();
        custom1();

        //Conexion  
        Statement st = null;

        try {
            con.setAutoCommit(false);
            st = con.createStatement();
            String sql = "SELECT * FROM ScoreMulti;";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String winner = rs.getString("Winner");
                String scorem = String.valueOf(rs.getInt("ScoreM"));
                String datem = rs.getString("Date");

                String tbData[] = {winner, scorem, datem};
                tblModel2 = (DefaultTableModel) tablaMulti.getModel();
                tblModel2.addRow(tbData);

            }
            rs.close();
            st.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }//GEN-LAST:event_jBMultiActionPerformed

    public void ShowtableSingle() {
        custom2();
        label.setText("Puntuaci??n: Un Jugador");
        tablaMulti.setVisible(false);
        tablaSingle.setVisible(true);
        jScrollPane2.setVisible(true);
        jScrollPane3.setVisible(false);

        //Conexion
        Statement st = null;

        try {
            con.setAutoCommit(false);

            st = con.createStatement();
            String sql = "SELECT * FROM ScoreSingle;";
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                String status = rs.getString("Outcome");
                String duration = rs.getString("Duration");
                String score = String.valueOf(rs.getInt("Score"));
                String date = rs.getString("Date");
                tblModel1 = (DefaultTableModel) tablaSingle.getModel();
                String tbData[] = {status, duration, score, date};
                tblModel1.addRow(tbData);

            }
            rs.close();
            st.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        custom2();

    }

    public void CleanTables() {
        DefaultTableModel tbS = (DefaultTableModel) tablaSingle.getModel();
        int a1 = tbS.getRowCount() - 1;
        for (int i = a1; i >= 0; i--) {
            tbS.removeRow(tbS.getRowCount() - 1);
        }

        DefaultTableModel tbM = (DefaultTableModel) tablaMulti.getModel();
        int a2 = tbM.getRowCount() - 1;
        for (int i = a2; i >= 0; i--) {
            tbM.removeRow(tbM.getRowCount() - 1);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBMulti;
    private javax.swing.JButton jBPlayer;
    private javax.swing.JButton jBack;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label;
    private javax.swing.JTable tablaMulti;
    private javax.swing.JTable tablaSingle;
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
