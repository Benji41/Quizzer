/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//
// Esta clase permite gestionar la tabla y los eventos realizados sobre ella
// cada celda seria un objeto personalizable
// @author CHENAO
// 
//
public class GestionCeldas extends DefaultTableCellRenderer {

    private String tipo = "text";

    //se definen por defecto los tipos de datos a usar
    private Font normal = new Font("Verdana", Font.BOLD, 12);
    private Font bold = new Font("Verdana", Font.BOLD, 12);
    //etiqueta que almacenará el icono a mostrar
    private JLabel label = new JLabel();

    public GestionCeldas() {

    }

//
//constructor explicito con el tipo de dato que tendrá la celda
// @param tipo
//
    public GestionCeldas(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

//
//Este metodo controla toda la tabla, podemos obtener el valor que contiene
// definir que celda está seleccionada, la fila y columna al tener el foco en ella.
// 
// cada evento sobre la tabla invocará a este metodo
//
        //definimos colores por defecto
        Color colorFondo = null;
        Color colorFondoPorDefecto = new Color(192, 192, 192);
        Color colorFondoSeleccion = new Color(140, 140, 140);

        /*
         * Si la celda del evento es la seleccionada se asigna el fondo por defecto para la selección
         */
        if (selected) {
            this.setBackground(colorFondoPorDefecto);
        } else {
            //Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
            this.setBackground(Color.white);
        }

        /*
         * Se definen los tipos de datos que contendrán las celdas basado en la instancia que
         * se hace en la ventana de la tabla al momento de construirla
         */
        if (tipo.equals("pregunta")) {
            //si es tipo texto define el color de fondo del texto y de la celda así como la alineación
            if (focused) {
                colorFondo = colorFondoSeleccion;
            } else {
                colorFondo = colorFondoPorDefecto;
            }
            this.setHorizontalAlignment(JLabel.LEFT);
            this.setText((String) value);
//            this.setForeground((selected) ? new Color(255, 255, 255) : new Color(254, 207, 183));

            this.setBackground((selected) ? new Color(255, 255, 255) : new Color(255, 245, 234));
            this.setFont(normal);
            //this.setFont(bold);
            return this;
        }

        //si el tipo es icono entonces valida cual icono asignar a la etiqueta.
        //definie si el tipo de dato el numerico para personalizarlo
        if (tipo.equals("respuesta")) {
            if (focused) {
                colorFondo = colorFondoSeleccion;
            } else {
                colorFondo = colorFondoPorDefecto;
            }
            // System.out.println(value);
            this.setHorizontalAlignment(JLabel.LEFT);
            this.setText((String) value);
            this.setForeground((selected) ? new Color(255, 255, 255) : new Color(33, 141, 166));
//            this.setBackground((selected) ? colorFondo : Color.WHITE);
            this.setBackground((selected) ? new Color(255, 255, 255) : new Color(255, 245, 234));
            this.setFont(bold);
            return this;
        }

        return this;
    }
}
