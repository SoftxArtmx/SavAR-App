package com.example.barradenavegacion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class InicioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(15, 15, 15, 15);
        layout.setBackgroundColor(Color.argb(255, 247, 247, 247));

        ImageView imageView = new ImageView(requireContext());
        imageView.setImageResource(R.drawable.logo_savar);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                750
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

        Button loginButton = new Button(requireContext());
        loginButton.setText("Iniciar Sesión");
        loginButton.setBackgroundColor(Color.parseColor("#0B4F6C"));
        loginButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        loginButton.setPadding(10, 10, 10, 10);
        loginButton.setTextSize(18);
        loginButton.setClickable(true);
        loginButton.setFocusable(true);

        layout.addView(loginButton);
        View spacer2 = new View(requireContext());
        LinearLayout.LayoutParams spacerParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                75
        );
        spacer2.setLayoutParams(spacerParams2);
        layout.addView(spacer2);

        Button registerButton = new Button(requireContext());
        registerButton.setText("Registrarse");
        registerButton.setBackgroundColor(Color.parseColor("#0B4F6C"));
        registerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        registerButton.setPadding(10, 10, 10, 10);
        registerButton.setTextSize(18);
        registerButton.setClickable(true);
        registerButton.setFocusable(true);
        layout.addView(registerButton);

        scrollView.addView(layout);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new LoginFragment()); // Cambia `HomeFragment` si quieres navegar a otro fragmento
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RegistroFragment()); // Cambia `HomeFragment` si quieres navegar a otro fragmento
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return scrollView;
    }
}