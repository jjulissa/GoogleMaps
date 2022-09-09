package com.example.practicomapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

//Esto es porque getMapAsync(this) necesita que nuestra activity
// implemente la función onMapReady() y para ello tenemos
// que añadir la interfaz OnMapReadyCallback.
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

// Crearemos una variable en la parte superior de la clase
// y le asignaremos el objeto GoogleMap cuando lo recibamos.
    private lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFragment()

    }
//  Creamos una variable y le decimos a supportFragmentManager que
//  busque un fragment que tenga una id llamada fragmentMap,
//  que será la id del fragment que añadimos en activity_main.xml,
//  luego a nuestra variable que contiene un SupportMapFragment
//  (fragment de tipo mapa) la inicializamos con la función getMapAsync..
    private fun createFragment() {

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map
        ) as? SupportMapFragment
        mapFragment?.getMapAsync {
                googleMap ->
            onMapReady(googleMap)
        }

//        val mapFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)

    }

//Esta interfaz que hemos implementado nos obliga a sobrescribir
// la función onMapReady(), que se llamará automáticamente cuando
// el mapa haya cargado. Es por eso que nuestra activity nos marca
// un error, pues no hemos sobrescrito el método todavía.
    override fun onMapReady(googleMap: GoogleMap) {

//  Esta función nos devolverá un objeto GoogleMap que será muy
//  útil, es por ello que debemos guardarlo en una variable.
        map = googleMap
        createMarker()
    }
    private fun createMarker() {

//  Crear una instancia de un objeto LatLng() que recibirá dos parámetros,
//  la latitud y la longitud. Yo, en este ejemplo he puesto las coordenadas de uno de mis hoteles favoritos de HAITI.
        val coordinates = LatLng(18.959000448319692, -72.72780840573532)

//  llamaremos a nuestro mapa y con la función addMarker() le añadiremos MarkerOptions().
//  position(favoritePlace).title(«Mi playa favorita!»), simplemente le hemos puesto las
//  coordenadas anteriormente creadas y hemos asignado un title que se
//  mostrará cuando el usuario pulse el marker. El title es opcional.
        val marker = MarkerOptions().position(coordinates).title("Haiti")
        map.addMarker(marker)

//  Añadir una animación para que el mapa haga zoom donde creamos el marker.
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f) ,
        4000,
            null
        )
//  La función animateCamera() recibirá tres parámetros:
// 1)   Un CameraUpdateFactory que a su vez llevará otros dos parámetros,
    // el primero las coordenadas donde queremos hacer zoom y
    // el segundo valor (es un float) será la cantidad de zoom
    // que queremos hacer en dichas coordenadas.
// 2)   La duración de la animación en milisegundos,
    // por ejemplo 4000 milisegundos son 4 segundos.
// 3)   Un listener que no vamos a utilizar, simplemente añadimos null.
    }
}


//Google también nos permite añadir markers, pequeñas etiquetas para marcar un lugar
// a través de sus coordenadas. Para ello crearemos una nueva función que
// la llamaremos cuando el método onMapReady() se ejecute.
