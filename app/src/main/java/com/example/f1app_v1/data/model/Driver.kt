package com.example.f1app_v1.data.model

import com.example.f1app_v1.Info

data class DriverIds(

    val stage: Stage
) {
    inner class Stage(
        val competitors: List<Comp>
    ) {
        inner class Comp(
            val id: String = "",
            val name:String=""
        )
    }
}




data class Driver(
    val info: Info
)

data class Info(
    val placeofbirth: String = "",
    val country_code_of_residence: String = "",
    val weight: Int = -1
)