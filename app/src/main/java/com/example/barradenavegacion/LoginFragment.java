package com.example.barradenavegacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import mo.login.ApiService;
import mo.login.ConsultaLogin;
import mo.login.ResponseModel;
import mo.login.RetrofitClient;
import mo.login.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;


//package com.example.uilogin;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;

import com.example.barradenavegacion.R;

public class LoginFragment extends Fragment {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(15, 15, 15, 15);
        layout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background_color));

        ImageView imageView = new ImageView(requireContext());
        imageView.setImageResource(R.drawable.logo_savar);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                270
        );
        imageView.setLayoutParams(imageParams);
        layout.addView(imageView);

        View spacer1 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                75
        );
        spacer1.setLayoutParams(spacerParams1);
        layout.addView(spacer1);

        usernameEditText = new EditText(requireContext());
        usernameEditText.setHint("Usuario");
        layout.addView(usernameEditText);

        View spacer2 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer2.setLayoutParams(spacerParams2);
        layout.addView(spacer2);

        passwordEditText = new EditText(requireContext());
        passwordEditText.setHint("Contraseña");
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(passwordEditText);

        View spacer3 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer3.setLayoutParams(spacerParams3);
        layout.addView(spacer3);

        Button loginButton = new Button(requireContext());
        loginButton.setText("Iniciar Sesión");
        loginButton.setBackgroundColor(Color.parseColor("#0B4F6C"));
        loginButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        loginButton.setPadding(10, 10, 10, 10);
        loginButton.setTextSize(18);
        loginButton.setClickable(true);
        loginButton.setFocusable(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(requireActivity(), MainActivity.class);
//                startActivity(intent);
//                requireActivity().finish();
                loginUser();
            }
        });
        layout.addView(loginButton);

        scrollView.addView(layout);
        return scrollView;
    }

    private void loginUser() {
        // Obtiene los datos ingresados por el usuario
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Verificar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear instancia de Retrofit y ApiService
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Crear objeto ConsultaLogin con las credenciales
        ConsultaLogin loginRequest = new ConsultaLogin(username, password);

        // Hacer la solicitud de inicio de sesión
        Call<ResponseModel> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Manejar la respuesta de inicio de sesión exitosa
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();
                    User user = response.body().getUser();
                    Toast.makeText(requireContext(), "Bienvenido " + user.getNombre(), Toast.LENGTH_SHORT).show();

                    // Llamada a la API para verificar las credenciales
                    // Si la respuesta es exitosa:
                    SharedPreferences preferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn", true); // Establece que el usuario está logueado
                    editor.putString("username", username); // Guardar el nombre de usuario (opcional)
                    editor.apply();

                    // Redirigir a MainActivity después de iniciar sesión correctamente
                    Intent intent = new Intent(requireActivity(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().finish(); // Cerrar LoginActivity para que no puedan regresar
                } else {
                    Toast.makeText(requireContext(), "Inicio de sesión fallido: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Manejar fallo en la llamada (por ejemplo, error de red)
                Toast.makeText(requireContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
