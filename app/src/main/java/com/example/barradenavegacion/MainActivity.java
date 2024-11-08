package com.example.barradenavegacion;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Listener para detectar clics en la barra de navegación
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Usamos if-else en lugar de switch debido a la limitación de constantes en los Resource IDs
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = new CartFragment();
            } else if (itemId == R.id.nav_camera) {
                selectedFragment = new CameraFragment();
            } else if (itemId == R.id.nav_dashboard) {
                selectedFragment = new MeterFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new HistorialFragment();
            }

            // Reemplazar el contenedor de fragmentos con el fragmento seleccionado
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });

        // Establecer el fragmento de inicio (Inicio) cuando se inicie la aplicación
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CartFragment())
                    .commit();
        }
    }
}