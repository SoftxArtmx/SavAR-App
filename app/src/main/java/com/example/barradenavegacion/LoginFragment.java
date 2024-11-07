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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

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

        EditText usernameEditText = new EditText(requireContext());
        usernameEditText.setHint("Usuario");
        layout.addView(usernameEditText);

        View spacer2 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        spacer2.setLayoutParams(spacerParams2);
        layout.addView(spacer2);

        EditText passwordEditText = new EditText(requireContext());
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
                // Handle login
            }
        });
        loginButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.rounded_button));
        layout.addView(loginButton);

        scrollView.addView(layout);
        return scrollView;
    }
}