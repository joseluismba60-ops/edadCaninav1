package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Paso 6: Constantes en lugar de números mágicos
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    // Paso 7: Atributos globales de clase para las vistas
    private EditText ageEdit;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Paso 7: Asignación a los atributos de clase
        ageEdit = findViewById(R.id.age_input);
        resultText = findViewById(R.id.result_text);
        Button button1 = findViewById(R.id.btn_calc);

        // Paso 8: Listener en una línea hacia la función de cálculo
        button1.setOnClickListener(view -> calcularEdad());
    }

    // Paso 10: Lógica completa de validación
    private void calcularEdad() {
        String input = ageEdit.getText().toString().trim();

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
        ageEdit.setError(null);
        int edadHumana = age * FACTOR_CANINO;
        resultText.setText(getString(R.string.tu_edad_format, edadHumana));
    }

    // Paso 9: Método para mostrar el error y gestionar el foco
    private void mostrarError(String mensaje) {
        ageEdit.setError(mensaje);
        ageEdit.requestFocus();
        resultText.setText("");
    }
}