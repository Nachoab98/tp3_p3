package clase;

import java.util.Date;

public class RelojAcelerado {
    private long fechaInicio;
    private int aceleracion;

    public RelojAcelerado(int aceleracion) {
        this.fechaInicio = System.currentTimeMillis();
        this.aceleracion = aceleracion;
    }

    public Date getTiempoAcelerado() {
        long ahora = System.currentTimeMillis();
        long diferencia = ahora - fechaInicio;
        long tiempoAcelerado = fechaInicio + (diferencia * aceleracion);
        return new Date(tiempoAcelerado);
    }
}
