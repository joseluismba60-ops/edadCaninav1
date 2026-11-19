package es.davante.edadcanina.modelo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import es.davante.edadcanina.BR;

public class EdadCaninaModelo extends BaseObservable {
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    @Bindable
    public String getEdadTexto() {
        return edadTexto;
    }

    public void setEdadTexto(String edadTexto) {
        if (edadTexto == null) edadTexto = "";
        if (!this.edadTexto.equals(edadTexto)) {
            this.edadTexto = edadTexto;
            notifyPropertyChanged(BR.edadTexto);
            setValidacion(Validacion.SIN_CALCULAR);
            setEdadHumana(0);
        }
    }

    @Bindable
    public int getEdadHumana() {
        return edadHumana;
    }

    private void setEdadHumana(int edadHumana) {
        if (this.edadHumana != edadHumana) {
            this.edadHumana = edadHumana;
            notifyPropertyChanged(BR.edadHumana);
        }
    }

    @Bindable
    public Validacion getValidacion() {
        return validacion;
    }

    private void setValidacion(Validacion validacion) {
        if (this.validacion != validacion) {
            this.validacion = validacion;
            notifyPropertyChanged(BR.validacion);
        }
    }

    public Validacion calcular() {
        if (edadTexto.trim().isEmpty()) {
            setValidacion(Validacion.ERROR_VACIO);
            setEdadHumana(0);
            return validacion;
        }

        try {
            int edad = Integer.parseInt(edadTexto.trim());
            if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
                setValidacion(Validacion.ERROR_RANGO);
                setEdadHumana(0);
            } else {
                setEdadHumana(edad * FACTOR_CANINO);
                setValidacion(Validacion.OK);
            }
        } catch (NumberFormatException e) {
            setValidacion(Validacion.ERROR_NO_NUMERICO);
            setEdadHumana(0);
        }
        return validacion;
    }

    public void limpiar() {
        setEdadTexto("");
        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
    }
}