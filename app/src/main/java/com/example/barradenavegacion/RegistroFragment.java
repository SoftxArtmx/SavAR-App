package com.example.barradenavegacion;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class RegistroFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(15, 15, 15, 15);
        layout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background_color));

        ImageView imageView = new ImageView(requireContext());
        imageView.setImageResource(R.drawable.whatsapp_image_2024_10_10_at_13_22_15);
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

        EditText firstNameEditText = new EditText(requireContext());
        firstNameEditText.setHint("Nombre");
        layout.addView(firstNameEditText);

        View spacer2 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer2.setLayoutParams(spacerParams2);
        layout.addView(spacer2);

        EditText lastNameEditText = new EditText(requireContext());
        lastNameEditText.setHint("Apellido");
        layout.addView(lastNameEditText);

        View spacer3 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer3.setLayoutParams(spacerParams3);
        layout.addView(spacer3);

        EditText usernameEditText = new EditText(requireContext());
        usernameEditText.setHint("Usuario");
        layout.addView(usernameEditText);

        View spacer4 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer4.setLayoutParams(spacerParams4);
        layout.addView(spacer4);

        EditText emailEditText = new EditText(requireContext());
        emailEditText.setHint("Correo Electronico");
        layout.addView(emailEditText);

        View spacer5 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer5.setLayoutParams(spacerParams5);
        layout.addView(spacer5);

        EditText passwordEditText = new EditText(requireContext());
        passwordEditText.setHint("Contraseña");
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(passwordEditText);

        TextView passwordHintTextView = new TextView(requireContext());
        passwordHintTextView.setText("La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial.");
        passwordHintTextView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
        layout.addView(passwordHintTextView);

        View spacer6 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams6 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer6.setLayoutParams(spacerParams6);
        layout.addView(spacer6);

        Button registerButton = new Button(requireContext());
        registerButton.setText("Registrarse");
        registerButton.setBackgroundColor(Color.parseColor("#0B4F6C"));
        registerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        registerButton.setPadding(10, 10, 10, 10);
        registerButton.setTextSize(18);
        registerButton.setClickable(true);
        registerButton.setFocusable(true);
        registerButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.rounded_button));
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle registration confirmation and save
            }
        });
        layout.addView(registerButton);

        scrollView.addView(layout);
        return scrollView;
    }
}