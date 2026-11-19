package es.davante.edadcanina;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EdadCaninaModelo modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modelo = new EdadCaninaModelo();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModelo(modelo);

        binding.btnCalc.setOnClickListener(v -> modelo.calcular());
    }
}