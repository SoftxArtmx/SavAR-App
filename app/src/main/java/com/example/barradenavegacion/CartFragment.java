package com.example.barradenavegacion;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import mo.cart.MiListaAdapter;

public class CartFragment extends Fragment {

    private RecyclerView recyclerMiLista;
    private MiListaAdapter miListaAdapter;
    private ArrayList<String> listaItems;
    private ArrayList<String> carritoItems;
    private FloatingActionButton fabAgregar;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        recyclerMiLista = view.findViewById(R.id.recyclerMiLista);
        fabAgregar = view.findViewById(R.id.fabAgregar);

        // Inicializar las listas
        listaItems = new ArrayList<>();
        carritoItems = new ArrayList<>();

        // Inicializar el adaptador
        miListaAdapter = new MiListaAdapter(getActivity(), listaItems, carritoItems);
        recyclerMiLista.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMiLista.setAdapter(miListaAdapter);

        // Configurar el botón para agregar ítems
        fabAgregar.setOnClickListener(v -> mostrarDialogoAgregarItem());

        return view;
    }

    private void mostrarDialogoAgregarItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Agregar nuevo ítem");

        // Inflar el layout del diálogo
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_item, null);
        builder.setView(dialogView);

        final EditText input = dialogView.findViewById(R.id.editTextItem); // Obtener el EditText

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nuevoItem = input.getText().toString(); // Obtener el texto ingresado
            if (!nuevoItem.isEmpty()) {
                listaItems.add(nuevoItem);
                miListaAdapter.notifyDataSetChanged(); // Notificar que se ha agregado un nuevo ítem
            } else {
                Toast.makeText(requireContext(), "El ítem no puede estar vacío", Toast.LENGTH_SHORT).show(); // Mensaje de error si está vacío
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}