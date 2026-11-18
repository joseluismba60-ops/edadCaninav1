package es.davante.edadcanina.modelo;

public class EdadCaninaModelo {
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    protected String edadTexto = "";
    protected int edadHumana = 0;
    protected Validacion validacion = Validacion.SIN_CALCULAR;

    public String getEdadTexto() {
        return edadTexto;
    }

    public void setEdadTexto(String edadTexto) {
        if (edadTexto == null) edadTexto = "";
        if (!this.edadTexto.equals(edadTexto)) {
            this.edadTexto = edadTexto;
            // Regla de negocio: si el texto cambia, el resultado anterior caduca
            this.validacion = Validacion.SIN_CALCULAR;
            this.edadHumana = 0;
        }
    }

    public int getEdadHumana() {
        return edadHumana;
    }

    public Validacion getValidacion() {
        return validacion;
    }

    public Validacion calcular() {
        if (edadTexto.trim().isEmpty()) {
            this.validacion = Validacion.ERROR_VACIO;
            this.edadHumana = 0;
            return validacion;
        }

        try {
            int edadPerro = Integer.parseInt(edadTexto.trim());
            if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
                this.validacion = Validacion.ERROR_RANGO;
                this.edadHumana = 0;
            } else {
                this.edadHumana = edadPerro * FACTOR_CANINO;
                this.validacion = Validacion.OK;
            }
        } catch (NumberFormatException e) {
            this.validacion = Validacion.ERROR_NO_NUMERICO;
            this.edadHumana = 0;
        }

        return validacion;
    }

    public void limpiar() {
        this.edadTexto = "";
        this.edadHumana = 0;
        this.validacion = Validacion.SIN_CALCULAR;
    }
}