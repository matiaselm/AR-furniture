package com.example.ar_furniture

object FurnitureList {
    val lista = ArrayList<Furniture>()

    init{
        lista.add(Furniture("graniitti", "kivi", "rock.gltf"))
        lista.add(Furniture("es", "euroshopper", "euroshopper.gltf"))
    }
}