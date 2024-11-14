package mo.cart;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barradenavegacion.R;

import java.util.ArrayList;

public class MiListaAdapter extends RecyclerView.Adapter<MiListaAdapter.ViewHolder> {

    private final ArrayList<String> listaItems;
    private final ArrayList<String> carritoItems;
    private final boolean esCarrito; // Variable para identificar si es la lista del carrito
    private final Context context;

    public MiListaAdapter(Context context, ArrayList<String> listaItems, ArrayList<String> carritoItems, boolean esCarrito) {
        this.context = context;
        this.listaItems = listaItems;
        this.carritoItems = carritoItems;
        this.esCarrito = esCarrito; // Inicializar la variable
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = esCarrito ? carritoItems.get(position) : listaItems.get(position);
        holder.tvItem.setText(item);

        // Configuración del checkbox y tachado de texto solo para "Mi lista"
        if (!esCarrito) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setOnCheckedChangeListener(null); // Para evitar problemas de referencia
            holder.checkBox.setChecked(false); // Inicialmente sin marcar

            // Configurar comportamiento del checkbox y tachado de texto
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    holder.tvItem.setPaintFlags(holder.tvItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // Tachar texto
                } else {
                    holder.tvItem.setPaintFlags(holder.tvItem.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG); // Quitar tachado
                }
            });
        } else {
            holder.checkBox.setVisibility(View.GONE); // Ocultar checkbox en el carrito
        }
    }

    @Override
    public int getItemCount() {
        return esCarrito ? carritoItems.size() : listaItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
