package com.example.gamejournal;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderVideojuegos> sliderVideojuegos;
    private ViewPager2 viewPager2;
    private Context context;
    private OnItemClickListener mListener; // Interfaz para manejar clics en los elementos del slider


    public SliderAdapter(List<SliderVideojuegos> sliderVideojuegos, ViewPager2 viewPager2) {
        this.sliderVideojuegos = sliderVideojuegos;
        this.viewPager2 = viewPager2;
    }
    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_videogame_container, parent, false)); // Infla el diseño del elemento del slider
    }
    // Método para asociar datos a una instancia de ViewHolder
    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        holder.setImage(sliderVideojuegos.get(position)); // Establece la imagen del elemento en la posición actual

        // Si se alcanza el penúltimo elemento del slider, se realiza un post del runnable
        if (position == sliderVideojuegos.size() - 2) {
            viewPager2.post(runnable);
        }

        // Establece un OnClickListener para el elemento del slider
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position); // Invoca el método onItemClick de la interfaz OnItemClickListener
                }
            }
        });
    }
    // Método para obtener la cantidad de elementos en el slider
    @Override
    public int getItemCount() {
        return sliderVideojuegos.size();
    }
    // Clase interna que representa un elemento del slider
    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView; // ImageView para mostrar la imagen del elemento del slider

        // Constructor de SliderViewHolder, recibe la vista del elemento
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide); // Encuentra la ImageView en la vista
        }
        // Método para establecer la imagen del elemento del slider
        void setImage(SliderVideojuegos sliderVideojuegos) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60)); // Aplica transformaciones a la imagen (recorte central y esquinas redondeadas)

            Glide.with(context).load(sliderVideojuegos.getImage()).apply(requestOptions).into(imageView); // Carga la imagen utilizando Glide con las opciones especificadas
        }
    }
    // Runnable para duplicar la lista de elementos del slider cuando se alcanza el penúltimo elemento
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderVideojuegos.addAll(sliderVideojuegos); // Duplica la lista de elementos
            notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
        }
    };
    // Interfaz para manejar clics en los elementos del slider
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // Método para establecer el listener de clics en los elementos del slider
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
