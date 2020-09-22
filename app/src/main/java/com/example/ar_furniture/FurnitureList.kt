package com.example.ar_furniture

import java.io.Serializable

class Furniture(var name: String, var type: String,var src: HashMap<String, String>): Serializable {}

object FurnitureList {
    val lista = ArrayList<Furniture>()
    val barstoolColors = HashMap<String, String>()
    val chairColors = HashMap<String, String>()

    init{
        barstoolColors.put("default","barstool/barstool.gltf")
        barstoolColors.put("musta","barstool/barstoolMusta.gltf")
        barstoolColors.put("valkoinen","barstool/barstoolValkoinen.gltf")

        chairColors.put("default","chair/chair.gltf")
        chairColors.put("musta","chair/chairMusta.gltf")
        chairColors.put("valkoinen","chair/chairValkoinen.gltf")

        lista.add(Furniture("chair", "chair", chairColors))
        lista.add(Furniture("barstool", "stool", barstoolColors))
    }
}