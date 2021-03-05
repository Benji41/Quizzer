package quizzer;

import java.awt.Checkbox;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objetos.Jugador;
import objetos.Pregunta;

public class VPersonalizacion extends javax.swing.JFrame {

    Connection con = null;
    int categoriasSeleccionadas = 0;
    int celdas;
    int tipo;
    int tiempoTurno;
    ArrayList<String> listaCatego = new ArrayList<>();
    ArrayList<Jugador> players = new ArrayList<>();
    ArrayList<String> categoriasJugar = new ArrayList<>();
    ArrayList<objetos.Pregunta> preguntas;
    Jugador j1;
    Jugador j2;
    Jugador j3;
    String queryQuestL = "SELECT Pregunta,Categoria,RespuestaCorrecta,RespuestaIncorrecta1,RespuestaIncorrecta2 from Pregunta;";
    DefaultListModel list = new DefaultListModel();

    public VPersonalizacion(ArrayList<String> categorias, ArrayList<Pregunta> preguntas, Connection con) {
        initComponents();
        this.con = con;
        this.preguntas = preguntas;
        Image icon = new ImageIcon(getClass().getResource("/resource/minilogo.png")).getImage();
        this.setIconImage(icon);
        setLocationRelativeTo(null);
        LabelNombrej1.setVisible(false);
        LabelNombrej2.setVisible(false);
        LabelNombrej3.setVisible(false);
        nombreJ1.setVisible(false);
        nombreJ2.setVisible(false);
        nombreJ3.setVisible(false);
        player1.setVisible(false);
        player2.setVisible(false);
        player3.setVisible(false);
        ListaCategorias.setModel(list);
        this.listaCatego = new ArrayList<>();
        CategoriaCB.addItem("---");
        for (String categoria : categorias) {
            System.out.println(categoria);
            CategoriaCB.addItem(categoria);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numJugadores = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        LabelNombrej2 = new javax.swing.JLabel();
        LabelNombrej1 = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        nombreJ3 = new javax.swing.JTextField();
        LabelNombrej3 = new javax.swing.JLabel();
        comboBoxCasillas = new javax.swing.JComboBox<>();
        RadioButton1j = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        comboBoxPreguntas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        RadioButton3j = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        RadioButton2j = new javax.swing.JRadioButton();
        nombreJ1 = new javax.swing.JTextField();
        nombreJ2 = new javax.swing.JTextField();
        player1 = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        player3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CategoriaCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaCategorias = new javax.swing.JList<>();
        BorrarBTN = new javax.swing.JButton();
        RegresarBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Personalizar partida");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 64, 53));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 84, 35), 10));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelNombrej2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelNombrej2.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombrej2.setText("Nombre del jugador 2:");
        jPanel1.add(LabelNombrej2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        LabelNombrej1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelNombrej1.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombrej1.setText("Nombre del jugador 1:");
        jPanel1.add(LabelNombrej1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        start.setBackground(new java.awt.Color(255, 255, 255));
        start.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        start.setText("Iniciar");
        start.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMouseClicked(evt);
            }
        });
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        jPanel1.add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 80, 30));

        nombreJ3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombreJ3.setText("Jugador3");
        jPanel1.add(nombreJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 100, -1));

        LabelNombrej3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelNombrej3.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombrej3.setText("Nombre del jugador 3:");
        jPanel1.add(LabelNombrej3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        comboBoxCasillas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboBoxCasillas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "9", "16", "49", "64" }));
        comboBoxCasillas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        comboBoxCasillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCasillasActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxCasillas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 91, -1));

        numJugadores.add(RadioButton1j);
        RadioButton1j.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        RadioButton1j.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton1j.setText("1 jugador");
        RadioButton1j.setPreferredSize(new java.awt.Dimension(128, 28));
        RadioButton1j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton1jActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton1j, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 120, -1));

        jLabel5.setFont(new java.awt.Font("Ink Free", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Personalice la partida");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        comboBoxPreguntas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboBoxPreguntas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "0:10", "0:20", "0:40", "1:00", "1:30", " " }));
        comboBoxPreguntas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.add(comboBoxPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 91, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Número de jugadores:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        numJugadores.add(RadioButton3j);
        RadioButton3j.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        RadioButton3j.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton3j.setText("3 jugadores");
        RadioButton3j.setPreferredSize(new java.awt.Dimension(128, 28));
        RadioButton3j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton3jActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton3j, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Seleccione el número de casillas:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccione 6 categorías:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Duración por pregunta:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, -1, -1));

        numJugadores.add(RadioButton2j);
        RadioButton2j.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        RadioButton2j.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton2j.setText("2 jugadores");
        RadioButton2j.setPreferredSize(new java.awt.Dimension(128, 28));
        RadioButton2j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton2jActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton2j, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        nombreJ1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombreJ1.setText("Jugador 1");
        nombreJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreJ1ActionPerformed(evt);
            }
        });
        jPanel1.add(nombreJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 100, -1));

        nombreJ2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombreJ2.setText("Jugador2");
        jPanel1.add(nombreJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 100, -1));

        player1.setBackground(new java.awt.Color(0, 0, 0));
        player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/pj1.png"))); // NOI18N
        player1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));
        player1.setOpaque(true);
        jPanel1.add(player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 122, -1, -1));

        player2.setBackground(new java.awt.Color(0, 0, 0));
        player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/pj2.png"))); // NOI18N
        player2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51), 2));
        player2.setOpaque(true);
        player2.setPreferredSize(new java.awt.Dimension(43, 36));
        jPanel1.add(player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 123, -1, -1));

        player3.setBackground(new java.awt.Color(0, 0, 0));
        player3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/pj3.png"))); // NOI18N
        player3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 2));
        player3.setOpaque(true);
        player3.setPreferredSize(new java.awt.Dimension(43, 40));
        jPanel1.add(player3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 122, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/book.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 80, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/book.png"))); // NOI18N
        jLabel7.setText("jLabel6");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 80, 60));

        CategoriaCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CategoriaCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CategoriaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaCBActionPerformed(evt);
            }
        });
        jPanel1.add(CategoriaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 150, -1));

        ListaCategorias.setBackground(new java.awt.Color(52, 64, 53));
        ListaCategorias.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
        ListaCategorias.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(ListaCategorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 180, 170));

        BorrarBTN.setBackground(new java.awt.Color(255, 255, 255));
        BorrarBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BorrarBTN.setText("Borrar");
        BorrarBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BorrarBTN.setPreferredSize(new java.awt.Dimension(65, 30));
        BorrarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarBTNActionPerformed(evt);
            }
        });
        jPanel1.add(BorrarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, -1, -1));

        RegresarBTN.setText("Regresar");
        RegresarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarBTNActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxCasillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCasillasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxCasillasActionPerformed

    private void RadioButton1jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton1jActionPerformed
        // TODO add your handling code here:
        players.clear();
        LabelNombrej1.setVisible(true);
        nombreJ1.setVisible(true);
        LabelNombrej2.setVisible(false);
        nombreJ2.setVisible(false);
        LabelNombrej3.setVisible(false);
        nombreJ3.setVisible(false);
        player1.setVisible(true);
        player2.setVisible(false);
        player3.setVisible(false);
        tipo = 0;
    }//GEN-LAST:event_RadioButton1jActionPerformed

    private void RadioButton2jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton2jActionPerformed
        // TODO add your handling code here:
        players.clear();
        LabelNombrej1.setVisible(true);
        nombreJ1.setVisible(true);
        LabelNombrej2.setVisible(true);
        nombreJ2.setVisible(true);
        LabelNombrej3.setVisible(false);
        nombreJ3.setVisible(false);
        player1.setVisible(true);
        player2.setVisible(true);
        player3.setVisible(false);
        tipo = 1;
    }//GEN-LAST:event_RadioButton2jActionPerformed

    private void RadioButton3jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton3jActionPerformed
        // TODO add your handling code here:
        players.clear();
        LabelNombrej1.setVisible(true);
        nombreJ1.setVisible(true);
        LabelNombrej2.setVisible(true);
        nombreJ2.setVisible(true);
        LabelNombrej3.setVisible(true);
        nombreJ3.setVisible(true);
        player1.setVisible(true);
        player2.setVisible(true);
        player3.setVisible(true);
        tipo = 1;
    }//GEN-LAST:event_RadioButton3jActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed

        switch (this.comboBoxPreguntas.getSelectedItem().toString()) {
            case "0:10":
                this.tiempoTurno = 10;
                break;
            case "0:40":
                this.tiempoTurno = 40;
                break;
            case "1:00":
                this.tiempoTurno = 60;
                break;
            case "1:30":
                this.tiempoTurno = 90;
                break;
            case "0:20":
                this.tiempoTurno = 20;
                break;
        }
        if (RadioButton1j.isSelected()) {
            String name = nombreJ1.getText();
            j1 = new Jugador(name, 0, 1);
            players.add(j1);
        }

        if (RadioButton2j.isSelected()) {
            String name1 = nombreJ1.getText();
            String name2 = nombreJ2.getText();
            j1 = new Jugador(name1, 0, 1);
            j2 = new Jugador(name2, 0, 2);
            players.add(j1);
            players.add(j2);
        }
        if (RadioButton3j.isSelected()) {
            String name1 = nombreJ1.getText();
            String name2 = nombreJ2.getText();
            String name3 = nombreJ3.getText();
            j1 = new Jugador(name1, 0, 1);
            j2 = new Jugador(name2, 0, 2);
            j3 = new Jugador(name3, 0, 3);
            players.add(j1);
            players.add(j2);
            players.add(j3);
        }
        String celdasN = comboBoxCasillas.getSelectedItem().toString();
        if (!celdasN.equals("---")) {
            this.celdas = Integer.parseInt(celdasN);
        }
        for (int i = 0; i < ListaCategorias.getModel().getSize(); i++) {
            this.listaCatego.add(ListaCategorias.getModel().getElementAt(i));
        }
        if (RadioButton1j.isSelected() & !nombreJ1.getText().isEmpty() & ListaCategorias.getModel().getSize() == 6 & !comboBoxCasillas.getSelectedItem().equals("---") & !comboBoxPreguntas.getSelectedItem().equals("---")) {
            try {
                VJugar vj = new VJugar(this.celdas, this.players, this.tipo, this.preguntas, this.listaCatego, this.con, this.tiempoTurno);
                vj.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (RadioButton2j.isSelected() & !nombreJ2.getText().isEmpty() & !nombreJ1.getText().isEmpty() & ListaCategorias.getModel().getSize() == 6 & !comboBoxCasillas.getSelectedItem().equals("---") & !comboBoxPreguntas.getSelectedItem().equals("---")) {
            try {
                VJugar vj = new VJugar(this.celdas, this.players, this.tipo, this.preguntas, this.listaCatego, this.con, this.tiempoTurno);
                vj.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (RadioButton3j.isSelected() & !nombreJ1.getText().isEmpty() & !nombreJ2.getText().isEmpty() & !nombreJ3.getText().isEmpty() & ListaCategorias.getModel().getSize() == 6 & !comboBoxCasillas.getSelectedItem().equals("---") & !comboBoxPreguntas.getSelectedItem().equals("---")) {
            try {
                VJugar vj = new VJugar(this.celdas, this.players, this.tipo, this.preguntas, this.listaCatego, this.con, this.tiempoTurno);
                vj.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Algo está vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_startActionPerformed

    private void nombreJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreJ1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreJ1ActionPerformed

    private void startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMouseClicked

    }//GEN-LAST:event_startMouseClicked

    private void CategoriaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaCBActionPerformed
        String seleccion = CategoriaCB.getSelectedItem().toString();
        if (!seleccion.equals("---")) {
            if (ListaCategorias.getModel().getSize() <= 5 & !list.contains(seleccion)) {
                list.addElement(seleccion);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede repetir o tener más de 6 categorías", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_CategoriaCBActionPerformed

    private void BorrarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarBTNActionPerformed
        int seleccion = ListaCategorias.getSelectedIndex();
        System.out.println(seleccion);
        if (seleccion != -1) {
            list.remove(seleccion);
        }
    }//GEN-LAST:event_BorrarBTNActionPerformed

    private void RegresarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarBTNActionPerformed
        VPrincipal VP = null;
        try {
            VP = new VPrincipal();
        } catch (InterruptedException ex) {
            Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPersonalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        VP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BorrarBTN;
    private javax.swing.JComboBox<String> CategoriaCB;
    private javax.swing.JLabel LabelNombrej1;
    private javax.swing.JLabel LabelNombrej2;
    private javax.swing.JLabel LabelNombrej3;
    private javax.swing.JList<String> ListaCategorias;
    private javax.swing.JRadioButton RadioButton1j;
    private javax.swing.JRadioButton RadioButton2j;
    private javax.swing.JRadioButton RadioButton3j;
    private javax.swing.JButton RegresarBTN;
    private javax.swing.JComboBox<String> comboBoxCasillas;
    private javax.swing.JComboBox<String> comboBoxPreguntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreJ1;
    private javax.swing.JTextField nombreJ2;
    private javax.swing.JTextField nombreJ3;
    private javax.swing.ButtonGroup numJugadores;
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel player3;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}
