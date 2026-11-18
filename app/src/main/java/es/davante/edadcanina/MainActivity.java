package es.davante.edadcanina;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Constantes de negocio de la v2
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    // Atributo binding en lugar de vistas sueltas (no más findViewById)
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicialización mediante DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Acceso directo al botón desde el binding
        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }

    private void calcularEdad() {
        String input = binding.ageInput.getText().toString().trim();

        // 1. Validar si está vacío
        if (input.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        // 2. Controlar conversión no numérica mediante try/catch
        int age;
        try {
            age = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        // 3. Validar rango permitido (1 a 30)
        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            String mensajeRango = getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA);
            mostrarError(mensajeRango);
            return;
        }

        // 4. Salida en caso de éxito
        binding.ageInput.setError(null);
        int edadHumana = age * FACTOR_CANINO;
        binding.resultText.setText(getString(R.string.tu_edad_format, edadHumana));
    }

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }
}