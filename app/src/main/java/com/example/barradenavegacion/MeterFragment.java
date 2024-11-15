package com.example.barradenavegacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MeterFragment extends Fragment {

    private EditText budgetInput;
    private TextView statusMessage;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflamos el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_meter, container, false);

        // Referenciamos los elementos de la UI
        budgetInput = view.findViewById(R.id.budgetInput);
        statusMessage = view.findViewById(R.id.statusMessage);
        saveButton = view.findViewById(R.id.saveButton);

        // Configuramos la acción del botón de guardar
        saveButton.setOnClickListener(v -> {
            String budget = budgetInput.getText().toString().trim();
            if (!budget.isEmpty() && !budget.equals("$ 0.00")) {
                statusMessage.setVisibility(View.VISIBLE);  // Mostrar mensaje de estado
                statusMessage.setText("Presupuesto guardado: $" + budget);
                Toast.makeText(getActivity(), "Presupuesto guardado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Por favor ingresa un presupuesto válido", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}