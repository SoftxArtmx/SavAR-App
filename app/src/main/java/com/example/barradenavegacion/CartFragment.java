/*
 * |                             S O F T x A R T
 * | Proyecto: SaVR
 * | Archivo:
 * | Clase/modulo a codificar:
 * | Descripción general:
 * | Funciones especificas:
 * |
 * |
 * | Desarrollador encargado: Sandra Hermione Ortiz Sandoval
 * | Aprobado por: Marcos Emmanuel Juarez Navarro
 * |
 * | CAMBIOS REALIZADOS DESDE LA ULTIMA VERSION
 * | Nombre:        Fecha:               Cambios Realizados:
 * | LZG            13/11/24             Implementación de un arreglo que maneje los elementos escaneados en el modulo de camara y los muestre "En el carrito: ".
 * |
 * |
 * |
 * */

package com.example.barradenavegacion;

import android.annotation.SuppressLint;
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

import mo.cart.ListaItemsManager;
import mo.cart.MiListaAdapter;
import mo.ar.ConexionARCamara;

public class CartFragment extends Fragment {

    private RecyclerView recyclerMiLista;
    private RecyclerView recyclerCarrito;
    private MiListaAdapter miListaAdapter;
    private MiListaAdapter carritoAdapter;
    private ArrayList<String> listaItems;
    private ArrayList<String> carritoItems;
    private FloatingActionButton fabAgregar;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerMiLista = view.findViewById(R.id.recyclerMiLista);
        recyclerCarrito = view.findViewById(R.id.recyclerCarrito);
        fabAgregar = view.findViewById(R.id.fabAgregar);

        // Inicializar las listas
        listaItems = ListaItemsManager.getInstance().getListaItems();
        carritoItems = ConexionARCamara.getInstance().getProductosEnCarrito(); // Obtener elementos del singleton

        // Configurar adaptador para "Mi lista"
        miListaAdapter = new MiListaAdapter(getActivity(), listaItems, carritoItems, false);
        recyclerMiLista.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerMiLista.setAdapter(miListaAdapter);

        // Configurar adaptador para "En el carrito"
        carritoAdapter = new MiListaAdapter(getActivity(), listaItems, carritoItems, true);
        recyclerCarrito.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerCarrito.setAdapter(carritoAdapter);

        // Configurar el botón para agregar ítems a "Mi lista"
        fabAgregar.setOnClickListener(v -> mostrarDialogoAgregarItem());

        return view;
    }

    private void mostrarDialogoAgregarItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Agregar nuevo ítem");

        // Inflar el layout del diálogo
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_item, null);
        builder.setView(dialogView);

        final EditText input = dialogView.findViewById(R.id.editTextItem);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nuevoItem = input.getText().toString();
            if (!nuevoItem.isEmpty()) {
                listaItems.add(nuevoItem);
                miListaAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(requireContext(), "El ítem no puede estar vacío", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
