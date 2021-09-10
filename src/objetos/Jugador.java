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

    public Jugador(String apodo, int score, int numero, int racha) {
        this.apodo = apodo;
        this.score = score;
        this.numero = numero;
        this.racha = racha;
    }

    public int getRacha() {
        return racha;
    }

    public void setRacha(int racha) {
        this.racha = racha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void responde(int respuesta, Celda imagen) {

    }
    private String apodo;
    private int score;
    private int numero;
    private int racha;
}
