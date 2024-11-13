package mo.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MiListaAdapter extends RecyclerView.Adapter<MiListaAdapter.ViewHolder> {

    private final ArrayList<String> listaItems;
    private final ArrayList<String> carritoItems;
    private final Context context;

    public MiListaAdapter(Context context, ArrayList<String> listaItems, ArrayList<String> carritoItems) {
        this.context = context;
        this.listaItems = listaItems;
        this.carritoItems = carritoItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = listaItems.get(position);
        holder.tvItem.setText(item);
        holder.checkBox.setOnCheckedChangeListener(null); // Para evitar problemas de referencia
        holder.checkBox.setChecked(carritoItems.contains(item)); // Marcar el checkbox si el ítem está en el carrito

        // Manejar el evento de cambio de estado del checkbox
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!carritoItems.contains(item)) {
                    carritoItems.add(item); // Agregar al carrito
                }
            } else {
                carritoItems.remove(item); // Remover del carrito
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem); // Asegúrate de que este ID coincida con tu layout de ítem
            checkBox = itemView.findViewById(R.id.checkBox); // Asegúrate de que este ID coincida con tu layout de ítem
        }
    }
}