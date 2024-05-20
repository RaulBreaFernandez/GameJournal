package com.example.gamejournal;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adpaterTendencias, adapterGenero, adapterNuevosLanzamientos;
    private RecyclerView recyclerViewTendencias, recyclerViewGenero, recyclerViewNuevosLanzamientos;
    private ProgressBar progressBarTendencias, progressBarGenero, progressBarNuevosLanzamientos;
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    private List<Videojuego> videojuegoList = new ArrayList<>();
    private List<Videojuego> proximoVideojuegoList = new ArrayList<>();
    private List<String> generoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videojuegoList.add(new Videojuego("Elden Ring", "RPG", "From Software", 2022, "https://image.api.playstation.com/vulcan/ap/rnd/202107/1612/esnQejHW2fLjVn3QsqMORML0.png"));
        videojuegoList.add(new Videojuego("Cyberpunk 2077", "RPG", "CD Project Red", 2020, "https://www.dolby.com/siteassets/xf-site/content-detail-pages/cyberpunk-2077.jpg"));
        videojuegoList.add(new Videojuego("Titan Quest", "RPG", "Iron Lore Entertainment", 2006, "https://cdn1.epicgames.com/offer/096d7313d4ae432f9327081a45db439d/EGS_TitanQuestAnniversaryEdition_IronLoreEntertainmentTHQNordic_S2_1200x1600-0a7aa9a28c2e334b0cae858ce19977a7"));
        videojuegoList.add(new Videojuego("Age of Empires II: Definitive Edition", "RTS", "Forgotten Empires", 2019, "https://static.wikia.nocookie.net/ageofempires/images/9/95/Age2de-library-boxart.jpg.jpg/revision/latest?cb=20221101191630&path-prefix=es"));
        videojuegoList.add(new Videojuego("Los Sims 4", "Simulación", "Maxis", 2014, "https://sm.ign.com/ign_es/game/l/los-sims-4/los-sims-4_c8f4.jpg"));
        videojuegoList.add(new Videojuego("Forza Horizon 4", "Carreras", "Playground Games", 2018, "https://store-images.s-microsoft.com/image/apps.36093.14343301090572358.2000000000007864116.1feb0fed-abe9-4849-b638-8d7eca69cff4"));
        videojuegoList.add(new Videojuego("New Super Mario Bros U Deluxe", "Plataformas", "Nintendo", 2019, "https://i.pinimg.com/736x/35/08/1c/35081c1b20e869d7efd28c928889ac6b.jpg"));


        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukong", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg"));
        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukon", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg"));
        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukon", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg"));
        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukon", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg"));
        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukon", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg"));

        generoList.add(0, "RPG");
        generoList.add(1, "Shooter");
        generoList.add(2, "Plataformas");
        generoList.add(3, "Carreras");
        generoList.add(4, "Simulación");
        generoList.add(5, "Deportes");
        generoList.add(5, "Estrategia");

        initView();
        banners();
        setupRecyclerViews();
        loadVideojuegos();

    }

    private void setupRecyclerViews() {
        // Configuración del adaptador y del RecyclerView para tendencias
        adpaterTendencias = new VideojuegosAdapter(videojuegoList, this);
        recyclerViewTendencias.setAdapter(adpaterTendencias);
        // Configuración del adaptador y del RecyclerView para nuevos lanzamientos
        adapterNuevosLanzamientos = new VideojuegosAdapter(proximoVideojuegoList, this);
        recyclerViewNuevosLanzamientos.setAdapter(adapterNuevosLanzamientos);
        // Configuración del adaptador y del RecyclerView para los géneros
        adapterGenero = new VideojuegosGeneroAdapter(generoList, this);
        recyclerViewGenero.setAdapter(adapterGenero);
    }

    private void loadVideojuegos() {
            // Una vez cargados los datos, oculta las barras de progreso
            progressBarTendencias.setVisibility(View.GONE);
            progressBarGenero.setVisibility(View.GONE);
            progressBarNuevosLanzamientos.setVisibility(View.GONE);
    }

    private void banners() {

        List<SliderVideojuegos> sliderVideojuegos = new ArrayList<>();
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide1));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide3));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide4));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide5));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide6));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide7));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide8));
        sliderVideojuegos.add(new SliderVideojuegos(R.drawable.wide9));

        viewPager2.setAdapter(new SliderAdapter(sliderVideojuegos, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
            }
        });

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 2000);
    }

    private void initView() {

        viewPager2 = findViewById(R.id.viewPager);
        recyclerViewTendencias = findViewById(R.id.recyclerviewTendencias);
        recyclerViewTendencias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewGenero = findViewById(R.id.recyclerviewGenero);
        recyclerViewGenero.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNuevosLanzamientos = findViewById(R.id.recyclerviewNuevosLanzamientos);
        recyclerViewNuevosLanzamientos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        progressBarTendencias = findViewById(R.id.progressBarTendencias);
        progressBarGenero = findViewById(R.id.progressBarGenero);
        progressBarNuevosLanzamientos = findViewById(R.id.progressBarNuevosLanzamientos);

    }

}