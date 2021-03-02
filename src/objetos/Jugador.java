/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author Benjimon41
 */
public class Jugador {

    public Jugador(String apodo, double score) {
        this.apodo = apodo;
        this.score = score;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void responde(int respuesta,Celda imagen ){
        
    }
    private String apodo;
    private double score;
}
