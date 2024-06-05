package com.example.gamejournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adaptador para un RecyclerView que muestra una lista de géneros de videojuegos.
public class VideojuegosGeneroAdapter extends RecyclerView.Adapter<VideojuegosGeneroAdapter.ViewHolder> {

    // Lista de géneros de videojuegos.
    private List<String> generoList;
    // Contexto en el que se utiliza el adaptador.
    private Context context;
    // Listener para manejar los clics en los ítems de la lista.
    private OnGeneroClickListener onGeneroClickListener;
    // Constructor del adaptador.
    public VideojuegosGeneroAdapter(List<String> generoList, Context context, OnGeneroClickListener listener) {
        this.generoList = generoList;
        this.context = context;
        this.onGeneroClickListener = listener;
    }
    // Método que infla el layout para cada ítem del RecyclerView.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout para el ítem del RecyclerView.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_genero_videojuego, parent, false);
        return new ViewHolder(view);
    }
    // Método que vincula los datos de la lista al ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtiene el género en la posición especificada.
        String genero = generoList.get(position);
        // Llama al método bind del ViewHolder para actualizar el contenido.
        holder.bind(genero);
    }
    // Método que devuelve el número de ítems en la lista.
    @Override
    public int getItemCount() {
        return generoList.size();
    }

    // ViewHolder que representa cada ítem del RecyclerView.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // TextView que muestra el género del videojuego.
        private TextView genero;
        // Constructor del ViewHolder.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Asocia el TextView del layout con la variable.
            genero = itemView.findViewById(R.id.generoVideojuego);
            // Establece el OnClickListener para el ítem.
            itemView.setOnClickListener(this);
        }
        // Método para vincular el texto del género al TextView.
        public void bind(String generoText) {
            genero.setText(generoText);
        }
        // Método que maneja los clics en los ítems.
        @Override
        public void onClick(View v) {
            // Obtiene la posición del ítem clicado.
            int position = getAdapterPosition();
            // Verifica que la posición sea válida.
            if (position != RecyclerView.NO_POSITION) {
                // Obtiene el género seleccionado.
                String selectedGenero = generoList.get(position);
                // Llama al método onGeneroClick del listener.
                onGeneroClickListener.onGeneroClick(selectedGenero);
            }
        }
    }
    // Interfaz para manejar los clics en los ítems de la lista.
    public interface OnGeneroClickListener {
        void onGeneroClick(String genero);
    }
}
