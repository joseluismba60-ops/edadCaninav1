package es.davante.edadcanina.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EdadCaninaModeloTest {

    private EdadCaninaModelo modelo;

    @Before
    public void setUp() {
        modelo = new EdadCaninaModelo();
    }

    @Test
    public void edadValida_calculaCorrectamente() {
        modelo.setEdadTexto("6");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
    }

    @Test
    public void entradaVacia_retornaErrorVacio() {
        modelo.setEdadTexto("");
        assertEquals(Validacion.ERROR_VACIO, modelo.calcular());
    }

    @Test
    public void entradaNoNumerica_retornaErrorNoNumerico() {
        modelo.setEdadTexto("abc");
        assertEquals(Validacion.ERROR_NO_NUMERICO, modelo.calcular());
    }

    @Test
    public void edadBajoRango_retornaErrorRango() {
        modelo.setEdadTexto("0");
        assertEquals(Validacion.ERROR_RANGO, modelo.calcular());
    }

    @Test
    public void edadSobreRango_retornaErrorRango() {
        modelo.setEdadTexto("31");
        assertEquals(Validacion.ERROR_RANGO, modelo.calcular());
    }

    @Test
    public void cambiarTexto_caducaResultadoPrevio() {
        modelo.setEdadTexto("6");
        modelo.calcular();
        modelo.setEdadTexto("7");
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValidacion());
    }
}