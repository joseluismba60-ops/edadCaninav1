package es.davante.edadcanina.controlador;

import es.davante.edadcanina.modelo.EdadCaninaModelo;

public class EdadCaninaControlador {

    private final EdadCaninaModelo modelo;

    public EdadCaninaControlador(EdadCaninaModelo modelo) {
        this.modelo = modelo;
    }

    public void onCalcular() {
        if (modelo != null) {
            modelo.calcular();
        }
    }

    public void onLimpiar() {
        if (modelo != null) {
            modelo.limpiar();
        }
    }
}