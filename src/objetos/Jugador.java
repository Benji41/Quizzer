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

    public Jugador(String apodo, double score, float posicionX, float posicionY) {
        this.apodo = apodo;
        this.score = score;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
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

    public float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(float posicionX) {
        this.posicionX = posicionX;
    }

    public float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(float posicionY) {
        this.posicionY = posicionY;
    }
    
    public void responde(int respuesta,Celda imagen ){
        
    }
    private String apodo;
    private double score;
    private float posicionX;
    private float posicionY;
}
