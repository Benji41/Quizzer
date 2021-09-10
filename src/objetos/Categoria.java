/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;

/**
 *
 * @author ama
 */
public class Categoria {
    ArrayList<objetos.Pregunta> preguntas;
    int contador;

    public Categoria(ArrayList<objetos.Pregunta> preguntas, int contador) {
        this.preguntas = preguntas;
        this.contador = contador;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
}
