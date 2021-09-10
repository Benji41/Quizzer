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
public class Pregunta {

    private String pregunta = "nada1";
    private String categoria = "nada2";
    private String respuestaCorrecta = "nada3";
    private String respuestaIncorrecta1 = "nada4";
    private String respuestaIncorrecta2 = "nada5";
    private boolean usada = false;

    public Pregunta(String q, String c, String ans, String wr1, String wr2) {
        this.pregunta = q;
        this.categoria = c;
        this.respuestaCorrecta = ans;
        this.respuestaIncorrecta1 = wr1;
        this.respuestaIncorrecta2 = wr2;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public String getRespuestaIncorrecta1() {
        return respuestaIncorrecta1;
    }

    public String getRespuestaIncorrecta2() {
        return respuestaIncorrecta2;
    }

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }

}
