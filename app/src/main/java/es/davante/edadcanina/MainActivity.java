package es.davante.edadcanina;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EdadCaninaModelo modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instanciar el modelo puro
        modelo = new EdadCaninaModelo();

        // Conectar el layout con DataBinding y pasarle el modelo
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModelo(modelo);

        binding.btnCalc.setOnClickListener(v -> {
            modelo.calcular();

            // Gestion de errores de entrada en la vista hasta aplicar el @BindingAdapter en el Paso 5
            Validacion vRes = modelo.getValidacion();
            if (vRes == Validacion.ERROR_VACIO) {
                binding.ageInput.setError(getString(R.string.error_vacio));
                binding.ageInput.requestFocus();
            } else if (vRes == Validacion.ERROR_NO_NUMERICO) {
                binding.ageInput.setError(getString(R.string.error_no_numero));
                binding.ageInput.requestFocus();
            } else if (vRes == Validacion.ERROR_RANGO) {
                binding.ageInput.setError(getString(R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA));
                binding.ageInput.requestFocus();
            } else {
                binding.ageInput.setError(null);
            }
        });
    }
}