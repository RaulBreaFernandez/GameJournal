package com.example.gamejournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapaterTendencias, adapterGenero, adapterNuevosLanzamientos;
    private RecyclerView recyclerViewTendencias, recyclerViewGenero, recyclerViewNuevosLanzamientos;
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    private List<Videojuego> videojuegoList = new ArrayList<>();
    private List<Videojuego> proximoVideojuegoList = new ArrayList<>();
    private List<Videojuego> sliderVideojuegoList = new ArrayList<>();
    private List<String> generoList = new ArrayList<>();
    private VideojuegosAdapter.OnItemClickListener listener;
    private VideojuegosGeneroAdapter.OnGeneroClickListener onGeneroClickListener;
    private ImageView imageViewFavorito;
    private Boolean favorito = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVideojuegoLists();
        initView();
        banners();
        setupRecyclerViews();

    }

    private void initVideojuegoLists() {
        videojuegoList.add(new Videojuego("Player Unknown Battlegrounds", "Shooter", "Krafton Inc", 2017, "https://image.api.playstation.com/vulcan/ap/rnd/202404/0504/c584205a4d3b37877102318d24775372f9337f19309c7003.jpg", "Aterriza en posiciones estratégicas, saquea armas y suministros, y sobrevive para que vuestro equipo sea el único en pie en los distintos y variados campos de batalla. Forma equipo y participa en los campos de batalla para experimentar el Battle Royale original como solo existe en PUBG: BATTLEGROUNDS.", 8.6, "https://xxboxnews.blob.core.windows.net/prod/sites/2/2022/01/PUBG_NewKeyArt2022.jpg", false));
        videojuegoList.add(new Videojuego("Jump King", "Plataformas", "Nexile", 2024, "https://store-images.s-microsoft.com/image/apps.43518.14346211770606155.4d34dee5-ab84-4876-83bd-1e181ab60f68.aed7275a-ee75-42b6-b785-0c00fea05e73", "Aventura táctica de saltos - Jump King: ¡Hay una buenorra en la cumbre!» es un desafío de plataformas sobre las dificultades durante el ascenso en busca de la legendaria buenorra. Esta solitaria aventura para alcanzar la cumbre te exigirá un dominio absoluto de la técnica del salto. Recuerda que cada caída supone una lección que tienes que aprender...", 7.1, "https://store-images.s-microsoft.com/image/apps.34157.14346211770606155.4d34dee5-ab84-4876-83bd-1e181ab60f68.696c9bfe-31d3-4228-8436-d3d0f48a12b3", true));
        videojuegoList.add(new Videojuego("Stardew Valley", "Simulación", "ConcernedApe", 2016, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAkTvqNvPZdF1XMMo3r1H9qZJdFyEgcoJIpOQOXRZ8Rg&s", "Acabas de heredar la vieja parcela agrícola de tu abuelo de Stardew Valley. Decides partir hacia una nueva vida con unas herramientas usadas y algunas monedas. ¿Te ves capaz de vivir de la tierra y convertir estos campos descuidados en un hogar próspero?", 8.9, "https://images4.alphacoders.com/782/thumb-1920-782781.png", false));
        videojuegoList.add(new Videojuego("Baldur's Gate 3", "RPG", "Larian Studios", 2023, "https://cdn2.steamgriddb.com/grid/5cdf5c84489e801e6bac5693b1c8e290.png", "Reúne a tu grupo y regresa a los Reinos Olvidados en una historia de compañerismo, traición, sacrificio, supervivencia y la atracción de un poder absoluto. Unas misteriosas aptitudes empiezan a surgir en tu interior por obra de un parásito de los azotamentes que te han implantado en el cerebro. Resístete y vuelve a la oscuridad contra sí misma o abraza la corrupción y conviértete en el mal supremo. De manos de los creadores de Divinity: Original Sin 2, llega un juego de rol para la nueva generación de consolas, ambientado en el mundo de Dungeons & Dragons.", 9.6, "https://initiate.alphacoders.com/images/132/cropped-1920-1080-1325553.jpg?5765", false));
        videojuegoList.add(new Videojuego("Sid Meier's Civilization VI", "Estrategia", "Firaxis Gamer", 2016, "https://m.media-amazon.com/images/I/A1BceoX-6nL._AC_UF894,1000_QL80_.jpg", "Civilization VI es la nueva entrega de la galardonada franquicia Civilization. Expande tu imperio, haz avanzar tu cultura y enfréntate a los mejores líderes de la historia. ¿Podrá tu civilización superar la prueba del tiempo?", 8.8, "https://initiate.alphacoders.com/images/700/cropped-1920-1080-700502.jpg?6602", false));
        videojuegoList.add(new Videojuego("Rocket League", "Carreras", "Psyonix", 2015, "https://m.media-amazon.com/images/M/MV5BMWE1NjRiMGEtMTUyMy00ODVkLWE2OWMtY2VjZjc3OGEwN2Q5XkEyXkFqcGdeQXVyNTgyNTA4MjM@._V1_FMjpg_UX1000_.jpg", "Compite en este título de alto octanaje que combina el fútbol de estilo arcade con el caos a cuatro ruedas. ¡Personaliza tu coche, salta al campo y compite en uno de los juegos deportivos más aclamados de todos los tiempos! ¡Descárgalo y haz el saque inicial! Salta al campo en solitario o con amigos en los modos de juego en línea 1v1, 2v2 y 3v3, o disfruta de los modos extra como Rumble, Snow Day o Hoops. ¡Desbloquea objetos con el Rocket Pass, sube de rango competitivo, participa en torneos competitivos, supera desafíos, disfruta del progreso multiplataforma y mucho más! El campo te espera. ¡Haz el saque inicial!", 8.5, "https://initiate.alphacoders.com/images/130/cropped-1920-1080-1300706.jpg?5994", false));
        videojuegoList.add(new Videojuego("NBA 2K24", "Deportes", "Visual Concepts", 2023, "https://store-images.s-microsoft.com/image/apps.11707.14149820581167521.ee00106c-c535-40d2-8892-1082ebcf87c0.4bed6227-a0cf-48b9-88d6-febe9c6c65eb", "Reúne a tu equipo y vive el pasado, presente y futuro de la cultura del baloncesto en NBA 2K24. Disfruta de un montón de acción pura y auténtica y de ilimitadas opciones personalizadas de Mi JUGADOR en Mi CARRERA. Colecciona una impresionante colección de leyendas y construye tu alineación perfecta en MyTEAM. Juega con tus equipos favoritos de la NBA y la WNBA en JUGAR YA.", 6.8, "https://initiate.alphacoders.com/images/132/cropped-1920-1200-1329637.jpeg?7803", false));

        proximoVideojuegoList.add(new Videojuego("Black Mith: Wukong", "RPG", "Game Science", 2024, "https://pics.filmaffinity.com/black_myth_wukong-856105810-large.jpg", "Black Myth: Wukong es un RPG de acción inspirado en la mitología china. La historia está basada en «Viaje al Oeste», una de las cuatro grandes novelas clásicas de la literatura china. Encarnarás al Predestinado, que ha de embarcarse en un viaje repleto de peligros y maravillas, donde descubrirá la verdad que se oculta tras una gloriosa leyenda del pasado.", 0 , "https://cdn.wccftech.com/wp-content/uploads/2023/12/black-myth-wukong-HD-scaled.jpg", false));
        proximoVideojuegoList.add(new Videojuego("EA FC 25", "Deportes", "Electronic Arts", 2024, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTf1o-UyVh_pRCtn8oPKTjrqYwm8bvQL5qGkRKvLNmcvw&s", "EA SPORTS FC™ 25 te da la bienvenida a The World's Game: la experiencia futbolística más fiel hasta la fecha con HyperMotionV, PlayStyles optimizado por Opta y el motor mejorado de Frostbite™.", 0, "https://ocdn.eu/sport-images-transforms/1/_Ysk9lBaHR0cHM6Ly9vY2RuLmV1L3B1bHNjbXMvTURBXy9iMTI5ZmEzYjM5N2VkNTEzYTQ0N2RhODE5NGEwMmFmZC5wbmeTlQMAAM0HgM0EOJUCzQSwAMLDkwmmZGYwMTFiBt4AAqEwAaExAQ/ea-sports-fc-25.png", false));
        proximoVideojuegoList.add(new Videojuego("Frostpunk 2", "Estrategia", "11 Bit Studios", 2024, "https://data.xxlgamer.com/products/5667/6NX2JWouwri5cG-big.jpg", "Descubre un juego de supervivencia urbano ambientado 30 años después de una apocalíptica tormenta de nieve que asoló la Tierra y la convirtió en un páramo gélido. En Frostpunk 2 te enfrentarás a una nueva amenaza mortífera: la naturaleza humana y su insaciable apetito de poder.", 0, "https://media.vandal.net/m/105329/frostpunk-2-20218121825368_1.jpg", false));
        proximoVideojuegoList.add(new Videojuego("Stalker 2: Heart of Chernobyl", "Shooter", "GSC Game World", 2024, "https://upload.wikimedia.org/wikipedia/en/8/80/STALKER_2_cover_art.jpg", "La Zona de Exclusión de Chornóbil cambió radicalmente tras la segunda explosión en 2006. Los mutantes violentos, las anomalías letales y las facciones enfrentadas hacen de la Zona un lugar en el que sobrevivir es todo un reto. Sin embargo, los valiosísimos artefactos atraen a gente de todo el mundo. Apodados Stalkers, se adentran en la zona por su cuenta y riesgo en busca de fortuna o posiblemente de la verdad que esconde el Corazón de Chornóbil.", 0, "https://cdn1.epicgames.com/offer/602a0ef0aceb46cca62445439661d712/EGS_STALKER2HeartofChornobyl_GSCGameWorld_S1_2560x1440-7cc8db55646ee7b969c48defed6963f4", false));
        proximoVideojuegoList.add(new Videojuego("Trans-Siberian Railway Simulator ", "Simulación", "Pentacle", 2024, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7gA3-5VSqXgYRM3UnOB6O9mQ6dTrq8cuGzg&s", "Ponte a los mandos de una locomotora rusa clásica y comprueba si los animales salvajes, la gente mala, las fuertes heladas y una locomotora que se rompe constantemente pueden detenerte. ¡Intenta sobrevivir en la línea de ferrocarril más larga del mundo!", 0, "https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/998400/capsule_616x353.jpg?t=1716566137", false));
        proximoVideojuegoList.add(new Videojuego("Titan Quest II", "RPG", "Grimlore Games", 2024, "https://image.api.playstation.com/vulcan/ap/rnd/202308/0412/5f29b8a9df639fa8cf89ea61346a6eabd96257de5505d9f4.jpg", "Regresa al escenario inspirado en la mitología clásica de Titan Quest y enfréntate a monstruos legendarios en tu viaje por una recreación fantástica de la antigua Grecia. Némesis, la diosa del castigo, está fuera de control. Ha corrompido los Hilos del Destino y condena a quienquiera que se oponga a su castigo eterno..., como tú. Toma tu arma, lucha junto a los dioses y cambia el propio destino para detener a Némesis, liberar a aquellos a los que ha castigado y forjar tu propia leyenda. De los creadores de SpellForce 3, llega un RPG de acción diseñado para una nueva generación de héroes mitológicos.", 0, "https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/1154030/capsule_616x353.jpg?t=1709285132", false));
        proximoVideojuegoList.add(new Videojuego("F1 24", "Carreras", "Codemasters", 2024, "https://image.api.playstation.com/vulcan/ap/rnd/202404/0814/4b28d6615b99f1c325c5f9e6b15a362116c19fff01e37e62.png", "Únete a la parrilla y forma parte de los 20. Conduce como los mejores en EA SPORTS™ F1® 24, el videojuego oficial del 2024 FIA Formula One World Championship™.", 0, "https://cdn1.epicgames.com/offer/edffc5fcc62140a7afd239d9e65df463/EGS_F124StandardEdition_Codemasters_S1_2560x1440-b36f31537d367449b39fa18ef2afe862", false));

        sliderVideojuegoList.add(new Videojuego("Elden Ring", "RPG", "From Software", 2022, "", "Álzate, Sinluz, y que la gracia te guíe para abrazar el poder del Círculo de Elden y encumbrarte como señor del Círculo en las Tierras Intermedias.", 9.6, "https://periodismo.ull.es/wp-content/uploads/2022/04/Se-rumorea-que-Elden-Ring-realizara-proximamente-una-nueva-prueba.jpg", false));
        sliderVideojuegoList.add(new Videojuego("Cyberpunk 2077", "RPG", "CD Project Red", 2020, "", "Cyberpunk 2077 es un RPG de aventura y acción de mundo abierto ambientado en el futuro sombrío de Night City, una peligrosa megalópolis obsesionada con el poder, el glamur y las incesantes modificaciones corporales.", 8.6, "https://cdn1.epicgames.com/offer/77f2b98e2cef40c8a7437518bf420e47/EGS_Cyberpunk2077_CDPROJEKTRED_S1_03_2560x1440-359e77d3cd0a40aebf3bbc130d14c5c7", false));
        sliderVideojuegoList.add(new Videojuego("Titan Quest Anniversary Edition", "RPG", "Iron Lore Entertainment", 2016, "", "Los titanes han escapado de su prisión eterna y siembran el caos en el mundo. Los dioses buscan a un héroe que pueda cambiar el curso de una lucha épica que decidirá tanto el destino de los dioses como el de la humanidad. En esta épica cruzada del bien contra el mal, los jugadores conocerán a los mayores villanos de la mitología griega, se enfrentarán a Cerbero y se aventurarán en las orillas del río Estigia.", 8.6, "https://www.nintendo.com/eu/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_TitanQuest_image1600w.jpg", false));
        sliderVideojuegoList.add(new Videojuego("Forza Horizon 4", "Carreras", "Playground Games", 2021, "", "Las estaciones dinámicas lo cambian todo en el mejor festival automovilístico del mundo. Ve por cuenta propia o únete a otros equipos para explorar la hermosa e histórica Gran Bretaña en un mundo abierto compartido.", 9.2, "https://i.ytimg.com/vi/RCRYs7yfeo4/maxresdefault.jpg",false));
        sliderVideojuegoList.add(new Videojuego("Valorant", "Shooter", "Riot Games", 2020, "", "Valorant es un hero shooter en primera persona ambientado en un futuro próximo. Los jugadores asumen el control de agentes, personajes que provienen de una gran cantidad de países y culturas de todo el mundo.", 8.0, "https://i.blogs.es/3f15c2/valorant/1366_2000.jpg", false));
        sliderVideojuegoList.add(new Videojuego("Age of Empires II: Definitive Edition", "Estrategia", "Forgotten Empires", 2019, "", "Disfruta como nunca de todas las campañas originales y las expansiones más vendidas. ¡Te aguarda una experiencia de juego de más de 200 horas que abarca todo un milenio de la historia humana! Juega en línea para desafiar a otros jugadores en tu empresa por dominar el mundo con 35 civilizaciones diferentes.", 8.4, "https://gaming-cdn.com/images/products/4820/orig/age-of-empires-ii-definitive-edition-definitive-edition-pc-juego-steam-cover.jpg?v=1713188935", false));
        sliderVideojuegoList.add(new Videojuego("Los Sims 4", "Simulación", "Electronic Arts", 2014, "", "Disfruta del poder de crear y controlar a personas en un mundo virtual donde no hay reglas. ¡Ejerce tu poder con total libertad, diviértete y juega a la vida!", 7.0, "https://cdn1.epicgames.com/offer/2a14cf8a83b149919a2399504e5686a6/SIMS4_EPIC_LANDSCAPE-Product-Image_2560x1440_2560x1440-9072dd5d94cccaf50d8b3edede5ba8a9", false));
        sliderVideojuegoList.add(new Videojuego("Super Mario Bros U Deluxe", "Plataformas", "Nintendo", 2019, "", "Corre, salta y pega pisotones de campeonato a lo largo de más de 160 niveles en 2D de desplazamiento lateral. New Super Mario Bros. U Deluxe para Nintendo Switch recupera el estilo de los juegos clásicos de Super Mario. Hasta cuatro jugadores pueden aunar fuerzas para recoger monedas y derrotar a los enemigos de camino al banderín. También pueden competir de manera amistosa para ver quién reúne más monedas. Sea como sea, la diversión está asegurada.", 8.4, "https://www.nintendo.com/eu/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_NewSuperMarioBrosUDeluxe_image1600w.jpg",false));

        generoList.add("Cualquier género");
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
        adapaterTendencias = new VideojuegosAdapter(videojuegoList, this, listener);
        recyclerViewTendencias.setAdapter(adapaterTendencias);
        // Configuración del adaptador y del RecyclerView para nuevos lanzamientos
        adapterNuevosLanzamientos = new VideojuegosAdapter(proximoVideojuegoList, this, listener);
        recyclerViewNuevosLanzamientos.setAdapter(adapterNuevosLanzamientos);
        // Configuración del adaptador y del RecyclerView para los géneros
        adapterGenero = new VideojuegosGeneroAdapter(generoList, this, new VideojuegosGeneroAdapter.OnGeneroClickListener() {
            @Override
            public void onGeneroClick(String genero) {
                // Filtrar los videojuegos de tendencias por género
                List<Videojuego> filteredTendencias = filterVideojuegosByGenero(genero, videojuegoList);
                adapaterTendencias = new VideojuegosAdapter(filteredTendencias, MainActivity.this, listener);
                recyclerViewTendencias.setAdapter(adapaterTendencias);
                // Filtrar los nuevos lanzamientos por género
                List<Videojuego> filteredNuevosLanzamientos = filterVideojuegosByGenero(genero, proximoVideojuegoList);
                adapterNuevosLanzamientos = new VideojuegosAdapter(filteredNuevosLanzamientos, MainActivity.this, listener);
                recyclerViewNuevosLanzamientos.setAdapter(adapterNuevosLanzamientos);
            }
        });
        recyclerViewGenero.setAdapter(adapterGenero);
    }

    private List<Videojuego> filterVideojuegosByGenero(String genero, List<Videojuego> videojuegoList) {
        List<Videojuego> filteredList = new ArrayList<>();
        if (genero.equals("Cualquier género")) {
            filteredList.addAll(videojuegoList);
        } else {
            for (Videojuego videojuego : videojuegoList) {
                if (videojuego.getGenero().equalsIgnoreCase(genero)) {
                    filteredList.add(videojuego);
                }
            }
        }
        return filteredList;
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

        SliderAdapter sliderAdapter = (SliderAdapter) viewPager2.getAdapter();
        if (sliderAdapter != null) {
            sliderAdapter.setOnItemClickListener(new SliderAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Obtener el videojuego seleccionado desde la lista de videojuegos en la posición dada
                    Videojuego selectedVideojuego = sliderVideojuegoList.get(position);
                    // Iniciar la actividad de detalles del videojuego
                    Intent intent = new Intent(MainActivity.this, VideojuegoDetailActivity.class);
                    intent.putExtra("videojuego", selectedVideojuego);
                    startActivity(intent);
                }
            });
        }

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
    imageViewFavorito = findViewById(R.id.corazon);

    imageViewFavorito.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            favorito = !favorito;
            filterAndRefreshRecyclerViews();
        }
    });
    }
    private void filterAndRefreshRecyclerViews() {
        List<Videojuego> filteredTendencias = favorito ? filterVideojuegosByFavorites(videojuegoList) : videojuegoList;
        adapaterTendencias = new VideojuegosAdapter(filteredTendencias, this, listener);
        recyclerViewTendencias.setAdapter(adapaterTendencias);

        List<Videojuego> filteredNuevosLanzamientos = favorito ? filterVideojuegosByFavorites(proximoVideojuegoList) : proximoVideojuegoList;
        adapterNuevosLanzamientos = new VideojuegosAdapter(filteredNuevosLanzamientos, this, listener);
        recyclerViewNuevosLanzamientos.setAdapter(adapterNuevosLanzamientos);
    }

    private List<Videojuego> filterVideojuegosByFavorites(List<Videojuego> videojuegos) {
        List<Videojuego> favorites = new ArrayList<>();
        for (Videojuego videojuego : videojuegos) {
            if (videojuego.isFavorito()) {
                favorites.add(videojuego);
            }
        }
        return favorites;
    }

}