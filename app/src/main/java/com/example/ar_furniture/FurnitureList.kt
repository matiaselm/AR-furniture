package com.example.ar_furniture

import java.io.Serializable

class Furniture(var name: String, var type: String,var src: HashMap<String, String>): Serializable {}

object FurnitureList {
    val lista = ArrayList<Furniture>()
    val chairColors = HashMap<String, String>()
    val benchColors = HashMap<String, String>()

    init{
        chairColors.put("musta", "rock.gltf")
        chairColors.put("valkoinen", "rock.gltf")

        benchColors.put("musta", "euroshopperMusta.gltf")
        benchColors.put("punainen", "euroshopper.gltf")
        benchColors.put("valkoinen", "euroshopperValkoinen.gltf")

        lista.add(Furniture("graniitti", "kivi", chairColors))
        lista.add(Furniture("es", "euroshopper", benchColors))
    }
}