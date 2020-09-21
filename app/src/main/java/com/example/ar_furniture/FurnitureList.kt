package com.example.ar_furniture

import java.io.Serializable

class Furniture(var name: String, var type: String,var src: HashMap<String, String>): Serializable {}

object FurnitureList {
    val lista = ArrayList<Furniture>()
    val rockColors = HashMap<String, String>()
    val euroshopperColors = HashMap<String, String>()
    val barstoolColors = HashMap<String, String>()
    val chairColors = HashMap<String, String>()

    init{
        rockColors.put("default", "rock.gltf")
        rockColors.put("valkoinen", "rock.gltf")

        barstoolColors.put("default","barstool.gltf")
        chairColors.put("default","chair.gltf")

        euroshopperColors.put("musta", "euroshopperMusta.gltf")
        euroshopperColors.put("default", "euroshopper.gltf")
        euroshopperColors.put("valkoinen", "euroshopperValkoinen.gltf")

        lista.add(Furniture("graniitti", "kivi", rockColors))
        lista.add(Furniture("es", "euroshopper", euroshopperColors))
        lista.add(Furniture("chair", "chair", chairColors))
        lista.add(Furniture("barstool", "stool", barstoolColors))
    }
}