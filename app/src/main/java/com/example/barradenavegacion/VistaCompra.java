/*
 * |                             S O F T x A R T
 * | Proyecto: SavAR
 * | Archivo: VistaCompra
 * | Clase/modulo a codificar: MO_HistorialCompras
 * | Descripción general: Clase auxiliar para la creación de vistas para cada una de las compras
 * | Funciones especificas:
 * |   -Visualizar datos tales como: Lugar, total de la compra, fecha
 * |
 * | Desarrollador encargado: Marcos Juárez
 * | Aprobado por: Diego Muñoz, Hermione Sandoval
 * */

package com.example.barradenavegacion;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VistaCompra extends LinearLayout {

    private TextView textViewDato1;
    private TextView textViewValor1;
    private TextView textViewDato2;
    private TextView textViewValor2;
    private TextView textViewDato3;
    private TextView textViewValor3;
    private Button boton;

    // Constructor para inflar el layout
    public VistaCompra(Context context) {
        super(context);
        init(context);
    }

    public VistaCompra(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VistaCompra(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // Inflar el layout personalizado
        LayoutInflater.from(context).inflate(R.layout.layout_datos_compra, this, true);

        // Referenciar los componentes del layout
        textViewDato1 = findViewById(R.id.textViewDato1);
        textViewValor1 = findViewById(R.id.textViewValor1);
        textViewDato2 = findViewById(R.id.textViewDato2);
        textViewValor2 = findViewById(R.id.textViewValor2);
        textViewDato3 = findViewById(R.id.textViewDato3);
        textViewValor3 = findViewById(R.id.textViewValor3);
    }

    // Método para asignar los valores de los datos
    public void setDatos(String dato1, String valor1, String dato2, String valor2, String dato3, String valor3) {
        textViewDato1.setText(dato1);
        textViewValor1.setText(valor1);
        textViewDato2.setText(dato2);
        textViewValor2.setText(valor2);
        textViewDato3.setText(dato3);
        textViewValor3.setText(valor3);
    }

    // Método para configurar el listener del botón
    public void setBotonListener(OnClickListener listener) {
        boton.setOnClickListener(listener);
    }
}
