package com.example.gamejournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
    private VideojuegosAdapter.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVideojuegoLists();
        initView();
        banners();
        setupRecyclerViews();
        loadVideojuegos();

    }

    private void initVideojuegoLists() {
        videojuegoList.add(new Videojuego("Player Unknown Battlegrounds", "Shooter", "Krafton Inc", 2017, "https://image.api.playstation.com/vulcan/ap/rnd/202404/0504/c584205a4d3b37877102318d24775372f9337f19309c7003.jpg", "Aterriza en posiciones estratégicas, saquea armas y suministros, y sobrevive para que vuestro equipo sea el único en pie en los distintos y variados campos de batalla. Forma equipo y participa en los campos de batalla para experimentar el Battle Royale original como solo existe en PUBG: BATTLEGROUNDS.", 8.6, "https://xxboxnews.blob.core.windows.net/prod/sites/2/2022/01/PUBG_NewKeyArt2022.jpg"));
        videojuegoList.add(new Videojuego("Jump King", "Plataformas", "Nexile", 2024, "https://store-images.s-microsoft.com/image/apps.43518.14346211770606155.4d34dee5-ab84-4876-83bd-1e181ab60f68.aed7275a-ee75-42b6-b785-0c00fea05e73", "", 0, "https://store-images.s-microsoft.com/image/apps.34157.14346211770606155.4d34dee5-ab84-4876-83bd-1e181ab60f68.696c9bfe-31d3-4228-8436-d3d0f48a12b3"));
        videojuegoList.add(new Videojuego("Stardew Valley", "Simulación", "ConcernedApe", 2016, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAkTvqNvPZdF1XMMo3r1H9qZJdFyEgcoJIpOQOXRZ8Rg&s", "", 0, ""));
        videojuegoList.add(new Videojuego("Baldur's Gate 3", "RPG", "Larian Studios", 2023, "https://cdn2.steamgriddb.com/grid/5cdf5c84489e801e6bac5693b1c8e290.png", "", 0, ""));
        videojuegoList.add(new Videojuego("Sid Meier's Civilization VI", "Estrategia", "Firaxis Gamer", 2016, "https://m.media-amazon.com/images/I/A1BceoX-6nL._AC_UF894,1000_QL80_.jpg", "", 0, ""));
        videojuegoList.add(new Videojuego("Rocket League", "Carreras", "Psyonix", 2015, "https://m.media-amazon.com/images/M/MV5BMWE1NjRiMGEtMTUyMy00ODVkLWE2OWMtY2VjZjc3OGEwN2Q5XkEyXkFqcGdeQXVyNTgyNTA4MjM@._V1_FMjpg_UX1000_.jpg", "", 0, ""));
        videojuegoList.add(new Videojuego("NBA 2K24", "Deportes", "Visual Concepts", 2023, "https://store-images.s-microsoft.com/image/apps.11707.14149820581167521.ee00106c-c535-40d2-8892-1082ebcf87c0.4bed6227-a0cf-48b9-88d6-febe9c6c65eb", "", 0, ""));

        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukong", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg", "", 0, ""));
        proximoVideojuegoList.add(new Videojuego("EA FC 25", "Deportes", "Electronic Arts", 2024, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTf1o-UyVh_pRCtn8oPKTjrqYwm8bvQL5qGkRKvLNmcvw&s", "", 0, ""));
        proximoVideojuegoList.add(new Videojuego("Frostpunk 2", "Estrategia", "11 Bit Studios", 2024, "https://data.xxlgamer.com/products/5667/6NX2JWouwri5cG-big.jpg", "", 0, ""));
        proximoVideojuegoList.add(new Videojuego("Stalker 2: Heart of Chernobyl", "Shooter", "GSC Game World", 2024, "https://upload.wikimedia.org/wikipedia/en/8/80/STALKER_2_cover_art.jpg", "", 0, ""));
        proximoVideojuegoList.add(new Videojuego("Trans-Siberian Railway Simulator ", "Simulación", "Pentacle", 2024, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7gA3-5VSqXgYRM3UnOB6O9mQ6dTrq8cuGzg&s", "", 0, ""));
        proximoVideojuegoList.add(new Videojuego("Titan Quest II", "RPG", "Grimlore Games", 2024, "https://image.api.playstation.com/vulcan/ap/rnd/202308/0412/5f29b8a9df639fa8cf89ea61346a6eabd96257de5505d9f4.jpg", "", 0, ""));
        proximoVideojuegoList.add(new Videojuego("F1 24", "Carreras", "Codemasters", 2024, "https://image.api.playstation.com/vulcan/ap/rnd/202404/0814/4b28d6615b99f1c325c5f9e6b15a362116c19fff01e37e62.png", "", 0, ""));

        generoList.add("RPG");
        generoList.add("Shooter");
        generoList.add("Plataformas");
        generoList.add("Carreras");
        generoList.add("Simulación");
        generoList.add("Deportes");
        generoList.add("Estrategia");
    }

    private void setupRecyclerViews() {
        // Configuración del adaptador y del RecyclerView para tendencias
        adpaterTendencias = new VideojuegosAdapter(videojuegoList, this, listener);
        recyclerViewTendencias.setAdapter(adpaterTendencias);
        // Configuración del adaptador y del RecyclerView para nuevos lanzamientos
        adapterNuevosLanzamientos = new VideojuegosAdapter(proximoVideojuegoList, this, listener);
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

    public void onVideojuegoClick(Videojuego videojuego) {
        Intent intent = new Intent(MainActivity.this, VideojuegoDetailActivity.class);
        intent.putExtra("videojuego", videojuego);
        startActivity(intent);
    }

}