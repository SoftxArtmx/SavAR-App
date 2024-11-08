package mo.ar;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.barradenavegacion.MainActivity;
import com.example.barradenavegacion.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopupFacade {

    private Context context;
    private FrameLayout blurBackground;
    private static final String TAG = "PopupFacade";

    public PopupFacade(Context context, FrameLayout blurBackground) {
        this.context = context;
        this.blurBackground = blurBackground;
    }

    public void showPopup(String message, String productCode, Runnable onRescan, Runnable onConfirm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message);

        // Custom View para el popup
        ScrollView layout = (ScrollView) ((MainActivity) context).getLayoutInflater().inflate(R.layout.popup_layout, null);
        TextView productName = layout.findViewById(R.id.product_name);
        TextView productBrand = layout.findViewById(R.id.product_brand_label);
        TextView productType = layout.findViewById(R.id.product_type_label);
        TextView productSize = layout.findViewById(R.id.product_size_label);
        ImageView productImage = layout.findViewById(R.id.product_image);

        // Llamada a la API para obtener información del producto
        obtenerProducto(productCode, productName, productBrand, productType, productSize, productImage);

        // Spinner de cantidad
        Spinner spinnerQuantity = layout.findViewById(R.id.product_quantity_spinner);
        Integer[] quantities = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quantities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(adapter);

        builder.setView(layout);

        AlertDialog dialog = builder.create();

        // Mostrar fondo oscuro detrás del popup
        blurBackground.setVisibility(View.VISIBLE);

        Button btnRescan = layout.findViewById(R.id.btn_rescan);
        btnRescan.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(context, "Reiniciando escaneo...", Toast.LENGTH_SHORT).show();
            blurBackground.setVisibility(View.GONE);
            onRescan.run();
        });

        Button btnConfirm = layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(context, "Producto confirmado", Toast.LENGTH_SHORT).show();
            blurBackground.setVisibility(View.GONE);
            onConfirm.run();
        });

        dialog.show();

        dialog.setOnDismissListener(dialogInterface -> blurBackground.setVisibility(View.GONE));
    }

    private void obtenerProducto(String productId, TextView nombreProducto, TextView marca, TextView tipo, TextView tamano, ImageView productoImage) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ProductoRespuesta> call = apiService.obtenerProductoPorId(productId);

        call.enqueue(new Callback<ProductoRespuesta>() {
            @Override
            public void onResponse(Call<ProductoRespuesta> call, Response<ProductoRespuesta> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Producto producto = response.body().getProducto();

                    // Mostrar la información del producto en los TextViews
                    nombreProducto.setText(producto.getNombre());
                    marca.setText("Marca: " + producto.getMarca());
                    tipo.setText("Tipo: " + producto.getTipo());
                    tamano.setText("Tamaño: " + producto.getQtyunit() + " " + producto.getTipUnit());

                    // Cargar la imagen desde el enlace usando Glide
                    String enlaceNube = producto.getEnlace_nube();
                    Glide.with(context)
                            .load(enlaceNube)
                            .placeholder(R.drawable.ic_product_placeholder)
                            .into(productoImage);
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.message());
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductoRespuesta> call, Throwable t) {
                Log.e(TAG, "Error de conexión: " + t.getMessage());
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
