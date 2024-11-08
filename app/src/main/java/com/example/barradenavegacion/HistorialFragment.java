/*
 * |                             S O F T x A R T
 * | Proyecto: SavAR
 * | Archivo: UserFragment
 * | Clase/modulo a codificar: MO_HistorialCompras
 * | Descripción general: Clase auxiliar para la creación de la vista principal del historial de compra
 * | Funciones especificas:
 * |   -Mostrar el historial de compra
 * |
 * | Desarrollador encargado: Marcos Juárez
 * | Aprobado por: Diego Muñoz, Hermione Sandoval
 * */

package com.example.barradenavegacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HistorialFragment extends Fragment {
    private VistaCompra vistaCompra;
    public HistorialFragment( ) {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Inicializar la vista personalizada
        for ( int i = 0; i < 10 ; i++ ) {
            vistaCompra = new VistaCompra(getActivity());
            vistaCompra.setDatos("Total:", String.format("$%d", i), "Tienda:", "Soriana Doe", "Fecha", "00 sep 2024");
            LinearLayout layout = view.findViewById(R.id.linearLayoutCompras);
            layout.addView(vistaCompra);
        }

        return view;
    }
}