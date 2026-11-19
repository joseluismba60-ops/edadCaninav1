package es.davante.edadcanina.vista;

import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public final class AdaptadoresVista {

    private AdaptadoresVista() {}

    @BindingAdapter("errorValidacion")
    public static void mostrarErrorValidacion(EditText campo, Validacion validacion) {
        if (validacion == null || validacion == Validacion.SIN_CALCULAR || validacion == Validacion.OK) {
            campo.setError(null);
            return;
        }

        String mensaje;
        switch (validacion) {
            case ERROR_VACIO:
                mensaje = campo.getContext().getString(R.string.error_vacio);
                break;
            case ERROR_NO_NUMERICO:
                mensaje = campo.getContext().getString(R.string.error_no_numero);
                break;
            case ERROR_RANGO:
                mensaje = campo.getContext().getString(R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA);
                break;
            default:
                mensaje = null;
                break;
        }

        campo.setError(mensaje);
        if (mensaje != null) {
            campo.requestFocus();
        }
    }
}