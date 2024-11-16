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
import android.widget.TextView;
import android.widget.Toast;

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


public class RegistroFragment extends Fragment {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText preferenciasEditText;
    private EditText edadEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(15, 15, 15, 15);
        layout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background_color));

        // Imagen
        ImageView imageView = new ImageView(requireContext());
        imageView.setImageResource(R.drawable.logo_savar);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                270
        );
        imageView.setLayoutParams(imageParams);
        layout.addView(imageView);

        // Espaciador 1
        addSpacer(layout, 75);

        // Campo Nombre
        firstNameEditText = new EditText(requireContext());
        firstNameEditText.setHint("Nombre");
        layout.addView(firstNameEditText);

        // Espaciador 2
        addSpacer(layout, 5);

        // Campo Apellido
        lastNameEditText = new EditText(requireContext());
        lastNameEditText.setHint("Apellido");
        layout.addView(lastNameEditText);

        // Espaciador 3
        addSpacer(layout, 5);

        // Campo Usuario
        usernameEditText = new EditText(requireContext());
        usernameEditText.setHint("Usuario");
        layout.addView(usernameEditText);

        // Espaciador 4
        addSpacer(layout, 5);

        // Campo Correo Electrónico
        emailEditText = new EditText(requireContext());
        emailEditText.setHint("Correo Electrónico");
        layout.addView(emailEditText);

        // Espaciador 5
        addSpacer(layout, 5);

        // Campo Preferencias
        preferenciasEditText = new EditText(requireContext());
        preferenciasEditText.setHint("Preferencias");
        layout.addView(preferenciasEditText);

        // Espaciador 6
        addSpacer(layout, 5);

        // Campo Edad
        edadEditText = new EditText(requireContext());
        edadEditText.setHint("Edad");
        layout.addView(edadEditText);

        // Espaciador 7
        addSpacer(layout, 5);

        // Campo Contraseña
        passwordEditText = new EditText(requireContext());
        passwordEditText.setHint("Contraseña");
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(passwordEditText);

        // Indicaciones para la contraseña
        TextView passwordHintTextView = new TextView(requireContext());
        passwordHintTextView.setText("La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial.");
        passwordHintTextView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
        layout.addView(passwordHintTextView);

        // Botón Registrarse
        Button registerButton = new Button(requireContext());
        registerButton.setText("Registrarse");
        registerButton.setBackgroundColor(Color.parseColor("#0B4F6C"));
        registerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        registerButton.setTextSize(18);
        //registerButton.setOnClickListener(v -> registerUser());
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        layout.addView(registerButton);

        scrollView.addView(layout);
        return scrollView;
    }

    // Método auxiliar para agregar espaciadores
    private void addSpacer(LinearLayout layout, int height) {
        View spacer = new View(requireContext());
        LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                height
        );
        spacer.setLayoutParams(spacerParams);
        layout.addView(spacer);
    }

    private void registerUser() {
        try {
            // Obtén los datos ingresados por el usuario
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String nombre = firstNameEditText.getText().toString();
            String apellidos = lastNameEditText.getText().toString();
            String correo = emailEditText.getText().toString();
            String preferencias = preferenciasEditText.getText().toString();
            int edad = Integer.parseInt(edadEditText.getText().toString());

            // Crea una instancia de User con los datos
            User newUser = new User(username, password, nombre, apellidos, correo, preferencias, edad);

            // Configura la llamada a la API usando Retrofit
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<ResponseModel> call = apiService.createUser(newUser);

            // Encola la llamada para ejecutar la solicitud en segundo plano
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        // Manejar la respuesta de inicio de sesión exitosa
                        String message = response.body().getMessage();
                        String status = response.body().getStatus();
                        User user = response.body().getUser();
                        Toast.makeText(requireContext(), "Bienvenido " + user.getNombre(), Toast.LENGTH_SHORT).show();
                        // Guardamos el dato del usuario registrado
                        SharedPreferences preferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("isLoggedIn", true); // Establece que el usuario está logueado
                        editor.putString("username", username); // Guardar el nombre de usuario (opcional)
                        editor.putString("name", nombre);
                        editor.apply();
                        // Redirigir a MainActivity después de iniciar sesión correctamente
                        Intent intent = new Intent(requireActivity(), MainActivity.class);
                        startActivity(intent);
                        requireActivity().finish(); // Cerrar LoginActivity para que no puedan regresar
                    } else {
                        Toast.makeText(requireContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(requireContext(), "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Por favor, ingresa una edad válida.", Toast.LENGTH_SHORT).show();
        }
    }
}